package usecases;

import domain.entities.BankAccount;
import domain.exceptions.BankAccountDoesNotExistException;
import domain.exceptions.CustomerDoesNotExistException;
import repositories.BankAccountRepository;

public class CheckBalance {

    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private BankAccountRepository bankAccountRepository;

    public CheckBalance(){
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.bankAccountRepository = new BankAccountRepository();
    }

    public double execute(long SSN, long accountNumber) throws Exception {
        // Checks if customer exists.
        boolean customerExists = validateCustomer.execute(SSN);
        if (!customerExists){
            throw new CustomerDoesNotExistException(SSN);
        }
        // Checks if the bank account exists.
        boolean customerBankAccountExists = validateCustomerBankAccount.execute(SSN, accountNumber);
        if (!customerBankAccountExists){
            throw new BankAccountDoesNotExistException(accountNumber);
        }
        // We get the account where we want to check the balance with account number.
        BankAccount customerBankAccount = bankAccountRepository.getAccountByAccountNumber(accountNumber);
        // We execute the getBalance method from the entity class.
        return customerBankAccount.getBalance();
    }

}
