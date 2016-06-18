package com.example.mandisi.myassign7.Factories.Person;

/**
 * Created by Mandisi on 2016-06-09.
 */
import com.example.mandisi.myassign7.Persons.Persons;

/**
 * Created by hashcode on 2016/04/12.
 */
public class PersonFactory {
    public static Persons getPerson(String email,String lastname,String password){
        return new Persons.Builder()
                .authvalue(password)
                .emailAddress(email)
                .lastName(lastname)
                .build();
    }
}

