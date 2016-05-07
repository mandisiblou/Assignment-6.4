package com.example.mandisi.myassign7.EntityObjectsFactories;

import com.example.mandisi.myassign7.EntityObjects.Person;

/**
 * Created by 211014486 on 4/17/2016.
 */
public interface PersonFactory {
    Person createPerson(Long id, String name, int yearOfBirth);
}
