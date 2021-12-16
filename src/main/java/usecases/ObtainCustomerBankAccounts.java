package usecases;

import domain.entities.BankAccount;
import repositories.BankAccountRepository;

import java.util.ArrayList;

public class ObtainCustomerBankAccounts {
    private BankAccountRepository bankAccountRepository;

    public ObtainCustomerBankAccounts( ){
        this.bankAccountRepository = new BankAccountRepository();
    }

    public ArrayList<BankAccount> execute(long SSN){
        return this.bankAccountRepository.getAccountsBySSN(SSN);
    }
}
