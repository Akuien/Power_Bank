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
        if (bankAccount.getBalance() < 0.2 * totalMortgageValue){
            return false; // It is impossible to get a Mortgage if you do not have 20% of the total Mortgage value in your funds.
        }
        // The customer cannot ask for a Mortgage to pay in +40 years.
        if (time > 40){
            return false;
        }
        return true;
    }


}
