package com.example.mandisi.myassign7;

import android.test.AndroidTestCase;

import com.example.mandisi.myassign7.QuestionsRepository.QuestionsRepositories;
import com.example.mandisi.myassign7.QuestionsRepository.QuestionsRepositoryImp.QuestionsRepositoryImp;
import com.example.mandisi.myassign7.ValuesObjects.Questions;

import junit.framework.Assert;

import java.util.Set;

/**
 * Created by Mandisi on 2016-04-24.
 */
public class QuestionsRepositoryTest extends AndroidTestCase{
    private static final String TAG="QUESTIONS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        QuestionsRepositories repo = new QuestionsRepositoryImp(this.getContext());
        // CREATE
        Questions createEntity = new Questions.Builder()
                .questionID(848L)
                .questionName("B")
                .questions("B")
                .corrects("8")
                .build();
        Questions insertedEntity = repo.save(createEntity);
        id=insertedEntity.getQuestionID();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Questions> settings = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",settings.size()>0);

        //READ ENTITY
        Questions entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Questions updateEntity = new Questions.Builder()
                .copy(entity)
                .questionName("TEST47")
                .build();
        repo.update(updateEntity);
        Questions newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","TEST47",newEntity.getQuestionName());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Questions deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
