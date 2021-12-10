package usecases;

import domain.constants.TransactionType;
import domain.entities.*;
import domain.exceptions.*;
import repositories.BankAccountRepository;
import repositories.CompanyRepository;
import repositories.PortfolioRepository;
import repositories.TransactionRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public class SellShares {

    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private ValidateFunds validateFunds;
    private BankAccountRepository bankAccountRepository;
    private PortfolioRepository portfolioRepository;
    private TransactionRepository transactionRepository;
    private CompanyRepository companyRepository;

    public SellShares(ValidateCustomer validateCustomer, ValidateCustomerBankAccount validateCustomerBankAccount,
                     BankAccountRepository bankAccountRepository, PortfolioRepository portfolioRepository,
                     TransactionRepository transactionRepository, CompanyRepository companyRepository) {

        this.validateCustomer = validateCustomer;
        this.validateCustomerBankAccount = validateCustomerBankAccount;
        this.bankAccountRepository = bankAccountRepository;
        this.portfolioRepository = portfolioRepository;
        this.transactionRepository = transactionRepository;
        this.companyRepository = companyRepository;
    }

    public String execute(String companyName, int quantity, long customerSSN, long customerAccountNumber, Stock stock ) throws Exception {
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

        //Use Case
        Date now = new Date();

        addCreditTransaction(customerAccountNumber, purchasePrice, now);
        creditBalance(customerAccountNumber, purchasePrice);
        removeStock(customerSSN, stock);

        return "Purchase made successfully.";
    }

    //We created these three private methods to make the execute method more understandable
    //We do not want to use these methods onwards, just here
    private void addCreditTransaction(long accountNumber, double price, Date currentDate){
        Transaction transaction = new Transaction(accountNumber, accountNumber, price, TransactionType.debit, currentDate);
        transactionRepository.createTransaction(transaction);
    }

    private void creditBalance(long accountNumber, double price){
        BankAccount customerBankAccount = bankAccountRepository.getAccountByAccountNumber(accountNumber);
        customerBankAccount.setBalance(customerBankAccount.getBalance() + price);
        bankAccountRepository.updateBankAccount(customerBankAccount); //checker
    }

    private void removeStock(long customerSSN, Stock stock) throws Exception {
        Portfolio portfolio = portfolioRepository.getPortfolioBySSN(customerSSN);
        if (portfolio == null) {
            throw new PortfolioDoesNotExistException();
        }
        if (stock.getQuantity() > portfolioRepository.getStocksByCompanyName(customerSSN, stock.getCompany()).size()){
            throw new PortfolioDoesNotHaveEnoughStocks();
        }
        ArrayList<Stock> customerStocks = portfolio.getStocks();
        customerStocks.remove(stock);
    }
    //Before being able to buy any stock the system should validate the Bank Account where all the funds are coming from
    //Before being able to buy any stock the system should validate the existence of the User.
    //Before being able to buy any stock the system should validate that the account has enough funds to do the purchase.

}