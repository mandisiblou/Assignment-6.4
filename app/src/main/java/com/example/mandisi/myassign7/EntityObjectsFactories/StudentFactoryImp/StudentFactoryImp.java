package com.example.mandisi.myassign7.EntityObjectsFactories.StudentFactoryImp;

/**
 * Created by 211014486 on 4/17/2016.
 */
import com.example.mandisi.myassign7.EntityObjects.Students;
import com.example.mandisi.myassign7.EntityObjectsFactories.StudentFactory;
import com.example.mandisi.myassign7.ValuesObjects.Addrec;

import java.util.UUID;

public class StudentFactoryImp implements StudentFactory {
    private static StudentFactoryImp factory = null;

    private  StudentFactoryImp() {
    }
    public static StudentFactoryImp getInstance(){
        if(factory ==null)
            factory = new StudentFactoryImp();
        return factory;
    }
    public Students createStudents(Long studentID, int yearOfBirth, String name) {
        Students  students = new Students
                .Builder()
                .studentID(studentID)
                .yearOfBirth(yearOfBirth)
                .name(name)
                .addrec(new Addrec())
                .build();
        return students;
    }
}