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
        Staffs staffs = factory.createStaff("211", 16, "mandisi");
        Assert.assertEquals(staffs.getName(), "mandisi");
    }


    @Test
    public void testStudentUpdate() throws Exception {
        Staffs staffs = factory.createStaff("213", 12, "Blou");
       // Assert.assertEquals(staffs.getSID(), staffs.getYearOfBirth(), staffs.getName());

        // Updated Name

        Staffs updateStaffs = new Staffs.Builder()
                .copy(staffs)
                .name("Ntobs")
                .build();

        //Assert.assertEquals(updateStaffs.getSID(), "Ntobs");
        Assert.assertEquals("Ntobs", updateStaffs.getName());
       // Assert.assertEquals(staffs.getYearOfBirth(), updateStaffs.getYearOfBirth());
    }
}
