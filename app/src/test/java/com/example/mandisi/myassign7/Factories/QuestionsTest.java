package com.example.mandisi.myassign7.Factories;

/**
 * Created by 211014486 on 4/17/2016.
 */
import com.example.mandisi.myassign7.ValuesObjectFactories.QuestionsFactory;
import com.example.mandisi.myassign7.ValuesObjectFactories.QuestionsFatoryImp.QuestionsFatoryImp;
import com.example.mandisi.myassign7.ValuesObjects.Questions;

import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;
/**
 * Created by 211014486 on 4/17/2016.
 */
public class QuestionsTest {
    private QuestionsFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = QuestionsFatoryImp.getInstance();
    }

    @Test
    public void studenttestRoleCreation() throws Exception {
        Questions questions = factory.createQuestions("211","A","Question1", "Answer1");
        Assert.assertEquals(questions.getQuestionID(), "211");
    }


    @Test
    public void testResultsUpdate() throws Exception {
        Questions questions = factory.createQuestions("210","B","Question1", "Answer3");
        //Assert.assertEquals(questions.getQuestionID(), questions.getQuestionName(), questions.getQuestions());
        Assert.assertEquals(questions.getQuestionID(), "210");


        // Updated Name
        Questions updateQuestions = new Questions.Builder()
                .copy(questions)
                .questionID("Ntobs")
                .build();

        Assert.assertEquals(updateQuestions.getQuestionID(), "Ntobs");
        //Assert.assertEquals(questions.getQuestionName(), updateQuestions.getQuestionName());
        //Assert.assertEquals(questions.getQuestions(), updateQuestions.getQuestions());
    }
}

