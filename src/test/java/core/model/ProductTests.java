package core.model;

import core.exceptions.InvalidProductNameException;
import org.junit.Assert;
import org.junit.Before;
import core.exceptions.*;
import org.junit.Test;

public class ProductTests {
    Product targetProduct;
    @Before
    public void Init() throws InvalidProductNameException, InvalidProductDescriptionException, InvalidImagePathException, InvalidProductPriceException {
        targetProduct = new Product(1,"Kiscica","Nagyon nyávóg, de szeretni való.",1000,false,false,"C:\\cica.jpg");

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
    public void LengthMoreThanValidTwentyProductName() throws InvalidProductNameException {
        targetProduct.setName("aaaaaaaaaaaaaaaaaaaaa");
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
    public void LengthMoreThanValidTwentyDescription() throws InvalidProductDescriptionException {
        targetProduct.setDescription("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }
    @Test(expected = InvalidImagePathException.class)
    public void InvalidImagepath() throws InvalidImagePathException {
        targetProduct.setImagepath("");
    }
    @Test(expected = InvalidProductPriceException.class)
    public void NegativePrice() throws InvalidProductPriceException {
        targetProduct.setPrice(-1);
    }
}