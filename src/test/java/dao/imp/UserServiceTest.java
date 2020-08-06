package dao.imp;

import core.exceptions.InvalidEmailException;
import core.exceptions.InvalidPassword;
import core.exceptions.InvalidRealnameException;
import core.exceptions.InvalidUsernameException;
import core.model.Role;
import core.model.User;
import org.easymock.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import service.dao.IUserDAO;
import service.impl.UserService;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.easymock.EasyMock.same;
@RunWith(EasyMockRunner.class)
public class UserServiceTest {
    @Mock(type = MockType.STRICT, name = "userDao", fieldName = "dao")
    private IUserDAO dao;
    @TestSubject
    private UserService service = new UserService();

    List<User> dummyDB;
    User exceptionUser;
    User goodUser;
    User userWithoutAnything;
    @Before
    public void init() throws InvalidUsernameException, InvalidEmailException, InvalidPassword, InvalidRealnameException, InvalidUsernameException, InvalidEmailException, InvalidPassword, InvalidRealnameException {

        exceptionUser = new User(10,"hibasuser","12341234","hibA 2020","hiba@gmail.com", Role.admin);
        goodUser = new User(10,"gooduser","12341234#","hibA 2020","hiba@gmail.com", Role.admin);
        userWithoutAnything = new User();
        dummyDB = Arrays.asList(new User(1,"meoww","12341234#","Meow Meow","mewo@gmail.com",Role.admin),
                new User(2,"wooff","12341234#","Meow Woof","woof@gmail.com",Role.user),
                new User(3,"meoww","12341234#","Meow Meow","mewo@gmail.com",Role.user),
                new User(4,"meoww","12341234#","Meowasd Meow","mewo223@gmail.com",Role.user)
        );
        EasyMock.expect(dao.getAllUsers()).andReturn(dummyDB).anyTimes();
        EasyMock.expect(dao.getUsersByRole(Role.user)).andReturn(Arrays.asList(new User(5,"wooff","A12312344a#","Meowasd Meow","mewo223@gmail.com",Role.user),
                new User(3,"meoww","12341234","Meow Meow","mewo@gmail.com",Role.user),
                new User(5,"wooff","12341234#","Meowasd Meow","mewo223@gmail.com",Role.user))).anyTimes();
        EasyMock.expect(dao.addUser(same(userWithoutAnything))).andReturn(goodUser).anyTimes();
        EasyMock.expect(dao.deleteUserById(1)).andReturn(true).anyTimes();
        EasyMock.expect(dao.modifyUser(same(goodUser))).andReturn(true).anyTimes();
        EasyMock.expect(dao.getUserById(1)).andReturn(goodUser).anyTimes();
        EasyMock.replay(dao);
    }
    @Test
    public void getAllUser(){
        Collection<User> users = service.getAllUsers();
        Assert.assertEquals(dummyDB.size(),users.size());
    }
    @Test
    public void getAllNonAdminUser(){
        Collection<User> users = service.getUsersByRole(Role.user);
        Assert.assertEquals(3,users.size());
    }
    @Test
    public void addUser(){
        User user = service.addUser(userWithoutAnything);
        Assert.assertEquals(goodUser,user);
    }
    @Test
    public void deleteUser(){
        boolean result = service.deleteUserById(1);
        Assert.assertEquals(true,result);
    }
    @Test
    public void modifyUser(){
        boolean result = service.modifyUser(goodUser);
        Assert.assertEquals(true,result);
    }
    @Test
    public void getUserById(){
        User user = service.getUserById(1);
        Assert.assertEquals(user,goodUser);
    }

}
