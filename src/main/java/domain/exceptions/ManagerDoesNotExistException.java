package domain.exceptions;

public class ManagerDoesNotExistException extends Exception{

    public ManagerDoesNotExistException(long SSN){
        super("Manager with SSN " + SSN + " does not exist.");
    }
}
