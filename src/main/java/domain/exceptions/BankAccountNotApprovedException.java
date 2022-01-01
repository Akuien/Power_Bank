package domain.exceptions;

public class BankAccountNotApprovedException extends Exception{

    public BankAccountNotApprovedException(long accountNumber){
        super("Bank account (" + accountNumber + ") not approved. Therefore this action is not possible.");
    }
}
