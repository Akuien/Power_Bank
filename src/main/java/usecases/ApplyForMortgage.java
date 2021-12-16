package usecases;

import domain.entities.Mortgage;
import domain.exceptions.BankAccountDoesNotExistException;
import domain.exceptions.CustomerDoesNotExistException;
import domain.exceptions.DoesNotComplyConditionsForMortgage;
import repositories.MortgageRepository;

import java.util.concurrent.ThreadLocalRandom;

public class ApplyForMortgage {
    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private ValidateMortgage validateMortgage;
    private MortgageRepository mortgageRepository;


    public ApplyForMortgage(){
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.validateMortgage = new ValidateMortgage();
        this.mortgageRepository = new MortgageRepository();
    }

    public String execute(long SSN, long accountNumber, double totalMortgageValue, double time, double initialDeposit) throws Exception {
        boolean customerExists = validateCustomer.execute(SSN);
        if (!customerExists){
            throw new CustomerDoesNotExistException(SSN);
        }
        boolean bankAccountExists = validateCustomerBankAccount.execute(SSN, accountNumber);
        if (!bankAccountExists){
            throw new BankAccountDoesNotExistException(accountNumber);
        }
        boolean acceptsMortgage = validateMortgage.execute(accountNumber, totalMortgageValue, time);
        if (!acceptsMortgage){
            throw new DoesNotComplyConditionsForMortgage();
        }

        long loanID = ThreadLocalRandom.current().nextLong(100000000,999999999);
        Mortgage mortgage = new Mortgage(SSN, loanID, time, initialDeposit, totalMortgageValue, monthPayment(totalMortgageValue, 0.03, initialDeposit, time));
        mortgageRepository.createMortgage(mortgage);

        return "Mortgage request with " + loanID + " created successfully. Please wait till an operator checks the application.";
    }

    private double monthPayment(double totalMortgageValue, double interestRate, double initialDeposit, double time){
        double totalInterest = interestRate * totalMortgageValue;
        return ((totalMortgageValue + totalInterest - initialDeposit) / (time * 12));
    }


}
