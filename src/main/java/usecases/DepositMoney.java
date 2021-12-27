package usecases;

import domain.constants.TransactionType;
import domain.entities.BankAccount;
import domain.entities.Transaction;
import domain.exceptions.BankAccountDoesNotExistException;
import domain.exceptions.CustomerDoesNotExistException;
import repositories.BankAccountRepository;
import repositories.TransactionRepository;

import java.util.Date;

public class DepositMoney{

    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private BankAccountRepository bankAccountRepository;
    private TransactionRepository transactionRepository;

    public DepositMoney(){
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.bankAccountRepository = new BankAccountRepository();
        this.transactionRepository = new TransactionRepository();
    }

    public double execute(long SSN, long accountNumber, double amount) throws Exception {

        // Validation for existence of customer.
        boolean customerExists = validateCustomer.execute(SSN);
        if (!customerExists){
            throw new CustomerDoesNotExistException(SSN);
        }

        // Validation for existence of bank account.
        boolean customerBankAccountExists = validateCustomerBankAccount.execute(SSN, accountNumber);
        if (!customerBankAccountExists){
            throw new BankAccountDoesNotExistException(accountNumber);
        }

        //These two methods execute the whole thing
        addCreditTransaction(accountNumber, amount, new Date());
        return creditBalance(accountNumber, amount);
    }

    // This method is in charge of adding the deposit transaction in the arrayList of the persistence data.
    private void addCreditTransaction(long accountNumber, double price, Date currentDate){
        Transaction transaction = new Transaction(accountNumber, accountNumber, price, TransactionType.credit, currentDate);
        transactionRepository.createTransaction(transaction);
    }

    // This method is in charge of adding the money for the deposit functionality.
    private double creditBalance(long accountNumber, double price){
        BankAccount customerBankAccount = bankAccountRepository.getAccountByAccountNumber(accountNumber);
        customerBankAccount.setBalance(customerBankAccount.getBalance() + price);
        bankAccountRepository.updateBankAccount(customerBankAccount); //checker
        return customerBankAccount.getBalance();
    }
}
