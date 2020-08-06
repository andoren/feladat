package core.model;

import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AddressTest {
    @TestSubject
    Address address;
    @Before
    public void init(){
        address = new Address();
    }
    @Test
    public void setAddressCountry(){
        address.setCountry("Magyarország");
        Assert.assertEquals(address.getCountry(),"Magyarország");
    }
    @Test
    public void setAddressCounty(){
        address.setCounty("Heves");
        Assert.assertEquals(address.getCounty(),"Heves");
    }
    @Test
    public void setAddressVillage(){
        address.setVillage("Szarvas");
        Assert.assertEquals(address.getVillage(),"Szarvas");
    }
    @Test
    public void setAddressZip(){
        address.setZip(5540);
        Assert.assertEquals(address.getZip(),5540);
    }
    @Test
    public void setAddressStreet(){
        address.setStreet("Tessedik");
        Assert.assertEquals(address.getStreet(),"Tessedik");
    }
    @Test
    public void setAddressNumber(){
        address.setNumber("55");
        Assert.assertEquals(address.getNumber(),"55");
    }
    @Test
    public void setUserId(){
        address.setUserid(new User());
        Assert.assertNotEquals(address.getUserid(),null);
    }
    @Test
    public void addressParamConstructorTest(){
        Address address = new Address("Magyarország","Heves",5540,"Eger","Kis Pali utca","13");
    }
}
