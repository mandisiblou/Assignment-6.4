package com.example.mandisi.myassign7.ValuesObjectFactories.DepartmentFactoryImp;

/**
 * Created by 211014486 on 4/17/2016.
 */
import com.example.mandisi.myassign7.ValuesObjectFactories.DepartmentFactory;
import com.example.mandisi.myassign7.ValuesObjects.Department;

import java.util.UUID;

public class DepartmentFactoryImp implements DepartmentFactory {
    private static DepartmentFactoryImp factory = null;

    private  DepartmentFactoryImp() {
    }
    public static DepartmentFactoryImp getInstance(){
        if(factory ==null)
            factory = new DepartmentFactoryImp();
        return factory;
    }
    public Department createDepartment(String SID, String name) {
        Department department = new Department
                .Builder()
                .SID(SID)
                .name(name)
                .build();
        return department;
    }
}
