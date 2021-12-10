package usecases;

import domain.constants.TransactionType;
import domain.entities.*;
import domain.exceptions.BankAccountDoesNotExistException;
import domain.exceptions.CompanyDoesNotExistException;
import domain.exceptions.CustomerDoesNotExistException;
import domain.exceptions.DoesNotHaveEnoughFundsException;
import repositories.BankAccountRepository;
import repositories.CompanyRepository;
import repositories.PortfolioRepository;
import repositories.TransactionRepository;

import java.util.Date;

public class BuyShares {

    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private ValidateFunds validateFunds;
    private BankAccountRepository bankAccountRepository;
    private PortfolioRepository portfolioRepository;
    private TransactionRepository transactionRepository;
    private CompanyRepository companyRepository;

    public BuyShares(ValidateCustomer validateCustomer, ValidateCustomerBankAccount validateCustomerBankAccount,
                            ValidateFunds validateFunds, BankAccountRepository bankAccountRepository, PortfolioRepository portfolioRepository,
                            TransactionRepository transactionRepository, CompanyRepository companyRepository) {

        this.validateCustomer = validateCustomer;
        this.validateCustomerBankAccount = validateCustomerBankAccount;
        this.validateFunds = validateFunds;
        this.bankAccountRepository = bankAccountRepository;
        this.portfolioRepository = portfolioRepository;
        this.transactionRepository = transactionRepository;
        this.companyRepository = companyRepository;
    }

    public String execute(String companyName, int quantity, long customerSSN, long customerAccountNumber ) throws Exception {
        //Validations
        boolean customerExists = validateCustomer.execute(customerSSN);
        if (!customerExists){
            throw new CustomerDoesNotExistException(customerSSN);
        }
        boolean customerBankAccountExists = validateCustomerBankAccount.execute(customerSSN, customerAccountNumber);
        if (!customerBankAccountExists){
            throw new BankAccountDoesNotExistException(customerAccountNumber);
        }
        Company company = companyRepository.getCompanyByName(companyName);
        if (company == null){
            throw new CompanyDoesNotExistException(companyName);
        }
        double purchasePrice = company.getStockPrice() * quantity;
        boolean hasFunds = validateFunds.execute(customerAccountNumber, purchasePrice);
        if (!hasFunds){
            throw new DoesNotHaveEnoughFundsException();
        }

        //Use Case
        Date now = new Date();
        Stock stock = new Stock(purchasePrice, now, companyName, quantity,customerSSN, customerAccountNumber);

        addDebitTransaction(customerAccountNumber, purchasePrice, now);
        debitBalance(customerAccountNumber, purchasePrice);
        addStock(customerSSN, stock);

        return "Purchase made successfully.";
    }

    //We created these three private methods to make the execute method more understandable
    //We do not want to use these methods onwards, just here
    private void addDebitTransaction(long accountNumber, double price, Date currentDate){
        Transaction transaction = new Transaction(accountNumber, accountNumber, price, TransactionType.debit, currentDate);
        transactionRepository.createTransaction(transaction);
    }

    private void debitBalance(long accountNumber, double price){
        BankAccount customerBankAccount = bankAccountRepository.getAccountByAccountNumber(accountNumber);
        customerBankAccount.setBalance(customerBankAccount.getBalance() - price);
        bankAccountRepository.updateBankAccount(customerBankAccount); //checker
    }

    private void addStock(long customerSSN, Stock stock){
        Portfolio portfolio = portfolioRepository.getPortfolioBySSN(customerSSN);
        if (portfolio == null){
            portfolio = new Portfolio(customerSSN);
            portfolio.addStock(stock);
            portfolioRepository.createPortfolio(portfolio);
        } else {
            portfolio.addStock(stock);
            portfolioRepository.updatePortfolio(portfolio);
        }
    }
    //Before being able to buy any stock the system should validate the Bank Account where all the funds are coming from
    //Before being able to buy any stock the system should validate the existence of the User.
    //Before being able to buy any stock the system should validate that the account has enough funds to do the purchase.

}
