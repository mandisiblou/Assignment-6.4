package com.example.mandisi.myassign7.restapi.settings.api;

import com.example.mandisi.myassign7.Persons.Persons;

import java.io.IOException;


/**
 * Created by Mandisi on 2016/04/17.
 */
public interface ActivateAPI {
    Persons activateAccount(String email, String auth) throws IOException;
}
