package com.example.mandisi.myassign7.ValuesObjectFactories;

import com.example.mandisi.myassign7.ValuesObjects.Department;

/**
 * Created by 211014486 on 4/17/2016.
 */
public interface DepartmentFactory {
    Department createDepartment(String SID, String name);
}
