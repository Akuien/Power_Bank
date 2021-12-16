package domain.exceptions;

public class NameIsBlankException extends Exception{

    public NameIsBlankException(){
        super("Name cannot be empty");
    }
}
