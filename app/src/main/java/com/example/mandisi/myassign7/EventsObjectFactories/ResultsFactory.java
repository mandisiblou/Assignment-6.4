package com.example.mandisi.myassign7.EventsObjectFactories;

import com.example.mandisi.myassign7.EventsObjects.Results;

/**
 * Created by 211014486 on 4/17/2016.
 */
public interface ResultsFactory {
    Results createQuestions(Long resultsID, String questionName, String questions, String corrects);
}