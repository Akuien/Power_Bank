package domain.exceptions;

public class BankAccountDoesNotExistException extends Exception{

    public BankAccountDoesNotExistException(long accountNumber){
        super("Bank Account with number " + accountNumber + " does not exist.");
    }
}
