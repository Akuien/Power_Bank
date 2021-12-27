package usecases;

import domain.constants.TransactionType;
import domain.entities.BankAccount;
import domain.entities.Transaction;
import domain.exceptions.BankAccountDoesNotExistException;
import domain.exceptions.CustomerDoesNotExistException;
import domain.exceptions.DoesNotHaveEnoughFundsException;
import repositories.BankAccountRepository;
import repositories.TransactionRepository;

import java.util.Date;

public class WithdrawMoney {

    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private ValidateFunds validateFunds;
    private BankAccountRepository bankAccountRepository;
    private TransactionRepository transactionRepository;


    public WithdrawMoney(){
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.validateFunds = new ValidateFunds();
        this.bankAccountRepository = new BankAccountRepository();
        this.transactionRepository = new TransactionRepository();
    }

    public double execute(long SSN, long accountNumber, double amount) throws Exception {
        // Check if customer exists.
        boolean customerExists = validateCustomer.execute(SSN);
        if (!customerExists){
            throw new CustomerDoesNotExistException(SSN);
        }
        // Check if a customer bank account exists.
        boolean customerBankAccountExists = validateCustomerBankAccount.execute(SSN, accountNumber);
        if (!customerBankAccountExists){
            throw new BankAccountDoesNotExistException(accountNumber);
        }
        // Check if account has enough funds.
        boolean hasFunds = validateFunds.execute(accountNumber, amount);
        if (!hasFunds){
            throw new DoesNotHaveEnoughFundsException();
        }
        // Execute the program.
        addDebitTransaction(accountNumber, amount, new Date());
        return debitBalance(accountNumber, amount);
    }


    //This method creates the transaction and adds it to the list
    private void addDebitTransaction(long accountNumber, double price, Date currentDate){
        Transaction transaction = new Transaction(accountNumber, accountNumber, price, TransactionType.debit, currentDate);
        transactionRepository.createTransaction(transaction);
    }

    //This method does the business logic of getting rid of the money
    private double debitBalance(long accountNumber, double price){
        BankAccount customerBankAccount = bankAccountRepository.getAccountByAccountNumber(accountNumber);
        customerBankAccount.setBalance(customerBankAccount.getBalance() - price);
        bankAccountRepository.updateBankAccount(customerBankAccount); //checker
        return customerBankAccount.getBalance();
    }

}
