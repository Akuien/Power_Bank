package domain.exceptions;

public class MortgageDoesNotExistException extends Exception{

    public MortgageDoesNotExistException(long mortgageLoanID){
        super("Mortgage with " + mortgageLoanID + " does not exist.");
    }
}
