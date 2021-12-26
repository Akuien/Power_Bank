package domain.exceptions;

public class ManagerAlreadyExistsException extends Exception{

    public ManagerAlreadyExistsException(long managerSSN){
        super("Manager with " + managerSSN + " already exists-");
    }
}
