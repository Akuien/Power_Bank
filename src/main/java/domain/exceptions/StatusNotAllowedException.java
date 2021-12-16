package domain.exceptions;

public class StatusNotAllowedException extends Exception{

    public StatusNotAllowedException(){
        super("Suggested status not allowed.");
    }
}
