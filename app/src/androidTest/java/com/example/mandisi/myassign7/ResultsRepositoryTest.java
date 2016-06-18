package com.example.mandisi.myassign7;

import android.test.AndroidTestCase;

import com.example.mandisi.myassign7.EventsObjects.Results;
import com.example.mandisi.myassign7.ResultsRepository.ResultsRepositories;
import com.example.mandisi.myassign7.ResultsRepository.ResultsRepositoryImp.ResultsRepositoryImp;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Mandisi on 2016-04-24.
 */
public class ResultsRepositoryTest extends AndroidTestCase{
    private static final String TAG="RESULTS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        ResultsRepositories repo = new ResultsRepositoryImp(this.getContext());
        // CREATE
        Results createEntity = new Results.Builder()
                .resultsID(4125L)
                .questionName("A")
                .questions("2")
                .corrects("16")
                .build();
        Results insertedEntity = repo.save(createEntity);
        id=insertedEntity.getResultsID();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Results> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Results entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Results updateEntity = new Results.Builder()
                .copy(entity)
                .questionName("TEST47")
                .build();
        repo.update(updateEntity);
        Results newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","TEST47",newEntity.getQuestionName());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Results deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
