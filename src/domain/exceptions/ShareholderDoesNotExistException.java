package domain.exceptions;

public class ShareholderDoesNotExistException extends Exception{

    public ShareholderDoesNotExistException(long SSN){
        super("Shareholder with SSN " + SSN + " does not exist.");
    }
}
