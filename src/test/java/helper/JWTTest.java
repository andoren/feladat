package helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import core.exceptions.InvalidEmailException;
import core.exceptions.InvalidPassword;
import core.exceptions.InvalidRealnameException;
import core.exceptions.InvalidUsernameException;
import core.model.Role;
import core.model.User;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;

public class JWTTest {
    IJWTHelper JWTHelper = new JWTHelper();
    @Test(expected = JWTVerificationException.class)
    public void errorDecodeJWT() throws InvalidUsernameException, InvalidRealnameException, InvalidEmailException {
        JWTHelper.decodeJWT("12345678");
    }
    @Test()
    public void validEncodeJWT() throws InvalidUsernameException, InvalidRealnameException, InvalidEmailException, InvalidPassword {
        User validuser = new User();
        validuser.setId(1);
        validuser.setUsername("testsub");
        validuser.setPassword("ValidPassword&");
        validuser.setRealname("Test Subject");
        validuser.setEmail("valid@localhost.hu");
        validuser.setRole(Role.user);
        String token = JWTHelper.generateJWT(validuser);
        User user = JWTHelper.decodeJWT(token);

        Assert.assertEquals(user.getId(),validuser.getId());
    }
    @Test()
    public void generateJWT() throws InvalidUsernameException, InvalidRealnameException, InvalidEmailException, InvalidPassword {
        User validuser = new User();
        validuser.setId(1);
        validuser.setUsername("testsub");
        validuser.setPassword("ValidPassword&");
        validuser.setRealname("Test Subject");
        validuser.setEmail("valid@localhost.hu");
        validuser.setRole(Role.user);
        String token = JWTHelper.generateJWT(validuser);


        Assert.assertTrue(token.length() >2);
    }

}
