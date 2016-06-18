package com.example.mandisi.myassign7.Servic.Person.Imp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;

import java.io.IOException;

import com.example.mandisi.myassign7.Persons.PersonAddress;
import com.example.mandisi.myassign7.Repositories.Person.Imp.PersonAddressRepositoryImpl;
import com.example.mandisi.myassign7.Repositories.Person.PersonAddressRepository;
import com.example.mandisi.myassign7.Servic.Person.PersonAddressService;
import com.example.mandisi.myassign7.conf.util.App;
import com.example.mandisi.myassign7.restapi.settings.api.Impl.PersonAddressAPIImpl;
import com.example.mandisi.myassign7.restapi.settings.api.PersonAddressAPI;


public class PersonAddressServiceImpl extends IntentService implements PersonAddressService {
    private final PersonAddressAPI api;
    private final PersonAddressRepository repo;

    private static final String ACTION_ADD = "com.example.mandisi.myassign7.Servic.Person.Imp.action.ADD";
    private static final String ACTION_UPDATE = "com.example.mandisi.myassign7.Servic.Person.Imp.action.UPDATE";

    // TODO: Rename parameters
    private static final String EXTRA_ADD = "com.example.mandisi.myassign7.Servic.Person.Impextra.ADD";
    private static final String EXTRA_UPDATE = "com.example.mandisi.myassign7.Servic.Person.Imp.extra.UPDATE";

    private static PersonAddressServiceImpl service = null;

    public static PersonAddressServiceImpl getInstance() {
        if (service == null)
            service = new PersonAddressServiceImpl();
        return service;
    }

    public PersonAddressServiceImpl() {
        super("PersonAddressServiceImpl");
        api = new PersonAddressAPIImpl();
        repo = new PersonAddressRepositoryImpl(App.getAppContext());
    }


    @Override
    public void addPersonAddress(Context context, PersonAddress address) {
        Intent intent = new Intent(context, PersonAddressServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_ADD, address);
        context.startService(intent);

    }

    @Override
    public void updatePersonAddress(Context context, PersonAddress address) {
        Intent intent = new Intent(context, PersonAddressServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, address);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final PersonAddress personAddress = (PersonAddress) intent.getSerializableExtra(EXTRA_ADD);
                postAddress(personAddress);
            } else if (ACTION_UPDATE.equals(action)) {
                final PersonAddress personAddress = (PersonAddress) intent.getSerializableExtra(EXTRA_UPDATE);
                updateAddress(personAddress);
            }
        }
    }

    private void updateAddress(PersonAddress personAddress) {
        //POST and Save Local
        try {
            PersonAddress updatedAddress = api.updatePersonAddress(personAddress);
            repo.save(updatedAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void postAddress(PersonAddress personAddress) {
        //POST and Save Local
        try {
            PersonAddress createdAddress = api.createPersonAddress(personAddress);
            repo.save(createdAddress);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
