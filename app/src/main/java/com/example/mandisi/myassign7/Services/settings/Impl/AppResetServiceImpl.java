package com.example.mandisi.myassign7.Services.settings.Impl;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.example.mandisi.myassign7.Services.settings.AppResetService;
import com.example.mandisi.myassign7.conf.util.App;
import com.example.mandisi.myassign7.settings.AddressTypeRepository;
import com.example.mandisi.myassign7.settings.ContactTypeRespository;
import com.example.mandisi.myassign7.settings.GenderRepository;
import com.example.mandisi.myassign7.settings.Imp.AddressTypeRepositoryImpl;
import com.example.mandisi.myassign7.settings.Imp.ContactTypeRespositoryImpl;
import com.example.mandisi.myassign7.settings.Imp.GenderTypeRepositoryImpl;
import com.example.mandisi.myassign7.settings.Imp.SettingsRepositoryImpl;
import com.example.mandisi.myassign7.settings.SettingsRepository;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class AppResetServiceImpl extends IntentService implements AppResetService {
    private SettingsRepository settingsRepository;
    private ContactTypeRespository contactTypeRespository;
    private GenderRepository genderRepository;
    private AddressTypeRepository addressTypeRepository;

    private static AppResetServiceImpl service = null;

    public AppResetServiceImpl() {
        super("AppResetServiceImpl");
    }

    public static AppResetServiceImpl getInstance() {
        if (service == null)
            service = new AppResetServiceImpl();
        return service;
    }

    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_SETTINGS = "com.example.mandisi.myassign7.Services.settings.action.SETTINGS";
    private static final String ACTION_METARESET = "com.example.mandisi.myassign7.Services.settings.action.METARESET";

    @Override
    public void startActionSettings(Context context) {
        Intent intent = new Intent(context, AppResetServiceImpl.class);
        intent.setAction(ACTION_SETTINGS);
        context.startService(intent);
    }

    @Override
    public void startActionMetaReset(Context context) {
        Intent intent = new Intent(context, AppResetServiceImpl.class);
        intent.setAction(ACTION_METARESET);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();

            //Refactor to CoR later
            if (ACTION_SETTINGS.equals(action)) {
                resetSettimgs();
            } else if (ACTION_METARESET.equals(action)) {

                resetMetaData();
            }
        }
    }

    private void resetMetaData() {
        addressTypeRepository = new AddressTypeRepositoryImpl(App.getAppContext());
        addressTypeRepository.deleteAll();
        contactTypeRespository = new ContactTypeRespositoryImpl(App.getAppContext());
        contactTypeRespository.deleteAll();
        genderRepository = new GenderTypeRepositoryImpl(App.getAppContext());
        genderRepository.deleteAll();
    }

    private void resetSettimgs() {
        settingsRepository = new SettingsRepositoryImpl(App.getAppContext());
        settingsRepository.deleteAll();
    }


}
