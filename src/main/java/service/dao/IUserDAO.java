package service.dao;

import core.model.Role;
import core.model.User;

import java.util.Collection;

public interface IUserDAO {
    Collection<User> getAllUsers();
    Collection<User> getUsersByRole(Role role);
    boolean modifyUser(User user);
    User getUserById(int id);
    User addUser(User user);
    boolean deleteUserById(int id);
    boolean logIn(String username,String password);
}