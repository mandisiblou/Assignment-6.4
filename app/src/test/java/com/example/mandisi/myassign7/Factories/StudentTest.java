package com.example.mandisi.myassign7.Factories;

import com.example.mandisi.myassign7.EntityObjects.Staffs;
import com.example.mandisi.myassign7.EntityObjects.Students;

import com.example.mandisi.myassign7.EntityObjectsFactories.StaffFactory;
import com.example.mandisi.myassign7.EntityObjectsFactories.StudentFactory;
import com.example.mandisi.myassign7.EntityObjectsFactories.StudentFactoryImp.StudentFactoryImp;
import com.example.mandisi.myassign7.ValuesObjects.Addrec;

import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;
/**
 * Created by 211014486 on 4/17/2016.
 */
public class StudentTest {
    private StudentFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = StudentFactoryImp.getInstance();
    }

    @Test
    public void studenttestRoleCreation() throws Exception {
        Students student = factory.createStudents(211L, 16, "mandisi");
        Assert.assertEquals(211L, student.getSID(),4.4);
        //Addrec addrec = factory.
       // Assert.assertEquals(student.getName(), "mandisi");
    }

    @Test
    public void testStudentUpdate() throws Exception {
        Students students = factory.createStudents(213L, 12, "Blou");
        // Updated Name

        Students updateStudent = new Students.Builder()
                .copy(students)
                .name("Ntobs")
                .build();

        //Assert.assertEquals(updateStudent.getSID(), "Ntobs");
        Assert.assertEquals("Ntobs", updateStudent.getName());
        //Assert.assertEquals(students.getYearOfBirth(), updateStudent.getYearOfBirth());
    }
}
