package com.example.mandisi.myassign7;

/**
 * Created by Nkuli on 2016-06-09.
 */
import android.test.AndroidTestCase;

import com.example.mandisi.myassign7.Persons.Persons;
import com.example.mandisi.myassign7.Repositories.Person.Imp.PersonRepositoryImpl;
import com.example.mandisi.myassign7.Repositories.Person.PersonRepository;

import junit.framework.Assert;


import java.util.Set;


/**
 * Created by Mandisi on 2016/04/09.
 */
public class PersonTest  extends AndroidTestCase {
    private static final String TAG="PERSON TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        PersonRepository repo = new PersonRepositoryImpl(this.getContext());
        // CREATE
        Persons createEntity = new Persons.Builder()
                .serverId("FDCD")
                .firstName("Thembile")
                .emailAddress("mandisiblou@gmail.com")
                .lastName("Blou")
                .authvalue("tera1")
                .organisation("st.peters")
                .token("rasta")
                .build();
        Persons insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Persons> persons = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",persons.size()>0);

        //READ ENTITY
        Persons entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Persons updateEntity = new Persons.Builder()
                .copy(entity)
                .firstName("Mandisi")
                .build();
        repo.update(updateEntity);
        Persons newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","TEST47",newEntity.getFirstName());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Persons deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
