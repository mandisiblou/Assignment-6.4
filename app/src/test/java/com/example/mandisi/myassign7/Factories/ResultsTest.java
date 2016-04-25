package com.example.mandisi.myassign7.Factories;

/**
 * Created by 211014486 on 4/17/2016.
 */
import com.example.mandisi.myassign7.EventsObjectFactories.ResultsFactory;
import com.example.mandisi.myassign7.EventsObjectFactories.ResultsFatoryImp.ResultsFatoryImp;
import com.example.mandisi.myassign7.EventsObjects.Results;

import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;
/**
 * Created by 211014486 on 4/17/2016.
 */
public class ResultsTest {
    private ResultsFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = ResultsFatoryImp.getInstance();
    }

    @Test
    public void studenttestRoleCreation() throws Exception {
        Results results = factory.createQuestions("ADMIN","A","Question1", "Answer1");
        Assert.assertEquals(results.getResultsID(), "ADMIN");
    }


    @Test
    public void testResultsUpdate() throws Exception {
        Results results = factory.createQuestions("210","B","Question1", "Answer3");
        //Assert.assertEquals(results.getResultsID(), results.getQuestionName(), results.getQuestions());

        // Updated Name
        Results updateResults = new Results.Builder()
                .copy(results)
                .resultsID("Ntobs")
                .build();

        Assert.assertEquals(updateResults.getResultsID(), "Ntobs");
        //System.out.print(""+updateResults.getResultsID());
       // Assert.assertEquals(results.getQuestionName(), updateResults.getQuestionName());
       // Assert.assertEquals(results.getQuestions(), updateResults.getQuestions());
       //Assert.assertEquals(results.getCorrects(), updateResults.getCorrects());
    }
}

