package com.example.mandisi.myassign7;

import android.test.AndroidTestCase;

import com.example.mandisi.myassign7.CodeRepository.CodeRepositories;
import com.example.mandisi.myassign7.CodeRepository.CodeRepositoryImp.CodeRepositoryImp;
import com.example.mandisi.myassign7.ValuesObjects.Code;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Mandisi on 2016-04-24.
 */
public class CodeRepositoryTest extends AndroidTestCase{
    private static final String TAG="CODE TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        CodeRepositories repo = new CodeRepositoryImp(this.getContext());
        // CREATE
        Code createEntity = new Code.Builder()
                .codeId(3000000L)
                .name("test12")
                .build();
        Code insertedEntity = repo.save(createEntity);
        id=insertedEntity.getCodeId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Code> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Code entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Code updateEntity = new Code.Builder()
                .copy(entity)
                .name("TEST47")
                .build();
        repo.update(updateEntity);
        Code newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","TEST47",newEntity.getName());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Code deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
