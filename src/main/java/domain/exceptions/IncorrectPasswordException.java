package domain.exceptions;

public class IncorrectPasswordException extends Exception{

    public IncorrectPasswordException(){
        super("Password is incorrect.");
    }
}
