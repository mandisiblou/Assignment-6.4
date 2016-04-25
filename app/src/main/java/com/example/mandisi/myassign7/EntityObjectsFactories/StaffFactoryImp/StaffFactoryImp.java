package com.example.mandisi.myassign7.EntityObjectsFactories.StaffFactoryImp;

/**
 * Created by 211014486 on 4/17/2016.
 */
import com.example.mandisi.myassign7.EntityObjects.Staffs;
import com.example.mandisi.myassign7.EntityObjectsFactories.StaffFactory;

import java.util.UUID;

public class StaffFactoryImp implements StaffFactory {
    private static StaffFactoryImp factory = null;

    private  StaffFactoryImp() {
    }
    public static StaffFactoryImp getInstance(){
        if(factory ==null)
            factory = new StaffFactoryImp();
        return factory;
    }
    public Staffs createStaff(String SID, int yearOfBirth, String name) {
        Staffs staffs = new Staffs
                .Builder()
                .SID(UUID.randomUUID().toString())
                .yearOfBirth(yearOfBirth)
                .name(name)
                .build();
        return staffs;
    }
}
