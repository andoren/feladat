package core.model;

import core.exceptions.*;
import org.junit.Before;
import org.junit.Test;

public class UserTests {
    User user;

    @Before
    public void Init() throws InvalidUsernameException, InvalidEmailException, InvalidPassword, InvalidRealnameException {
        user = new User(1, "kiscica", "Feladat2020#", "Pekár Mihály", "mpekar55@gmail.com", Role.admin);
    }

    @Test
    public void ValidPassowrdUser() throws InvalidPassword {
        user.setPassword("Feladat2020#");
    }

    @Test(expected = InvalidPassword.class)
    public void TooShortPassword() throws InvalidPassword {
        user.setPassword("#Aa2");
    }

    @Test(expected = InvalidPassword.class)
    public void TooLongPassword() throws InvalidPassword {
        user.setPassword("a#2A0aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa2A0aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa2A0aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test
    public void ValidUsername() throws InvalidUsernameException {
        user.setUsername("kiscica");
    }

    @Test(expected = InvalidUsernameException.class)
    public void EmptyUsername() throws InvalidUsernameException {
        user.setUsername("");
    }

    @Test(expected = InvalidUsernameException.class)
    public void NullUsername() throws InvalidUsernameException {
        user.setUsername(null);
    }

    @Test(expected = InvalidUsernameException.class)
    public void TooLongUsername() throws InvalidUsernameException {
        user.setUsername("aaaaaaaaaaaaaaaaaaaaa");
    }

    @Test
    public void TwentyLongUsername() throws InvalidUsernameException {
        user.setUsername("aaaaaaaaaaaaaaaaaaaa");
    }

    @Test
    public void FiveLongUsername() throws InvalidUsernameException {
        user.setUsername("aaaaa");
    }

    @Test(expected = InvalidUsernameException.class)
    public void FourLongUsername() throws InvalidUsernameException {
        user.setUsername("aaaa");
    }

    @Test
    public void ValidRealName() throws InvalidRealnameException {
        user.setRealname("Pekár Mihály");
    }

    @Test(expected = InvalidRealnameException.class)
    public void EmptyRealName() throws InvalidRealnameException {
        user.setRealname("");
    }

    @Test(expected = InvalidRealnameException.class)
    public void NullRealName() throws InvalidRealnameException {
        user.setRealname(null);
    }

    @Test(expected = InvalidRealnameException.class)
    public void ShortRealName() throws InvalidRealnameException {
        user.setRealname("1234");
    }

    @Test(expected = InvalidRealnameException.class)
    public void LongRealName() throws InvalidRealnameException {
        user.setRealname("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
    }

    @Test(expected = InvalidRealnameException.class)
    public void NoSpaceRealName() throws InvalidRealnameException {
        user.setRealname("MeowMeow");
    }

    @Test()
    public void ValidEmail() throws InvalidEmailException {
        user.setEmail("mpekar55@gmail.com");
    }

    @Test(expected = InvalidEmailException.class)
    public void EmptyEmail() throws InvalidEmailException {
        user.setEmail("");
    }

    @Test(expected = InvalidEmailException.class)
    public void NullEmail() throws InvalidEmailException {
        user.setEmail(null);
    }

    @Test(expected = InvalidEmailException.class)
    public void NoAtSymbolEmail() throws InvalidEmailException {
        user.setEmail("mpekar55gmail.com");
    }
}
