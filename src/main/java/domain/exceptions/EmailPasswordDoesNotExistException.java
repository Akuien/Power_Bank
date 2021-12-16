package domain.exceptions;

public class EmailPasswordDoesNotExistException extends Exception{
    public EmailPasswordDoesNotExistException(){
        super("Email or password not valid.");
    }
}
