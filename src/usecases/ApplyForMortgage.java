package usecases;

import domain.entities.BankAccount;
import domain.exceptions.CustomerDoesNotExistException;
import repositories.BankAccountRepository;

import java.util.concurrent.ThreadLocalRandom;

public class ApplyForMortgage {
    private ValidateCustomer validateCustomer;
    private BankAccountRepository bankAccountRepository;

    public ApplyForMortgage(){
        this.validateCustomer = new ValidateCustomer();
        this.bankAccountRepository = new BankAccountRepository();
    }

    public String execute(long SSN, String bankAccountName) throws Exception {
        //We check if the customer who is doing the application exists
        boolean customerExists = validateCustomer.execute(SSN);
        if (!customerExists){
            throw new CustomerDoesNotExistException(SSN);
        }

        //With this method we can generate an accountNumber
        long accountNumber = ThreadLocalRandom.current().nextLong(100000000,999999999);

        BankAccount bankAccount = new BankAccount(0, bankAccountName, accountNumber, SSN);
        //We add the bank account to the arrayList with the pending type
        bankAccountRepository.createBankAccount(bankAccount);
        return "Bank account request with " + accountNumber + " created successfully. Please wait till an operator checks the application.";
    }
}
