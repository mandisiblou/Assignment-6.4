package com.example.mandisi.myassign7.Factories;

/**
 * Created by Nkuli on 2016-06-09.
 */
import com.example.mandisi.myassign7.Factories.Person.PersonFactory;
import com.example.mandisi.myassign7.Persons.Persons;
import com.example.mandisi.myassign7.conf.util.DomainState;

import junit.framework.Assert;

import org.junit.Test;

import com.example.mandisi.myassign7.conf.util.DomainState;



/**
 * Created by 211014486 on 4/17/2016.
 */
public class PersonsTest {
    @Test
    public void testCreate() throws Exception {
        //PersonFactory.getPerson("email","lastname","password");

        Persons personFactory = PersonFactory.getPerson("terra.gwaza@gmail.com","gwaza","0203");
        Assert.assertEquals("gwaza",personFactory.getLastName());

    }

    @Test
    public void testUpdate() throws Exception {
        Persons personFactory = PersonFactory.getPerson("rasta@gmail.com","Zide","0205");
        Persons newAddressType = new Persons
                .Builder()
                .copy(personFactory)
                .lastName("Zide")
                .build();
        Assert.assertEquals("Zide",newAddressType.getLastName());
        //Assert.assertEquals(DomainState.ACTIVE.name(),newAddressType.getState());
    }
}
