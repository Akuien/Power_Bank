package domain.exceptions;

public class CustomerDoesNotExistException extends Exception{

    public CustomerDoesNotExistException(long SSN){
        super("Customer with SSN " + SSN + " does not exist.");
    }
}
