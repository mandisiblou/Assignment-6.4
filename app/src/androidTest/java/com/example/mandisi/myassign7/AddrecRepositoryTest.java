package com.example.mandisi.myassign7;

import android.test.AndroidTestCase;

import com.example.mandisi.myassign7.AddrecRepository.AddrecRepositories;
import com.example.mandisi.myassign7.AddrecRepository.AddrecRepositoryImp.AddrecRepositoryImp;
import com.example.mandisi.myassign7.ValuesObjects.Addrec;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Mandisi on 2016-04-24.
 */
public class AddrecRepositoryTest extends AndroidTestCase{
    private static final String TAG="ADDRESS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        AddrecRepositories repo = new AddrecRepositoryImp(this.getContext());
        // CREATE
        Addrec createEntity = new Addrec.Builder()
                .addressId(38128925L)
                .street("anzio")
                .town("obs")
                .postCode("7925")
                .country("ghana")
                .build();
        Addrec insertedEntity = repo.save(createEntity);
        id=insertedEntity.getAddressId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Addrec> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Addrec entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Addrec updateEntity = new Addrec.Builder()
                .copy(entity)
                .street("TEST47")
                .build();
        repo.update(updateEntity);
        Addrec newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","TEST47",newEntity.getStreet());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Addrec deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
