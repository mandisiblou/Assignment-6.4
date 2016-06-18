package com.example.mandisi.myassign7.Servic.Person;

import android.content.Context;

import com.example.mandisi.myassign7.Persons.PersonAddress;


/**
 * Created by Mandisi on 2016/05/05.
 */
public interface PersonAddressService {
    void addPersonAddress(Context context, PersonAddress address);

    void updatePersonAddress(Context context, PersonAddress address);
}
