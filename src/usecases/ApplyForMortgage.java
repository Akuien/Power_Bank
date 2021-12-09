package usecases;

import domain.entities.BankAccount;
import domain.exceptions.BankAccountDoesNotExistException;
import domain.exceptions.CustomerDoesNotExistException;
import repositories.BankAccountRepository;
import repositories.MortgageRepository;

import java.util.concurrent.ThreadLocalRandom;

public class ApplyForMortgage {
    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private MortgageRepository mortgageRepository;

    public ApplyForMortgage(){
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.mortgageRepository = new MortgageRepository();
    }

    public String execute(long SSN, long accountNumber) throws Exception {
        boolean customerExists = validateCustomer.execute(SSN);
        if (!customerExists){
            throw new CustomerDoesNotExistException(SSN);
        }
        boolean bankAccountExists = validateCustomerBankAccount.execute(SSN, accountNumber);
        if (!bankAccountExists){
            throw new BankAccountDoesNotExistException(accountNumber);
        }



    }


}
