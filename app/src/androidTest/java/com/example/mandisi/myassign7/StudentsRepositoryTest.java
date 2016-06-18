package com.example.mandisi.myassign7;

import android.test.AndroidTestCase;

import com.example.mandisi.myassign7.EntityObjects.Students;
import com.example.mandisi.myassign7.StudentsRepository.StudentsRepositories;
import com.example.mandisi.myassign7.StudentsRepository.StudentsRepositoryImp.StudentsRepositoryImp;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Mandisi on 2016-04-23.
 */
public class StudentsRepositoryTest extends AndroidTestCase{
    private static final String TAG="STUDENTS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        StudentsRepositories repo = new StudentsRepositoryImp(this.getContext());
        // CREATE
        Students createEntity = new Students.Builder()
                .studentID(32563L)
                .yearOfBirth(16)
                .name("test12")
                .build();
        Students insertedEntity = repo.save(createEntity);
        id=insertedEntity.getSID();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Students> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Students entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Students updateEntity = new Students.Builder()
                .copy(entity)
                .name("TEST47")
                .build();
        repo.update(updateEntity);
        Students newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","TEST47",newEntity.getName());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Students deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
