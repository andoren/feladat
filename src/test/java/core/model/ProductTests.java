package core.model;

import core.exceptions.InvalidProductNameException;
import org.junit.Assert;
import org.junit.Before;
import core.exceptions.*;
import org.junit.Test;

import java.util.Date;

public class ProductTests {
    Product targetProduct;
    @Before
    public void Init() throws InvalidProductNameException, InvalidProductDescriptionException, InvalidImagePathException, InvalidProductPriceException {
        targetProduct = new Product(1,"Kiscica","Nagyon nyávóg, de szeretni való.",1000,false,false,"https://picsum.photos/200/300.jpg");

    }

    @Test
    public void ValidProductName() throws InvalidProductNameException {

        targetProduct.setName("Valami");
        String actual = targetProduct.getName();
        String expected = "Valami";
        Assert.assertEquals(expected, actual);
    }
    @Test(expected = InvalidProductNameException.class)
    public void EmptyProductName() throws InvalidProductNameException {
        targetProduct.setName("");
    }
    @Test(expected = InvalidProductNameException.class)
    public void LessLengthThanRequiredOneProductName() throws InvalidProductNameException {
        targetProduct.setName("a");
    }
    @Test(expected = InvalidProductNameException.class)
    public void LessLengthThanRequiredTwoProductName() throws InvalidProductNameException {
        targetProduct.setName("aa");
    }
    @Test(expected = InvalidProductNameException.class)
    public void LengthMoreThanValidFiftyProductName() throws InvalidProductNameException {
        targetProduct.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }
    @Test
    public void ValidDescriptiont() throws InvalidProductDescriptionException {

        targetProduct.setDescription("Valami hosszú szöveg, hogy jó legyen.");
        String actual = targetProduct.getDescription();
        String expected = "Valami hosszú szöveg, hogy jó legyen.";
        Assert.assertEquals(expected, actual);
    }
    @Test(expected = InvalidProductDescriptionException.class)
    public void EmptyDescription() throws InvalidProductDescriptionException {
        targetProduct.setDescription("");
    }
    @Test(expected = InvalidProductDescriptionException.class)
    public void LessLengthThanRequiredOneDescription() throws InvalidProductDescriptionException {
        targetProduct.setDescription("a");
    }
    @Test(expected = InvalidProductDescriptionException.class)
    public void LessLengthThanRequiredNineProductDescription() throws InvalidProductDescriptionException {
        targetProduct.setDescription("aaaaaaaaa");
    }
    @Test(expected = InvalidProductDescriptionException.class)
    public void LengthMoreThanValidDescription() throws InvalidProductDescriptionException {
        targetProduct.setDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }
    @Test(expected = InvalidImagePathException.class)
    public void InvalidImagepath() throws InvalidImagePathException {
        targetProduct.setImagepath("");
    }
    @Test(expected = InvalidProductPriceException.class)
    public void NegativePrice() throws InvalidProductPriceException {
        targetProduct.setPrice(-1);
    }
    @Test
    public void setIsAccapted(){
        targetProduct.setIsAccapted(true);
        boolean result = targetProduct.getIsAccapted();
        Assert.assertTrue(result);
    }
    @Test
    public void getIsAccapted(){
        targetProduct.setIsAccapted(true);
    }
    @Test
    public void setIsSold(){
        targetProduct.setIsSold(true);
        boolean result = targetProduct.getIssold();
        Assert.assertTrue(result);
    }


    @Test
    public void getAddress(){
        targetProduct.setBaddress(new Address());
        Address address = targetProduct.getBaddress();
        Assert.assertTrue(address != null);
    }
    @Test
    public void setSoldDate(){
        targetProduct.setSold_date(new Date());

        Assert.assertTrue(targetProduct.getSold_date() != null);
    }
    @Test
    public void setCreatedDate(){
        targetProduct.setCreated_date(new Date());

        Assert.assertTrue(targetProduct.getCreated_date() != null);
    }
    @Test
    public void setBuyer(){
        targetProduct.setBuyer(new User());

        Assert.assertTrue(targetProduct.getBuyer() != null);
    }
    @Test
    public void setOwner(){
        targetProduct.setOwner(new User());

        Assert.assertTrue(targetProduct.getOwner() != null);
    }
    @Test
    public void setId(){
        targetProduct.setId(1);

        Assert.assertTrue(targetProduct.getId() == 1);
    }
    @Test(expected = core.exceptions.InvalidImagePathException.class)
    public void setImagePath() throws InvalidImagePathException {
        targetProduct.setImagepath("kiscica");


    }
    @Test()
    public void setImagePathNoError() throws InvalidImagePathException {
        targetProduct.setImagepath("https://imagepath.jpg");
        Assert.assertTrue(targetProduct.getImagepath() != null);

    }
}