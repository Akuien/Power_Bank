package domain.exceptions;

public class EmployeeAlreadyExistsException extends Exception{

    public EmployeeAlreadyExistsException(long employeeSSN){
        super("Employee with " + employeeSSN + " already exists-");
    }
}
