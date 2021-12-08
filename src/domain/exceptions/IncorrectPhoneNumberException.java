package domain.exceptions;

public class IncorrectPhoneNumberException extends Exception{

    public IncorrectPhoneNumberException(){
        super("Phone-number is incorrect.");
    }
}
