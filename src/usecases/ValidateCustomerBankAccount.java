package usecases;

import domain.entities.BankAccount;

import java.util.ArrayList;

public class ValidateCustomerBankAccount {
    private ObtainCustomerBankAccounts obtainCustomerBankAccounts;

    public ValidateCustomerBankAccount( ){
        this.obtainCustomerBankAccounts = new ObtainCustomerBankAccounts();
    }

    public boolean execute(long SSN, long accountNumber){
        ArrayList<BankAccount> customerBankAccounts = obtainCustomerBankAccounts.execute(SSN);
        for (BankAccount currentBankAccount : customerBankAccounts){
            if (currentBankAccount.getAccountNumber() == accountNumber){
                return true;
            }
        }
        return false;
    }
}
