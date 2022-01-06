package usecases;

import domain.entities.Mortgage;
import domain.exceptions.BankAccountDoesNotExistException;
import domain.exceptions.BankAccountNotApprovedException;
import domain.exceptions.CustomerDoesNotExistException;
import domain.exceptions.DoesNotComplyConditionsForMortgage;
import repositories.MortgageRepository;

import java.util.concurrent.ThreadLocalRandom;

public class ApplyForMortgage {
    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private ValidateCustomerBankAccountStatus validateCustomerBankAccountStatus;
    private ValidateMortgage validateMortgage;
    private MortgageRepository mortgageRepository;


    public ApplyForMortgage(){
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.validateCustomerBankAccountStatus = new ValidateCustomerBankAccountStatus();
        this.validateMortgage = new ValidateMortgage();
        this.mortgageRepository = new MortgageRepository();
    }

    public String execute(long SSN, long accountNumber, double totalMortgageValue, double years, double initialDeposit) throws Exception {
        boolean customerExists = validateCustomer.execute(SSN);
        // checks if the customer applying for the mortgage exists in the system.
        if (!customerExists){
            throw new CustomerDoesNotExistException(SSN);
        }
        boolean bankAccountExists = validateCustomerBankAccount.execute(SSN, accountNumber);
        //checks if the bank account exists.
        if (!bankAccountExists){
            throw new BankAccountDoesNotExistException(accountNumber);
        }
        //checks if the bank account status is approved
        boolean approvedBankAccount = validateCustomerBankAccountStatus.execute(SSN, accountNumber);
        if (!approvedBankAccount){
            throw new BankAccountNotApprovedException(accountNumber);
        }
        boolean acceptsMortgage = validateMortgage.execute(accountNumber, totalMortgageValue, years);
        //checks if conditions for a Mortgage are met.
        if (!acceptsMortgage){
            throw new DoesNotComplyConditionsForMortgage();
        }
        //condition which checks that the initial deposit supported by the customer needs to always be higher than the total value of the mortgage
        if (initialDeposit > totalMortgageValue){
            throw new DoesNotComplyConditionsForMortgage();
        }
        //this method creates an ID for the loan setting the minimum and maximum value
        long loanID = ThreadLocalRandom.current().nextLong(100000000,999999999);
        double monthPayment = Math.round(monthPayment(totalMortgageValue, 0.03, initialDeposit, years));
        Mortgage mortgage = new Mortgage(accountNumber, SSN, loanID, years, initialDeposit, totalMortgageValue, monthPayment);
        mortgageRepository.createMortgage(mortgage); // Mortgage details.


        return "Mortgage request with " + loanID + " created successfully with a month payment of " + monthPayment + " SEK. Please wait till an operator checks the application.";
    }

    //this private method calculates the month payment required for the customer according to the requested mortgage
    private double monthPayment(double totalMortgageValue, double interestRate, double initialDeposit, double years){
        double totalInterest = interestRate * totalMortgageValue;
        return ((totalMortgageValue + totalInterest - initialDeposit) / (years * 12)); // calculates monthly payment.
    }


}
