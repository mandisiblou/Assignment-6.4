package com.example.mandisi.myassign7.Services.settings.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;

import com.example.mandisi.myassign7.Persons.Persons;
import com.example.mandisi.myassign7.Repositories.Person.PersonRepository;
import com.example.mandisi.myassign7.Services.settings.ActivateAccountService;
import com.example.mandisi.myassign7.Services.settings.GetMetaDataService;
import com.example.mandisi.myassign7.SettingsFactory.SettingsFactory;
import com.example.mandisi.myassign7.conf.util.App;
import com.example.mandisi.myassign7.domain.Settings;
import com.example.mandisi.myassign7.restapi.settings.api.ActivateAPI;
import com.example.mandisi.myassign7.restapi.settings.api.Impl.ActivateAPIImpl;
import com.example.mandisi.myassign7.settings.Imp.SettingsRepositoryImpl;
import com.example.mandisi.myassign7.settings.SettingsRepository;
import com.example.mandisi.myassign7.conf.util.DomainState;
import com.example.mandisi.myassign7.Repositories.Person.Imp.PersonRepositoryImpl;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class ActivateAccountServiceImpl extends IntentService implements ActivateAccountService {
    final private String EXTRA_EMAIL = "EMAIL";
    final private String EXTRA_PASSWORD = "PASSWORD";
    final private PersonRepository personRepository;
    final private SettingsRepository settingsRepository;
    final private GetMetaDataService getMetaDataService;

    private static ActivateAccountServiceImpl service = null;

    public static ActivateAccountServiceImpl getInstance() {
        if (service == null)
            service = new ActivateAccountServiceImpl();
        return service;
    }


    public ActivateAccountServiceImpl() {
        super("ActivateAccountServiceImpl");
        personRepository = new PersonRepositoryImpl(App.getAppContext());
        settingsRepository = new SettingsRepositoryImpl(App.getAppContext());
        getMetaDataService = GetMetaDataServiceImpl.getInstance();
    }

    @Override
    public void activateAccount(Context context, String email, String password) {
        Intent intent = new Intent(context, ActivateAccountServiceImpl.class);
        intent.putExtra(EXTRA_EMAIL, email);
        intent.putExtra(EXTRA_PASSWORD, password);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String email = intent.getStringExtra(EXTRA_EMAIL);
        final String password = intent.getStringExtra(EXTRA_PASSWORD);
        handleRequest(email, password);
    }

    private void handleRequest(String email, String password) {

        String state = DomainState.NOTACTIVATED.name();
        ActivateAPI api = new ActivateAPIImpl();
        try {

            Persons persons = api.activateAccount(email, password);
            if (persons.getToken() != "NONE") {
                Settings settings = SettingsFactory.getSettings(email, persons.getOrganisation(), persons.getToken());
                Settings savedSettings = settingsRepository.save(settings);
                Persons savedPerson = personRepository.save(persons);
                if (savedSettings.getId() != null && savedPerson.getId() != null) {
                    state = DomainState.ACTIVATED.name();
                    getMetaDataService.getMetaData(App.getAppContext());
                }
            }
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction("ACCOUNT_ACTIVATION");
            broadcastIntent.putExtra("RESULT", " Account Successfully ACTIVATED!!!");
            getBaseContext().sendBroadcast(broadcastIntent);

        } catch (IOException e) {
            e.printStackTrace();
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction("ACCOUNT_ACTIVATION");
            broadcastIntent.putExtra("RESULT", " Account Activation FAILED!!!");
            getBaseContext().sendBroadcast(broadcastIntent);
        }

    }
}
