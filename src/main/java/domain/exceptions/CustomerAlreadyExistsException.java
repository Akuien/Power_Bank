package domain.exceptions;

public class CustomerAlreadyExistsException extends Exception{

    public CustomerAlreadyExistsException(long customerSSN){
        super("Customer with " + customerSSN + " already exists-");
    }
}
