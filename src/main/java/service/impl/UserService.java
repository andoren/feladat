package service.impl;

import core.exceptions.InvalidPassword;
import core.model.Role;
import core.model.User;
import core.service.IUserService;
import dao.impl.MysqlUserDAO;
import helper.JWTHelper;
import service.dao.IUserDAO;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Collection;
@Stateless
public class UserService implements IUserService {
    @EJB
    private IUserDAO dao;

    public UserService(){
        dao = new MysqlUserDAO();
    }
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

        User newuser = dao.addUser(user);
        user.setToken(JWTHelper.generateJWT(newuser));
        return newuser;
    }

    public boolean deleteUserById(int id) {
        return dao.deleteUserById(id);
    }

    public User logIn(String username, String password) throws InvalidPassword, InvalidLoginException {

        User user = dao.logIn(username,password);
        if(user.getEmail() == null) throw new InvalidLoginException("Hibás felhasználónév vagy jelszó");
        user.setToken(JWTHelper.generateJWT(user));
        return user;
    }

}