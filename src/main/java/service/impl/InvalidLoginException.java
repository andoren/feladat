package service.impl;

public class InvalidLoginException extends Exception {
    InvalidLoginException(String msg){
        super(msg);
    }
}
