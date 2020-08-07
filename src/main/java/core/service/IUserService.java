package core.service;

import core.exceptions.InvalidEmailException;
import core.exceptions.InvalidPassword;
import core.exceptions.InvalidRealnameException;
import core.exceptions.InvalidUsernameException;
import core.model.Address;
import core.model.Role;
import core.model.User;
import service.impl.expections.InvalidLoginException;

import java.util.Collection;


public interface IUserService {
    Collection<User> getAllUsers();
    Collection<User> getUsersByRole(Role role);
    boolean modifyUser(User user);
    User getUserById(int id);
    User addUser(User user);
    boolean deleteUserById(int id) throws InvalidEmailException, InvalidRealnameException, InvalidUsernameException;
    User logIn(String username,String password) throws InvalidPassword, InvalidLoginException;
    Collection<Address> getUserAddresses(int id);
    boolean addAddressToUser(Address address);
}
