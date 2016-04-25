package com.example.mandisi.myassign7.Factories;

/**
 * Created by 211014486 on 4/17/2016.
 */
import com.example.mandisi.myassign7.ValuesObjectFactories.AddrecFactory;
import com.example.mandisi.myassign7.ValuesObjectFactories.AddrecFactoryImp.AddrecFactoryImp;
import com.example.mandisi.myassign7.ValuesObjects.Addrec;

import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;
/**
 * Created by 211014486 on 4/17/2016.
 */
public class AddrecTest {
    private AddrecFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = AddrecFactoryImp.getInstance();
    }

    @Test
    public void studenttestRoleCreation() throws Exception {
        Addrec addrec = factory.createAddrec("211", "Kwezi", "Cape Town", "7784", "S.A");
        Assert.assertEquals(addrec.getAddressId(), "211");
    }


    @Test
    public void testResultsUpdate() throws Exception {
        Addrec addrec = factory.createAddrec("211", "Kwezi", "Cape Town", "8000", "S.A");
        //Assert.assertEquals(addrec.getAddressId(), addrec.getTown(), addrec.getPostCode());
        Assert.assertEquals("Cape Town", addrec.getTown());

        // Updated Name
          Addrec updateAddrec = new Addrec.Builder()
                .copy(addrec)
                .addressId("Ntobs")
                .build();

        Assert.assertEquals(updateAddrec.getAddressId(), "Ntobs");
       // Assert.assertEquals(addrec.getTown(), updateAddrec.getTown());
       // Assert.assertEquals(addrec.getPostCode(), updateAddrec.getPostCode());
       // Assert.assertEquals(addrec.getStreet(), updateAddrec.getStreet());
       // Assert.assertEquals(addrec.getCountry(), updateAddrec.getCountry());
    }
}

