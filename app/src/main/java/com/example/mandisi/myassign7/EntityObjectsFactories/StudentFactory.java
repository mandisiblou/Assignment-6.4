package com.example.mandisi.myassign7.EntityObjectsFactories;

import com.example.mandisi.myassign7.EntityObjects.Students;

/**
 * Created by 211014486 on 4/17/2016.
 */
public interface StudentFactory {
    Students createStudents(String SID, int yearOfBirth, String name);
}
