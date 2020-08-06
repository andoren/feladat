package helper;

import core.exceptions.InvalidEmailException;
import core.exceptions.InvalidRealnameException;
import core.exceptions.InvalidUsernameException;
import core.model.User;

public interface IJWTHelper {
    String generateJWT(User user);
    User decodeJWT(String token) throws InvalidEmailException, InvalidRealnameException, InvalidUsernameException;
}
