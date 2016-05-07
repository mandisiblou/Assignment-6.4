package com.example.mandisi.myassign7.Factories;

/**
 * Created by 211014486 on 4/17/2016.
 */
import com.example.mandisi.myassign7.EntityObjects.Staffs;
import com.example.mandisi.myassign7.EntityObjectsFactories.StaffFactory;
import com.example.mandisi.myassign7.EntityObjectsFactories.StaffFactoryImp.StaffFactoryImp;

import org.junit.Before;
import org.junit.Test;
import junit.framework.Assert;
/**
 * Created by 211014486 on 4/17/2016.
 */
public class StaffsTest {
    private StaffFactory factory;

    @Before
    public void setUp() throws Exception {
        factory = StaffFactoryImp.getInstance();
    }

    @Test
    public void studenttestRoleCreation() throws Exception {
        Staffs staffs = factory.createStaff(215L, 16, "mandisi");
        Assert.assertEquals(215L, staffs.getSID(),4.4);
    }


    @Test
    public void testStudentUpdate() throws Exception {
        Staffs staffs = factory.createStaff(213L, 12, "Blou");
        Assert.assertEquals(213L, staffs.getSID(),4.4);

        // Updated Name

        Staffs updateStaffs = new Staffs.Builder()
                .copy(staffs)
                .SID(216L)
                .build();

        //Assert.assertEquals(updateStaffs.getSID(), "Ntobs");
        Assert.assertEquals(216L, updateStaffs.getSID(),4.4);
       // Assert.assertEquals(staffs.getYearOfBirth(), updateStaffs.getYearOfBirth());
    }
}
