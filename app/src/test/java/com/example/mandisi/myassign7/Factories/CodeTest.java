package com.example.mandisi.myassign7.Factories;

/**
 * Created by 211014486 on 4/17/2016.
 */
import com.example.mandisi.myassign7.ValuesObjectFactories.CodeFactory;
import com.example.mandisi.myassign7.ValuesObjectFactories.CodeFactoryImp.CodeFactoryImp;
import com.example.mandisi.myassign7.ValuesObjectFactories.QuestionsFatoryImp.QuestionsFatoryImp;
import com.example.mandisi.myassign7.ValuesObjects.Code;

import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;
/**
 * Created by 211014486 on 4/17/2016.
 */
public class CodeTest {
    private CodeFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = CodeFactoryImp.getInstance();
    }

    @Test
    public void studenttestRoleCreation() throws Exception {
        Code code = factory.createCode("211", "A");
        Assert.assertEquals(code.getCodeId(), "211");
    }


    @Test
    public void testResultsUpdate() throws Exception {
        Code code = factory.createCode("210", "B");
        Assert.assertEquals(code.getCodeId(), "210");

        // Updated Name
        Code updateCode = new Code.Builder()
                .copy(code)
                .codeId("Ntobs")
                .build();

        Assert.assertEquals(updateCode.getCodeId(), "Ntobs");
       // Assert.assertEquals(code.getName(), updateCode.getName());
    }
}
