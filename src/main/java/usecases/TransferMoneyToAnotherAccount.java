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

public class TransferMoneyToAnotherAccount {

    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private ValidateFunds validateFunds;
    private BankAccountRepository bankAccountRepository;
    private TransactionRepository transactionRepository;

    public TransferMoneyToAnotherAccount(){
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.validateFunds = new ValidateFunds();
        this.bankAccountRepository = new BankAccountRepository();
        this.transactionRepository = new TransactionRepository();
    }

    public double execute(long originSSN, long originAccountNumber, long finalSSN, long finalAccountNumber, double amount) throws Exception {
        //Checks if origin customer exists
        boolean originCustomerExists = validateCustomer.execute(originSSN);
        if (!originCustomerExists){
            throw new CustomerDoesNotExistException(originSSN);
        }

        //Checks if origin customer bankAccount exists
        boolean originCustomerBankAccountExists = validateCustomerBankAccount.execute(originSSN, originAccountNumber);
        if (!originCustomerBankAccountExists){
            throw new BankAccountDoesNotExistException(originAccountNumber);
        }

        //Checks if origin customer has enough funds to do the transaction
        boolean hasFunds = validateFunds.execute(originAccountNumber, amount);
        if (!hasFunds){
            throw new DoesNotHaveEnoughFundsException();
        }

        //checks if final customer exists
        boolean finalCustomerExists = validateCustomer.execute(finalSSN);
        if (!finalCustomerExists){
            throw new CustomerDoesNotExistException(finalSSN);
        }

        //Checks if final customer bankAccount exists
        boolean finalCustomerBankAccountExists = validateCustomerBankAccount.execute(finalSSN, finalAccountNumber);
        if (!finalCustomerBankAccountExists){
            throw new BankAccountDoesNotExistException(finalAccountNumber);
        }

        //Executes the program
        double originBalance = debitOriginBalance(originAccountNumber, amount);
        double finalBalance = creditFinalBalance(finalAccountNumber, amount);
        addTransaction(originAccountNumber, finalAccountNumber, amount, TransactionType.debit, new Date());
        return originBalance;
    }

    //Debit means when the money goes out of your account
    //Credit means when the money goes into your account


    //This method gets out the money from the origin customer
    private double debitOriginBalance(long originAccountNumber, double amount){
        BankAccount originBankAccount = bankAccountRepository.getAccountByAccountNumber(originAccountNumber);
        originBankAccount.setBalance(originBankAccount.getBalance() - amount);
        bankAccountRepository.updateBankAccount(originBankAccount);
        return originBankAccount.getBalance();
    }

    //This method adds in the money to the final customer
    private double creditFinalBalance(long finalAccountNumber, double amount){
        BankAccount finalBankAccount = bankAccountRepository.getAccountByAccountNumber(finalAccountNumber);
        finalBankAccount.setBalance(finalBankAccount.getBalance() + amount);
        bankAccountRepository.updateBankAccount(finalBankAccount);
        return finalBankAccount.getBalance();
    }

    //This method adds the transaction to the ArrayList in persistenceData
    private void addTransaction(long originAccountNumber, long finalAccountNumber, double price, String type, Date currentDate){
        Transaction transaction = new Transaction(originAccountNumber, finalAccountNumber, price, type, currentDate);
        transactionRepository.createTransaction(transaction);
    }

}
