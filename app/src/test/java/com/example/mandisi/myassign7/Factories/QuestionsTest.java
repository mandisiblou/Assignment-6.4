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
        Questions questions = factory.createQuestions(213L,"A","Question1", "Answer1");
         Assert.assertEquals(213L, questions.getQuestionID(),4.4);
    }


    @Test
    public void testResultsUpdate() throws Exception {
        Questions questions = factory.createQuestions(213L,"B","Question1", "Answer3");
        //Assert.assertEquals(questions.getQuestionID(), questions.getQuestionName(), questions.getQuestions());
        Assert.assertEquals(213L, questions.getQuestionID(),4.4);


        // Updated Name
        Questions updateQuestions = new Questions.Builder()
                .copy(questions)
                .questionID(214L)
                .build();

        Assert.assertEquals(214L, updateQuestions.getQuestionID(),4.4);
        //Assert.assertEquals(questions.getQuestionName(), updateQuestions.getQuestionName());
        //Assert.assertEquals(questions.getQuestions(), updateQuestions.getQuestions());
    }
}

