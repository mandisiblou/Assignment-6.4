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
        Person person = factory.createPerson("ADMIN", "mandisi", 32);
        //Assert.assertEquals(person.getId(), person.getName(),person.getYearOfBirth());
        //Assert.assertEquals("ADMIN",person.getId());
        Assert.assertEquals("mandisi", person.getName());
    }


    @Test
    public void testStudentUpdate() throws Exception {
        Person person = factory.createPerson("213", "Blou", 24);
        //Assert.assertEquals(person.getId(), person.getYearOfBirth(), person.getName());

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