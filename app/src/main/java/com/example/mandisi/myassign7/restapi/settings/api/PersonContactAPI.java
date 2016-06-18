package com.example.mandisi.myassign7.restapi.settings.api;

import com.example.mandisi.myassign7.Persons.PersonContact;

import java.io.IOException;


/**
 * Created by Mandisi on 2016/04/29.
 */
public interface PersonContactAPI {
    PersonContact createPersonContact(PersonContact contact) throws IOException;

    PersonContact updatePersonContact(PersonContact contact) throws IOException;
}
