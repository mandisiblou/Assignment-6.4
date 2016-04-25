package com.example.mandisi.myassign7;

import android.test.AndroidTestCase;

import com.example.mandisi.myassign7.EntityObjects.Person;
import com.example.mandisi.myassign7.PersonRepository.PersonRepositories;
import com.example.mandisi.myassign7.PersonRepository.PersonRepositoryImp.PersonRepositoryImpl;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Nkuli on 2016-04-23.
 */
public class PersonRepositoryTest extends AndroidTestCase {
    private static final String TAG="PERSON TEST";
    private String id;

    public void testCreateReadUpdateDelete() throws Exception {
        PersonRepositories repo = new PersonRepositoryImpl(this.getContext());
        // CREATE
        Person createEntity = new Person.Builder()
                .id("FDCD")
                .name("test12")
                .yearOfBirth(16)
                .build();
        Person insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Person> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Person entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Person updateEntity = new Person.Builder()
                .copy(entity)
                .name("TEST47")
                .build();
        repo.update(updateEntity);
        Person newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","TEST47",newEntity.getName());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Person deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
