package com.example.mandisi.myassign7.Factories;

import com.example.mandisi.myassign7.EntityObjects.Person;
import com.example.mandisi.myassign7.EntityObjectsFactories.PersonFactory;
import com.example.mandisi.myassign7.EntityObjectsFactories.PersonFactoryImp.PersonFactoryImp;
import com.example.mandisi.myassign7.EntityObjectsFactories.StudentFactoryImp.StudentFactoryImp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by 211014486 on 4/17/2016.
 */
public class PersonTest {
    private PersonFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = PersonFactoryImp.getInstance();
    }

    @Test
    public void studenttestRoleCreation() throws Exception {
        Person person = factory.createPerson(211L, "mandisi", 32);
        Assert.assertEquals(211L, person.getId(),4.4);
    }


    @Test
    public void testStudentUpdate() throws Exception {
        Person person = factory.createPerson(213L, "Blou", 24);
        Assert.assertEquals(213L, person.getId(),4.4);

        // Updated Name

        Person updatePerson = new Person.Builder()
                .copy(person)
                .name("Ntobs")
                .build();

        //Assert.assertEquals(updatePerson.getId(), "Ntobs");
        Assert.assertEquals("Ntobs", updatePerson.getName());
       // Assert.assertEquals(person.getYearOfBirth(), updatePerson.getYearOfBirth());
    }
}