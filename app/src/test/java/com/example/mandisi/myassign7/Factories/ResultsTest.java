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
        Results results = factory.createQuestions(214L,"A","Question1", "Answer1");
        Assert.assertEquals(214L, results.getResultsID(),4.4);
    }


    @Test
    public void testResultsUpdate() throws Exception {
        Results results = factory.createQuestions(214L,"B","Question1", "Answer3");
        Assert.assertEquals(214L, results.getResultsID(),4.4);

        // Updated Name
        Results updateResults = new Results.Builder()
                .copy(results)
                .resultsID(215L)
                .build();

        Assert.assertEquals(215L, updateResults.getResultsID(),4.4);
        //System.out.print(""+updateResults.getResultsID());
       // Assert.assertEquals(results.getQuestionName(), updateResults.getQuestionName());
       // Assert.assertEquals(results.getQuestions(), updateResults.getQuestions());
       //Assert.assertEquals(results.getCorrects(), updateResults.getCorrects());
    }
}

