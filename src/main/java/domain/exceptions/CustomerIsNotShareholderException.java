package domain.exceptions;

public class CustomerIsNotShareholderException extends Exception{

    public CustomerIsNotShareholderException(long SSN){
        super("Customer with SSN " + SSN + " is not a shareholder.");
    }
}
