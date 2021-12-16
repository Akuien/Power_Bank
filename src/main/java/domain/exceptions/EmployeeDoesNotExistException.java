package domain.exceptions;

public class EmployeeDoesNotExistException extends Exception{

    public EmployeeDoesNotExistException(long SSN){
        super("Employee with SSN " + SSN + " does not exist.");
    }
}
