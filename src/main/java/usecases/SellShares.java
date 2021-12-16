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

    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private BankAccountRepository bankAccountRepository;
    private PortfolioRepository portfolioRepository;
    private TransactionRepository transactionRepository;
    private CompanyRepository companyRepository;

    public SellShares() {
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.bankAccountRepository = new BankAccountRepository();
        this.portfolioRepository = new PortfolioRepository();
        this.transactionRepository = new TransactionRepository();
        this.companyRepository = new CompanyRepository();
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

        return "Sale made successfully.";
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

}