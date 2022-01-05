package usecases;

import domain.constants.TransactionType;
import domain.constants.UserType;
import domain.entities.*;
import domain.exceptions.*;
import repositories.*;

import java.util.Date;

public class BuyShares {
    private ValidateShareholder validateShareholder;
    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private ValidateCustomerBankAccountStatus validateCustomerBankAccountStatus;
    private ValidateFunds validateFunds;
    private BankAccountRepository bankAccountRepository;
    private PortfolioRepository portfolioRepository;
    private TransactionRepository transactionRepository;
    private CompanyRepository companyRepository;
    private CustomerRepository customerRepository;

    public BuyShares() {
        this.validateShareholder = new ValidateShareholder();
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.validateCustomerBankAccountStatus = new ValidateCustomerBankAccountStatus();
        this.validateFunds = new ValidateFunds();
        this.bankAccountRepository = new BankAccountRepository();
        this.portfolioRepository = new PortfolioRepository();
        this.transactionRepository = new TransactionRepository();
        this.companyRepository = new CompanyRepository();
        this.customerRepository = new CustomerRepository();
    }

    public String execute(String companyName, int quantity, long customerSSN, long customerAccountNumber ) throws Exception {
        // validates the existence of the customer
        boolean customerExists = validateCustomer.execute(customerSSN);
        if (!customerExists){
            throw new CustomerDoesNotExistException(customerSSN);
        }
        // validates that the customer's bank account exists
        boolean customerBankAccountExists = validateCustomerBankAccount.execute(customerSSN, customerAccountNumber);
        if (!customerBankAccountExists){
            throw new BankAccountDoesNotExistException(customerAccountNumber);
        }
        // validates that the bank account where the purchase price is going to be retired
        boolean approvedBankAccount = validateCustomerBankAccountStatus.execute(customerSSN, customerAccountNumber);
        if (!approvedBankAccount){
            throw new BankAccountNotApprovedException(customerAccountNumber);
        }
        Company company = companyRepository.getCompanyByName(companyName);
        // checks if company exists or not
        if (company == null){
            throw new CompanyDoesNotExistException(companyName);
        }
        double purchasePrice = company.getStockPrice() * quantity;
        // checks if the bank account where the purchase price is going to be retired has enough funds or not
        boolean hasFunds = validateFunds.execute(customerAccountNumber, purchasePrice);
        if (!hasFunds){
            throw new DoesNotHaveEnoughFundsException();
        }

        // The use case code, this is where the share is being purchased.
        Date now = new Date();
        Stock stock = new Stock(now, companyName, quantity,customerSSN, customerAccountNumber);

        addDebitTransaction(customerAccountNumber, purchasePrice, now);
        debitBalance(customerAccountNumber, purchasePrice);
        addStock(customerSSN, stock);
        promoteToShareholder(customerSSN);

        return "Purchase made successfully.";
    }

    // We created these three private methods to make the execution method more understandable.
    // We do not want to use these methods onwards, just here, inside this class.

    // this private method has the purpose of creating the respective debit transaction since the bank account is going to have a withdrawal
    private void addDebitTransaction(long accountNumber, double price, Date currentDate){
        Transaction transaction = new Transaction(accountNumber, accountNumber, price, TransactionType.debit, currentDate);
        transactionRepository.createTransaction(transaction);
    }

    // this private method has the purpose of doing the withdrawal to the specific bank account
    private void debitBalance(long accountNumber, double price){
        BankAccount customerBankAccount = bankAccountRepository.getAccountByAccountNumber(accountNumber);
        customerBankAccount.setBalance(customerBankAccount.getBalance() - price);
        bankAccountRepository.updateBankAccount(customerBankAccount); //checker
    }

    // this private method has the purpose of adding the bought stock to the customer's portfolio or create a new portfolio where the stocks are going to be added in case no other was created before.
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

    // this private method has the purpose of changing the status of a customer to the one of a shareholder.
    private void promoteToShareholder(long customerSSN){
        Customer customer = customerRepository.getBySSN(customerSSN);
        customer.setType(UserType.shareholder);
        customerRepository.updateProfile(customer);
    }


    // Before being able to buy any stock the system should validate the Bank Account where all the funds are coming from,
    // whether the User actually exists, and that there are sufficient funds to make the purchase.

}
