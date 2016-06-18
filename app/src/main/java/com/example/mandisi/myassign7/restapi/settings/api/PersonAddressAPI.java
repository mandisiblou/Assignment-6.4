package com.example.mandisi.myassign7.restapi.settings.api;

import com.example.mandisi.myassign7.Persons.PersonAddress;

import java.io.IOException;


/**
 * Created by Mandisi on 2016/04/29.
 */
public interface PersonAddressAPI {
    PersonAddress createPersonAddress(PersonAddress address) throws IOException;

    PersonAddress updatePersonAddress(PersonAddress address) throws IOException;
}
