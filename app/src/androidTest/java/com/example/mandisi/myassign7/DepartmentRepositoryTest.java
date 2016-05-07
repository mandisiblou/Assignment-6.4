package com.example.mandisi.myassign7;

import android.test.AndroidTestCase;

import com.example.mandisi.myassign7.DepartmentRepository.DepartmentRepositories;
import com.example.mandisi.myassign7.DepartmentRepository.DepartmentRepositoryImp.DepartmentRepositoryImp;
import com.example.mandisi.myassign7.ValuesObjects.Department;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Nkuli on 2016-04-24.
 */
public class DepartmentRepositoryTest extends AndroidTestCase{
    private static final String TAG="DEPARTMENT TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        DepartmentRepositories repo = new DepartmentRepositoryImp(this.getContext());
        // CREATE
        Department createEntity = new Department.Builder()
                .SID(236L)
                .name("test12")
                .build();
        Department insertedEntity = repo.save(createEntity);
        id=insertedEntity.getSID();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Department> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Department entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Department updateEntity = new Department.Builder()
                .copy(entity)
                .name("TEST47")
                .build();
        repo.update(updateEntity);
        Department newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","TEST47",newEntity.getName());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Department deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
