package usecases;

import domain.entities.BankAccount;
import repositories.BankAccountRepository;

public class ValidateFunds {

    private BankAccountRepository bankAccountRepository;

    public ValidateFunds(){
        BankAccountRepository bankAccountRepository = new BankAccountRepository();
    }

    public boolean execute(long accountNumber, double totalAmount){
        BankAccount bankAccount = bankAccountRepository.getAccountByAccountNumber(accountNumber);
        return bankAccount.getBalance() >= totalAmount;
    }

}
