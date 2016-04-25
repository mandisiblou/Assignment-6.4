package com.example.mandisi.myassign7;


/**
 * Created by 211014486 on 4/17/2016.
 */
import com.example.mandisi.myassign7.Factories.AddrecTest;
import com.example.mandisi.myassign7.Factories.CodeTest;
import com.example.mandisi.myassign7.Factories.DepartmentTest;
import com.example.mandisi.myassign7.Factories.PersonTest;
import com.example.mandisi.myassign7.Factories.QuestionsTest;
import com.example.mandisi.myassign7.Factories.ResultsTest;
import com.example.mandisi.myassign7.Factories.StaffsTest;
import com.example.mandisi.myassign7.Factories.StudentTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({

        StudentTest.class,
        PersonTest.class,
        QuestionsTest.class,
        AddrecTest.class,
        DepartmentTest.class,
        CodeTest.class,
        StaffsTest.class,
        ResultsTest.class})
public class AppUnitTestSuite {}
