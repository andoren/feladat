package core.exceptions;

public class InvalidPassword extends Throwable{
    public InvalidPassword(String message) {
        super(message);
    }
}
