package controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import core.exceptions.*;
import core.model.Product;
import core.model.Role;
import core.model.User;
import core.service.IProductSerivce;
import core.service.IUserService;
import helper.IJWTHelper;
import org.easymock.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import javax.ws.rs.core.Response;

import static org.easymock.EasyMock.same;


@RunWith(EasyMockRunner.class)
public class ProtectedEndPointsTest {
    @TestSubject
    ProtectedEndpoints testSubject = new ProtectedEndpoints();;
    @Mock(type = MockType.STRICT, name = "userService", fieldName = "userService")
    private IUserService userService;
    @Mock(type = MockType.STRICT, name = "productService", fieldName = "productService")
    private IProductSerivce productService;
    @Mock(type = MockType.STRICT, name = "JWTHelper", fieldName = "JWTHelper")
    private IJWTHelper jwthHelper;
    Product goodProduct;
    User goodUser;
    @Before
    public void InitData() throws InvalidProductNameException, InvalidProductDescriptionException, InvalidImagePathException, InvalidProductPriceException, InvalidRealnameException, InvalidUsernameException, InvalidPassword, InvalidEmailException {
        goodProduct =  new Product(1,"Eladó macska","Lorem ipsum jo volna ide de nincs kedvem kiváltani az IDE-ből :=(",100,false,false,"https://picsum.photos/200/300.jpg");
        goodUser = new User();
        goodUser.setId(1);
        goodUser.setRole(Role.user);
        goodUser.setEmail("mpekar55@gmail.com");
        goodUser.setPassword("TestFeladat2020");
        goodUser.setUsername("testsubject");
        goodUser.setRealname("Test Subject");
        goodProduct.setOwner(goodUser);
        EasyMock.expect(jwthHelper.decodeJWT(same(""))).andThrow(new JWTVerificationException("")).anyTimes();
        EasyMock.expect(productService.authProduct(same(goodProduct))).andThrow(new JWTVerificationException("")).anyTimes();
        EasyMock.replay(productService);
    }
    @Test(expected = JWTVerificationException.class)
    public void invalidTokenOnAuthProduct(){
        Response badresponse = testSubject.authProduct("",goodProduct);

    }
}
