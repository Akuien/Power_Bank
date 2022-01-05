package usecases;

import domain.constants.TransactionType;
import domain.entities.*;
import domain.exceptions.*;
import repositories.BankAccountRepository;
import repositories.CompanyRepository;
import repositories.PortfolioRepository;
import repositories.TransactionRepository;

import java.util.ArrayList;
import java.util.Date;

public class SellShares {
    private ValidateShareholder validateShareholder;
    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private ValidateCustomerBankAccountStatus validateCustomerBankAccountStatus;
    private BankAccountRepository bankAccountRepository;
    private PortfolioRepository portfolioRepository;
    private TransactionRepository transactionRepository;
    private CompanyRepository companyRepository;

    public SellShares() {
        this.validateShareholder = new ValidateShareholder();
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.validateCustomerBankAccountStatus = new ValidateCustomerBankAccountStatus();
        this.bankAccountRepository = new BankAccountRepository();
        this.portfolioRepository = new PortfolioRepository();
        this.transactionRepository = new TransactionRepository();
        this.companyRepository = new CompanyRepository();
    }

    public String execute(String companyName, int quantity, long customerSSN, long customerAccountNumber) throws Exception {
        //Validates if the shareholder who is trying to sell shares exists or not.
        boolean shareholderExists = validateShareholder.execute(customerSSN);
        if (!shareholderExists){
            throw new ShareholderDoesNotExistException(customerSSN);
        }
        //Validates if the customer exists or not.
        boolean customerExists = validateCustomer.execute(customerSSN);
        if (!customerExists){
            throw new CustomerDoesNotExistException(customerSSN);
        }
        //Validates if the customer bank account where all the money is going to be deposited exists or not.
        boolean customerBankAccountExists = validateCustomerBankAccount.execute(customerSSN, customerAccountNumber);
        if (!customerBankAccountExists){
            throw new BankAccountDoesNotExistException(customerAccountNumber);
        }
        //Validates if the customer's bank account where all the money is going to be deposited is approved or not.
        boolean approvedBankAccount = validateCustomerBankAccountStatus.execute(customerSSN, customerAccountNumber);
        if (!approvedBankAccount){
            throw new BankAccountNotApprovedException(customerAccountNumber);
        }
        Company company = companyRepository.getCompanyByName(companyName);
        //Checks if the company introduced exists or not
        if (company == null){
            throw new CompanyDoesNotExistException(companyName);
        }
        double purchasePrice = company.getStockPrice() * quantity;

        // The central code for this use case, which sells shares.
        Date now = new Date();

        addCreditTransaction(customerAccountNumber, purchasePrice, now);
        creditBalance(customerAccountNumber, purchasePrice);
        removeStock(customerSSN, companyName, quantity);

        return "Sale made successfully.";
    }

    // We created these three private methods to make the execute method more understandable.
    // We do not want to use these methods onwards, just in this class.
    //Creates the credit transaction for the operation
    private void addCreditTransaction(long accountNumber, double price, Date currentDate){
        Transaction transaction = new Transaction(accountNumber, accountNumber, price, TransactionType.credit, currentDate);
        transactionRepository.createTransaction(transaction);
    }

    //Adds the benefit done from the selling to the customer's specified bank account.
    private void creditBalance(long accountNumber, double price){
        BankAccount customerBankAccount = bankAccountRepository.getAccountByAccountNumber(accountNumber);
        customerBankAccount.setBalance(customerBankAccount.getBalance() + price);
        bankAccountRepository.updateBankAccount(customerBankAccount); //checker
    }

    //Removes the stock from the portfolio after selling
    private void removeStock(long customerSSN, String companyName, int quantity) throws Exception {
        Portfolio portfolio = portfolioRepository.getPortfolioBySSN(customerSSN);
        if (portfolio == null) {
            throw new PortfolioDoesNotExistException();
        }
        ArrayList<Stock> stocks = portfolioRepository.getStocksByCompanyName(customerSSN, companyName);
        int stockQuantity = 0;
        for (Stock currentStock : stocks){
            stockQuantity += currentStock.getQuantity();
        }
        //Checks if the quantity we are trying to sell oversizes the stocks in the portfolio or not.
        if (quantity > stockQuantity){
            throw new PortfolioDoesNotHaveEnoughStocks();
        }

        int soldQuantity = 0;
        ArrayList<Stock> stocksToDelete = new ArrayList<>();
        for (Stock currentStock : portfolio.getStocks()){
            if (currentStock.getCompany().equals(companyName)){
                if (currentStock.getQuantity() <= (quantity - soldQuantity)){
                    stocksToDelete.add(currentStock);
                    soldQuantity += currentStock.getQuantity();
                }
                else {
                    currentStock.setQuantity(currentStock.getQuantity() - quantity + soldQuantity);
                }
            }
        }
        if (stocksToDelete.size() > 0){
            for (Stock stock : stocksToDelete){
                portfolio.getStocks().remove(stock);
            }
        }
        portfolioRepository.updatePortfolio(portfolio);
    }

}