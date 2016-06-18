package com.example.mandisi.myassign7.Servic.Person;

import android.content.Context;

import com.example.mandisi.myassign7.Persons.PersonContact;


/**
 * Created by Mandisi on 2016/05/05.
 */
public interface PersonContactService {
    void addPersonContact(Context context, PersonContact contact);

    void updatePersonContact(Context context, PersonContact contact);
}
