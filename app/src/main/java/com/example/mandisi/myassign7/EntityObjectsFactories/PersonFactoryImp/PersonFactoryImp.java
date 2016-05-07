package com.example.mandisi.myassign7.EntityObjectsFactories.PersonFactoryImp;

/**
 * Created by 211014486 on 4/17/2016.
 */
import com.example.mandisi.myassign7.EntityObjects.Person;
import com.example.mandisi.myassign7.EntityObjectsFactories.PersonFactory;

import java.util.UUID;
/**
 * Created by Nkuli on 2016-04-09.
 */
public class PersonFactoryImp implements PersonFactory{
    private static PersonFactoryImp factory = null;

    private  PersonFactoryImp() {
    }
    public static PersonFactoryImp getInstance(){
        if(factory ==null)
            factory = new PersonFactoryImp();
        return factory;
    }
    public Person createPerson(Long id, String name, int yearOfBirth) {
        Person  person = new Person
                .Builder()
                .id(id)
                .name(name)
                .yearOfBirth(yearOfBirth)
                .build();
        return person;
    }
}
