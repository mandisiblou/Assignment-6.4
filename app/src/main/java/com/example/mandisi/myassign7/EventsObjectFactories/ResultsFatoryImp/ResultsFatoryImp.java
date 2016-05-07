package com.example.mandisi.myassign7.EventsObjectFactories.ResultsFatoryImp;

/**
 * Created by 211014486 on 4/17/2016.
 */
import com.example.mandisi.myassign7.EventsObjectFactories.ResultsFactory;
import com.example.mandisi.myassign7.EventsObjects.Results;

import java.util.UUID;

/**
 * Created by Nkuli on 2016-04-09.
 */
public class ResultsFatoryImp implements ResultsFactory{
    private static ResultsFatoryImp factory = null;

    private  ResultsFatoryImp() {
    }
    public static ResultsFatoryImp getInstance(){
        if(factory ==null)
            factory = new ResultsFatoryImp();
        return factory;
    }
    public Results createQuestions(Long resultsID, String questionName, String questions, String corrects) {
        Results  questionz = new Results
                .Builder()
                .resultsID(resultsID)
                .questionName(questionName)
                .questions(questions)
                .corrects(corrects)
                .build();
        return questionz;
    }


}
