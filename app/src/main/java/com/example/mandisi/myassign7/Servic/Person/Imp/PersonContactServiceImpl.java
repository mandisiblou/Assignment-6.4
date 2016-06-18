package com.example.mandisi.myassign7.Servic.Person.Imp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;

import com.example.mandisi.myassign7.Persons.PersonContact;
import com.example.mandisi.myassign7.Repositories.Person.Imp.PersonContactRepositoryImpl;
import com.example.mandisi.myassign7.Repositories.Person.PersonContactRepository;
import com.example.mandisi.myassign7.Servic.Person.PersonContactService;
import com.example.mandisi.myassign7.conf.util.App;
import com.example.mandisi.myassign7.restapi.settings.api.Impl.PersonContactAPIImpl;
import com.example.mandisi.myassign7.restapi.settings.api.PersonContactAPI;


public class PersonContactServiceImpl extends IntentService implements PersonContactService {
    private final PersonContactAPI api;
    private final PersonContactRepository repo;

    public static final String ACTION_ADD = "com.example.mandisi.myassign7.Servic.Person.Imp.action.ADD";
    public static final String ACTION_UPDATE = "com.example.mandisi.myassign7.Servic.Person.Imp.action.UPDATE";

    public static final String EXTRA_ADD = "com.example.mandisi.myassign7.Servic.Person.Imp.extra.ADD";
    public static final String EXTRA_UPDATE = "com.example.mandisi.myassign7.Servic.Person.Imp.extra.UPDATE";

    private static PersonContactServiceImpl service = null;

    public static PersonContactServiceImpl getInstance() {
        if (service == null)
            service = new PersonContactServiceImpl();
        return service;
    }

    public PersonContactServiceImpl() {
        super("PersonContactServiceImpl");
        api = new PersonContactAPIImpl();
        repo = new PersonContactRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addPersonContact(Context context, PersonContact contact) {
        Intent intent = new Intent(context, PersonContactServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    public void updatePersonContact(Context context, PersonContact contact) {
        Intent intent = new Intent(context, PersonContactServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final PersonContact personContact = (PersonContact) intent.getSerializableExtra(EXTRA_ADD);
                postContact(personContact);
            } else if (ACTION_UPDATE.equals(action)) {
                final PersonContact personContact = (PersonContact) intent.getSerializableExtra(EXTRA_UPDATE);
                updateContact(personContact);
            }
        }
    }

    private void updateContact(PersonContact personContact) {
        //REMOTE UPADTE AND LOCAL UPDATE
        try {
            PersonContact updatedContact = api.updatePersonContact(personContact);
            repo.save(updatedContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void postContact(PersonContact personContact) {
        //POST AND LOCAL SAVE
        try {
            PersonContact createdContact = api.createPersonContact(personContact);
            repo.save(createdContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




