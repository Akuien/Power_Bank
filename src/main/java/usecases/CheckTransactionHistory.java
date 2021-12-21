package usecases;

import domain.entities.Transaction;
import domain.exceptions.BankAccountDoesNotExistException;
import domain.exceptions.CustomerDoesNotExistException;
import repositories.TransactionRepository;

import java.util.ArrayList;

public class CheckTransactionHistory {
    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private TransactionRepository transactionRepository;

    public CheckTransactionHistory(){
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.transactionRepository = new TransactionRepository();
    }

    public ArrayList<Transaction> execute(long SSN, long accountNumber) throws Exception {
        // checks if the customers exists
        boolean customerExists = validateCustomer.execute(SSN);
        if (!customerExists){
            throw new CustomerDoesNotExistException(SSN);
        }
        // checks first if the bank account exists
        boolean customerBankAccountExists = validateCustomerBankAccount.execute(SSN, accountNumber);
        if (!customerBankAccountExists){
            throw new BankAccountDoesNotExistException(accountNumber);
        }
        return transactionRepository.getTransactionsByAccountNumber(accountNumber);
    }
}
