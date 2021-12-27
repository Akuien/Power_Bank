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
    private BankAccountRepository bankAccountRepository;
    private PortfolioRepository portfolioRepository;
    private TransactionRepository transactionRepository;
    private CompanyRepository companyRepository;

    public SellShares() {
        this.validateShareholder = new ValidateShareholder();
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.bankAccountRepository = new BankAccountRepository();
        this.portfolioRepository = new PortfolioRepository();
        this.transactionRepository = new TransactionRepository();
        this.companyRepository = new CompanyRepository();
    }

    public String execute(String companyName, int quantity, long customerSSN, long customerAccountNumber) throws Exception {
        // Below are validations, which check the requirements and throw exceptions to stop code from executing.
        boolean shareholderExists = validateShareholder.execute(customerSSN);
        if (!shareholderExists){
            throw new ShareholderDoesNotExistException(customerSSN);
        }
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

        // The central code for this use case, which sells shares.
        Date now = new Date();

        addCreditTransaction(customerAccountNumber, purchasePrice, now);
        creditBalance(customerAccountNumber, purchasePrice);
        removeStock(customerSSN, companyName, quantity);

        return "Sale made successfully.";
    }

    // We created these three private methods to make the execute method more understandable.
    // We do not want to use these methods onwards, just in this class.
    private void addCreditTransaction(long accountNumber, double price, Date currentDate){
        Transaction transaction = new Transaction(accountNumber, accountNumber, price, TransactionType.debit, currentDate);
        transactionRepository.createTransaction(transaction);
    }

    private void creditBalance(long accountNumber, double price){
        BankAccount customerBankAccount = bankAccountRepository.getAccountByAccountNumber(accountNumber);
        customerBankAccount.setBalance(customerBankAccount.getBalance() + price);
        bankAccountRepository.updateBankAccount(customerBankAccount); //checker
    }

    private void removeStock(long customerSSN, String companyName, int quantity) throws Exception {
        Portfolio portfolio = portfolioRepository.getPortfolioBySSN(customerSSN);
        if (portfolio == null) {
            throw new PortfolioDoesNotExistException();
        }
        if (quantity > portfolioRepository.getStocksByCompanyName(customerSSN, companyName).size()){
            throw new PortfolioDoesNotHaveEnoughStocks();
        }
        for (Stock currentStock : portfolio.getStocks()){
            if (currentStock.getCompany().equals(companyName)){
                if (currentStock.getQuantity() == quantity){
                    portfolio.getStocks().remove(currentStock);
                }
                else {
                    currentStock.setQuantity(currentStock.getQuantity()-quantity);
                }
            }
        }
        portfolioRepository.updatePortfolio(portfolio);
    }

}