package usecases;

import domain.constants.TransactionType;
import domain.entities.BankAccount;
import domain.entities.Transaction;
import domain.exceptions.BankAccountDoesNotExistException;
import domain.exceptions.BankAccountNotApprovedException;
import domain.exceptions.CustomerDoesNotExistException;
import domain.exceptions.DoesNotHaveEnoughFundsException;
import repositories.BankAccountRepository;
import repositories.TransactionRepository;

import java.util.Date;

public class TransferMoneyToAnotherAccount {

    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private ValidateCustomerBankAccountStatus validateCustomerBankAccountStatus;
    private ValidateFunds validateFunds;
    private BankAccountRepository bankAccountRepository;
    private TransactionRepository transactionRepository;

    public TransferMoneyToAnotherAccount(){
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.validateCustomerBankAccountStatus = new ValidateCustomerBankAccountStatus();
        this.validateFunds = new ValidateFunds();
        this.bankAccountRepository = new BankAccountRepository();
        this.transactionRepository = new TransactionRepository();
    }

    public double execute(long originSSN, long originAccountNumber, long finalSSN, long finalAccountNumber, double amount) throws Exception {
        // Checks if origin customer exists.
        boolean originCustomerExists = validateCustomer.execute(originSSN);
        if (!originCustomerExists){
            throw new CustomerDoesNotExistException(originSSN);
        }

        //Validates if the origin customer bankAccount exists or not.
        boolean originCustomerBankAccountExists = validateCustomerBankAccount.execute(originSSN, originAccountNumber);
        if (!originCustomerBankAccountExists){
            throw new BankAccountDoesNotExistException(originAccountNumber);
        }
        // Validates if the origin bank account is approved or not.
        boolean approvedOriginBankAccount = validateCustomerBankAccountStatus.execute(originSSN, originAccountNumber);
        if (!approvedOriginBankAccount){
            throw new BankAccountNotApprovedException(originAccountNumber);
        }

        //Validates if the origin customer bank account has enough funds to do the action or not.
        boolean hasFunds = validateFunds.execute(originAccountNumber, amount);
        if (!hasFunds){
            throw new DoesNotHaveEnoughFundsException();
        }

        //Validates if the final customer who is going to receive the transfer of money exsits or not.
        boolean finalCustomerExists = validateCustomer.execute(finalSSN);
        if (!finalCustomerExists){
            throw new CustomerDoesNotExistException(finalSSN);
        }

        //Validates if the final customer bankAccount exists or not.
        boolean finalCustomerBankAccountExists = validateCustomerBankAccount.execute(finalSSN, finalAccountNumber);
        if (!finalCustomerBankAccountExists){
            throw new BankAccountDoesNotExistException(finalAccountNumber);
        }
        //Validates if the final customer bank account is approved or not.
        boolean approvedFinalBankAccount = validateCustomerBankAccountStatus.execute(finalSSN, finalAccountNumber);
        if (!approvedFinalBankAccount){
            throw new BankAccountNotApprovedException(finalAccountNumber);
        }
        // Executes the program.
        double originBalance = debitOriginBalance(originAccountNumber, amount);
        double finalBalance = creditFinalBalance(finalAccountNumber, amount);
        addTransaction(originAccountNumber, finalAccountNumber, amount, TransactionType.debit, new Date());
        return originBalance;
    }

    // Debit means when the money goes out of your account.
    // Credit means when the money goes into your account.


    // This private method gets out the money from the origin customer.
    private double debitOriginBalance(long originAccountNumber, double amount){
        BankAccount originBankAccount = bankAccountRepository.getAccountByAccountNumber(originAccountNumber);
        originBankAccount.setBalance(originBankAccount.getBalance() - amount);
        bankAccountRepository.updateBankAccount(originBankAccount);
        return originBankAccount.getBalance();
    }

    // This private method adds in the money to the final customer.
    private double creditFinalBalance(long finalAccountNumber, double amount){
        BankAccount finalBankAccount = bankAccountRepository.getAccountByAccountNumber(finalAccountNumber);
        finalBankAccount.setBalance(finalBankAccount.getBalance() + amount);
        bankAccountRepository.updateBankAccount(finalBankAccount);
        return finalBankAccount.getBalance();
    }

    // This private method creates the transaction.
    private void addTransaction(long originAccountNumber, long finalAccountNumber, double price, String type, Date currentDate){
        Transaction transaction = new Transaction(originAccountNumber, finalAccountNumber, price, type, currentDate);
        transactionRepository.createTransaction(transaction);
    }

}
