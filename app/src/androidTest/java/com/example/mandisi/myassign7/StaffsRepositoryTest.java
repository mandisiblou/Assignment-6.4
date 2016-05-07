package com.example.mandisi.myassign7;

import android.test.AndroidTestCase;

import com.example.mandisi.myassign7.EntityObjects.Staffs;
import com.example.mandisi.myassign7.StaffsRepository.StaffsRepositories;
import com.example.mandisi.myassign7.StaffsRepository.StaffsRepositoryImp.StaffsRepositoryImp;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Nkuli on 2016-04-23.
 */
public class StaffsRepositoryTest extends AndroidTestCase {
    private static final String TAG="STAFFS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        StaffsRepositories repo = new StaffsRepositoryImp(this.getContext());
        // CREATE
        Staffs createEntity = new Staffs.Builder()
                .SID(545L)
                .yearOfBirth(16)
                .name("test12")
                .build();
        Staffs insertedEntity = repo.save(createEntity);
        id=insertedEntity.getSID();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Staffs> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Staffs entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Staffs updateEntity = new Staffs.Builder()
                .copy(entity)
                .name("TEST47")
                .build();
        repo.update(updateEntity);
        Staffs newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","TEST47",newEntity.getName());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Staffs deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
