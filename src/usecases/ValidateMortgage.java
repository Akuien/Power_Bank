package usecases;


//This method is created for checking if a user is allowed to get a mortgage or not

import domain.entities.BankAccount;
import domain.entities.Customer;
import domain.entities.Mortgage;
import repositories.BankAccountRepository;
import repositories.CustomerRepository;

public class ValidateMortgage {

    private CustomerRepository customerRepository;
    private BankAccountRepository bankAccountRepository;

    public ValidateMortgage(){
        this.customerRepository = new CustomerRepository();
        this.bankAccountRepository = new BankAccountRepository();
    }

    public boolean execute(long accountNumber, Mortgage mortgage){
        BankAccount bankAccount = bankAccountRepository.getAccountByAccountNumber(accountNumber);
        if (bankAccount.getBalance() < 0.2*mortgage.getTotalValue() ){
            return false;
        }


    }


}
