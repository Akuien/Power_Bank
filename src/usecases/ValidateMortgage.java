package usecases;


//This method is created for checking if a user is allowed to get a mortgage or not

import domain.entities.BankAccount;
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

    public boolean execute(long accountNumber,double totalMortgageValue, double time){
        BankAccount bankAccount = bankAccountRepository.getAccountByAccountNumber(accountNumber);
        //Do we need any other validations for
        if (bankAccount.getBalance() < 0.2 * totalMortgageValue){
            return false;
        }
        //The customer cannot ask for a mortgage to pay in +40 years
        if (time > 40){
            return false;
        }
        return true;
    }


}
