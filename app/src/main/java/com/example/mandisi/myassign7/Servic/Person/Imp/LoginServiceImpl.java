package com.example.mandisi.myassign7.Servic.Person.Imp;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Set;

import com.example.mandisi.myassign7.Persons.Persons;
import com.example.mandisi.myassign7.Repositories.Person.PersonRepository;
import com.example.mandisi.myassign7.Repositories.Person.Imp.PersonRepositoryImpl;
import com.example.mandisi.myassign7.Servic.Person.LoginService;
import com.example.mandisi.myassign7.conf.util.App;

public class LoginServiceImpl extends Service implements LoginService {
    final private PersonRepository personRepository;
    private static LoginServiceImpl service = null;

    public static LoginServiceImpl getInstance() {
        if (service == null)
            service = new LoginServiceImpl();
        return service;
    }

    public LoginServiceImpl() {
        personRepository = new PersonRepositoryImpl(App.getAppContext());
    }

    private final IBinder localBinder = new LoginServiceBinder();

    public class LoginServiceBinder extends Binder {
        public LoginServiceImpl getService() {
            return LoginServiceImpl.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    @Override
    public boolean checkActivition() {
        Set<Persons> persons = personRepository.findAll();
        return persons.size() > 0;
    }

    @Override
    public boolean checkLogin(String emailAddress, String password) {
        boolean loggedIn = false;
        Set<Persons> persons = personRepository.findAll();
        for (Persons person : persons) {
            loggedIn = BCrypt.checkpw(password, person.getAuthvalue());
        }
        return loggedIn;
    }
}
