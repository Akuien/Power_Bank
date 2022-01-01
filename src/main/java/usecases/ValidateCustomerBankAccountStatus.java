package usecases;

import domain.constants.BankAccountStatus;
import domain.entities.BankAccount;

import java.util.ArrayList;

public class ValidateCustomerBankAccountStatus {
    private ObtainCustomerBankAccounts obtainCustomerBankAccounts;

    public ValidateCustomerBankAccountStatus( ){
        this.obtainCustomerBankAccounts = new ObtainCustomerBankAccounts();
    }

    public boolean execute(long SSN, long accountNumber){
        ArrayList<BankAccount> customerBankAccounts = obtainCustomerBankAccounts.execute(SSN);
        for (BankAccount currentBankAccount : customerBankAccounts){
            if (currentBankAccount.getAccountNumber() == accountNumber && currentBankAccount.getStatus().equals(BankAccountStatus.approved)){
                return true;
            }
        }
        return false;
    }
}
