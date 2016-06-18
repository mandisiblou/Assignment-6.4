package com.example.mandisi.myassign7.Services.settings.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.example.mandisi.myassign7.Repositories.Person.PersonRepository;
import com.example.mandisi.myassign7.Services.settings.ActivateService;
import com.example.mandisi.myassign7.Services.settings.GetMetaDataService;
import com.example.mandisi.myassign7.conf.util.App;
import com.example.mandisi.myassign7.domain.Settings;
import com.example.mandisi.myassign7.settings.Imp.SettingsRepositoryImpl;
import com.example.mandisi.myassign7.settings.SettingsRepository;
import com.example.mandisi.myassign7.Repositories.Person.Imp.PersonRepositoryImpl;

// This is a Bound Local Service
public class ActivateServiceImpl extends Service implements ActivateService {
    final private PersonRepository personRepository;
    final private SettingsRepository settingsRepository;
    final private GetMetaDataService getMetaDataService;

    private static ActivateServiceImpl service = null;

    public static ActivateServiceImpl getInstance() {
        if (service == null)
            service = new ActivateServiceImpl();
        return service;
    }

    private final IBinder localBinder = new ActivateServiceLocalBinder();

    private SettingsRepository repo;

    public ActivateServiceImpl() {
        personRepository = new PersonRepositoryImpl(App.getAppContext());
        settingsRepository = new SettingsRepositoryImpl(App.getAppContext());
        getMetaDataService = GetMetaDataServiceImpl.getInstance();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ActivateServiceLocalBinder extends Binder {
        public ActivateServiceImpl getService() {
            return ActivateServiceImpl.this;
        }
    }

    @Override
    public boolean isAccountActivated() {
        return repo.findAll().size() > 0;
    }

    @Override
    public boolean deactivateAccount() {
        int rows = repo.deleteAll();
        return rows > 0;

    }

    private Settings createSettings(Settings settings) {
        repo = new SettingsRepositoryImpl(App.getAppContext());
        return repo.save(settings);
    }
}
