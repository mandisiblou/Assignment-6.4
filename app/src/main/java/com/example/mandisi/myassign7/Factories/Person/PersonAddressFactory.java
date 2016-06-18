package com.example.mandisi.myassign7.Factories.Person;

/**
 * Created by Mandisi on 2016-06-09.
 */
import com.example.mandisi.myassign7.Persons.PersonAddress;
import com.example.mandisi.myassign7.conf.util.DomainState;

import java.util.Date;


/**
 * Created by hashcode on 2016/04/12.
 */
public class PersonAddressFactory {
    public static PersonAddress getAddress(String address, String postalcode){
        return new PersonAddress.Builder()
                .state(DomainState.ACTIVE.name())
                .addressTypeId(address)
                .postalCode(postalcode)
                .date(new Date())
                .status(DomainState.ACTIVE.name())
                .build();
    }
}

