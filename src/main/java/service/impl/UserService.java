package service.impl;

import core.model.Role;
import core.model.User;
import core.service.IUserService;
import service.dao.IUserDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
@ApplicationScoped
public class UserService implements IUserService {
    @Inject  private IUserDAO dao;

    public Collection<User> getAllUsers() {
        return dao.getAllUsers();
    }

    public Collection<User> getUsersByRole(Role role) {
        return dao.getUsersByRole(role);
    }

    public boolean modifyUser(User user) {
        return dao.modifyUser(user);
    }

    public User getUserById(int id) {
        return dao.getUserById(id);
    }

    public User addUser(User user) {
        return dao.addUser(user);
    }

    public boolean deleteUserById(int id) {
        return dao.deleteUserById(id);
    }

    public boolean logIn(String username, String password) {
        return dao.logIn(username,password);
    }


}