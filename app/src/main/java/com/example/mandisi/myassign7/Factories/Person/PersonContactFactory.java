package com.example.mandisi.myassign7.Factories.Person;

/**
 * Created by Mandisi on 2016-06-09.
 */

import com.example.mandisi.myassign7.Persons.PersonContact;
import com.example.mandisi.myassign7.conf.util.DomainState;
import java.util.Date;
/**
 * Created by hashcode on 2016/04/12.
 */
public class PersonContactFactory {
    public static PersonContact getContact(String contact, String value) {
        return new PersonContact.Builder()
                .state(DomainState.ACTIVE.name())
                .contactTypeId(contact)
                .contactValue(value)
                .date(new Date())
                .status(DomainState.ACTIVE.name())
                .build();
    }

}

