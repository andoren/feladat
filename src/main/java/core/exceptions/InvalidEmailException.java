package core.exceptions;

public class InvalidEmailException extends Throwable {
    public InvalidEmailException(String msg){
        super(msg);
    }
}
