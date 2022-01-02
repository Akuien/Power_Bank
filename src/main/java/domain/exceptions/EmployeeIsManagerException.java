package domain.exceptions;

public class EmployeeIsManagerException extends Exception{

    public EmployeeIsManagerException(long employeeSSN){
        super("Employee with " + employeeSSN + " is already a manager and therefore cannot be promoted.");
    }
}
