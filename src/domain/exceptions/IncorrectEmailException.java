package domain.exceptions;

public class IncorrectEmailException extends Exception{

    public IncorrectEmailException(){
        super("Email is incorrect.");
    }
}
