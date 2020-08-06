package dao.imp;

import core.exceptions.*;
import core.model.Product;
import core.model.Role;
import core.model.User;
import core.service.IProductSerivce;
import org.easymock.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import service.dao.IProductDAO;
import service.dao.IUserDAO;
import service.impl.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.easymock.EasyMock.same;

@RunWith(EasyMockRunner.class)
public class ProductServiceTest {


    Collection<Product> dummyDB;
    Collection<Product> noAuthdummyDB;
    Product goodProduct;
    Product productWithoutAnything;
    User user;
    @Mock(type = MockType.STRICT, name = "productDao", fieldName = "dao")
    private IProductDAO productDao;


    @TestSubject
    private IProductSerivce service = new ProductService();

    @Before
    public void init() throws InvalidProductDescriptionException, InvalidImagePathException, InvalidProductNameException, InvalidProductPriceException, InvalidEmailException, InvalidPassword, InvalidUsernameException, InvalidRealnameException {
        goodProduct = new Product();
        user = new User();
        user.setId(1);
        user.setRole(Role.user);
        user.setEmail("mpekar55@gmail.com");
        user.setPassword("TestFeladat2020");
        user.setUsername("testsubject");
        user.setRealname("Test Subject");
        goodProduct.setOwner(user);
        productWithoutAnything = new Product();
        dummyDB = Arrays.asList(
                new Product(1,"Eladó macska","Lorem ipsum jo volna ide de nincs kedvem kiváltani az IDE-ből :=(",100,false,false,"https://picsum.photos/200/300.jpg"),
                new Product(3,"Eladó macska","Lorem ipsum jo volna ide de nincs kedvem kiváltani az IDE-ből :=(",100,false,false,"https://picsum.photos/200/300.jpg"),
                new Product(4,"Eladó macska","Lorem ipsum jo volna ide de nincs kedvem kiváltani az IDE-ből :=(",100,false,false,"https://picsum.photos/200/300.jpg")
        );
        noAuthdummyDB = Arrays.asList(
                new Product(2,"Eladó macska","Lorem ipsum jo volna ide de nincs kedvem kiváltani az IDE-ből :=(",100,false,false,"https://picsum.photos/200/300.jpg")
        );
        EasyMock.expect(productDao.addProduct(same(goodProduct))).andReturn(goodProduct).anyTimes();
        EasyMock.expect(productDao.deleteProductById(1)).andReturn(true).anyTimes();
        EasyMock.expect(productDao.getNotAuthorizedProducts()).andReturn(noAuthdummyDB).anyTimes();

        EasyMock.expect(productDao.getProductsByUserId(same(user))).andReturn(Arrays.asList(goodProduct)).anyTimes();
        EasyMock.expect(productDao.getProducts()).andReturn(dummyDB).anyTimes();
        EasyMock.expect(productDao.modifyProduct(same(goodProduct))).andReturn(true).anyTimes();
        EasyMock.expect(productDao.getProductById(same(1))).andReturn(goodProduct).anyTimes();
        EasyMock.expect(productDao.authProduct(same(goodProduct))).andReturn(true).anyTimes();
        EasyMock.expect(productDao.buyProduct(same(goodProduct),same(user))).andReturn(true).anyTimes();
        EasyMock.expect(productDao.getAuthorizedProducts()).andReturn(dummyDB).anyTimes();
        EasyMock.replay(productDao);
    }
    @Test
    public void addProduct(){
        Product product = service.addProduct(goodProduct);
        Assert.assertEquals(product,goodProduct);

    }
    @Test
    public void deleteProductById(){
        boolean result =  service.deleteProductById(1);
        Assert.assertEquals(true,result);
    }
    @Test
    public void getNotAuthorizedProducts(){
        Collection<Product> noauth = service.getNotAuthorizedProducts();
        Assert.assertEquals(noAuthdummyDB.size(),noauth.size());
    }
    @Test
    public void getProducts(){
        Collection<Product> products =  service.getProducts();
        Assert.assertEquals(dummyDB,products);
    }
    @Test
    public void getProductsByUserId(){
        Collection<Product> userProducts =  service.getProductsByUserId(user);
        Assert.assertEquals(1,userProducts.size());
    }
    @Test
    public void modifyPrdocut(){
        boolean result =  service.modifyProduct(goodProduct);
        Assert.assertEquals(result,true);
    }
    @Test
    public void getProductById(){
        Product product = service.getProductById(1);
        Assert.assertEquals(goodProduct,product);
    }
    @Test
    public void authProduct(){
        boolean result  =  service.authProduct(goodProduct);
        Assert.assertEquals(true,result);
    }
    @Test
    public void buyProduct(){
        boolean result =  service.buyProduct(goodProduct,user);
        Assert.assertTrue(result);
    }
    @Test
    public void getAuthorizedProducts(){
        Collection<Product> product = service.getAuthorizedProducts();
        Assert.assertEquals(3,product.size());
    }
}