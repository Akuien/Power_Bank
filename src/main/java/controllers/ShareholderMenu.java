package controllers;
import domain.entities.*;
import usecases.*;

import usecases.BuyShares;
import usecases.SellShares;

import java.util.ArrayList;

public class ShareholderMenu {
    private LogOutCustomer logOutCustomer;
    private CheckBalance checkBalance;
    private ApplyForBankAccount applyForBankAccount;
    private ApplyForMortgage applyForMortgage;
    private TransferMoneyToAnotherAccount transferMoneyToAnotherAccount;
    private DepositMoney depositMoney;
    private WithdrawMoney withdrawMoney;
    private ObtainCustomerBankAccounts obtainCustomerBankAccounts;
    private CheckTransactionHistory checkTransactionHistory;
    private ObtainShareholderPortfolio obtainShareholderPortfolio;
    private BuyShares buyShares;
    private SellShares sellShares;
    private ObtainCompanies obtainCompanies;
    private ObtainOwnedStocks obtainOwnedStocks;

    public static final String EOL = System.lineSeparator();

    public ShareholderMenu(){
        this.logOutCustomer = new LogOutCustomer();
        this.transferMoneyToAnotherAccount = new TransferMoneyToAnotherAccount();
        this.depositMoney = new DepositMoney();
        this.withdrawMoney = new WithdrawMoney();
        this.checkBalance = new CheckBalance();
        this.applyForMortgage = new ApplyForMortgage();
        this.applyForBankAccount = new ApplyForBankAccount();
        this.obtainCustomerBankAccounts = new ObtainCustomerBankAccounts();
        this.checkTransactionHistory = new CheckTransactionHistory();
        this.obtainShareholderPortfolio = new ObtainShareholderPortfolio();
        this.buyShares = new BuyShares();
        this.sellShares = new SellShares();
        this.obtainCompanies = new ObtainCompanies();
        this.obtainOwnedStocks = new ObtainOwnedStocks();

    }

    public void printMenu() {

        System.out.println("Customer menu:" + System.lineSeparator() +
                "0. Log Out." + EOL +
                "1. Transfer Money." + EOL +
                "2. Deposit Money." + EOL +
                "3. Withdraw Money." + EOL +
                "4. Check Balance." + EOL +
                "5. Apply for Mortgage" + EOL +
                "6. Apply for Bank Account" + EOL +
                "7. List of bank accounts " + EOL +
                "8. Look at transaction history" + EOL +
                "9. Buy shares" + EOL +
                "10. Sell shares" + EOL +
                "11. List companies with stocks" + EOL +
                "12. Check portfolio" + EOL +
                "Type an option number: ");
    }

    public void menu(int option, Shareholder shareholder){
        do {
            switch(option){

                case 0 :
                    try {
                        String accessToken = shareholder.getAccessToken();
                        logOutCustomer.execute(accessToken);
                    }
                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 1 :
                    try {
                        long originSSN = shareholder.getSSN();
                        long originAccountNumber = UserInput.inputLong(" Enter sender's Account Number: ");
                        long finalSSN = UserInput.inputLong(" Enter receiver's SSN: ");
                        long finalAccountNumber = UserInput.inputLong(" Enter receiver's Account Number: ");
                        double amount = UserInput.inputDouble(" Enter Amount(e.g 100,07): ");

                        double balance = transferMoneyToAnotherAccount.execute(originSSN, originAccountNumber, finalSSN,  finalAccountNumber,  amount);
                        System.out.println("Your balance after the operation is: " + balance);

                    } catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 2 :
                    try {
                        long SSN = shareholder.getSSN();
                        long accountNumber = UserInput.inputLong(" Enter Account Number: ");
                        double amount = UserInput.inputDouble(" Enter Amount(e.g 100,07): ");

                        double balance = depositMoney.execute(SSN, accountNumber, amount);
                        System.out.println("Your balance after the operation is: " + balance);

                    } catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 3 :
                    try {
                        long SSN = shareholder.getSSN();
                        long accountNumber = UserInput.inputLong(" Enter Account Number: ");
                        double amount = UserInput.inputDouble(" Enter Amount(e.g 100,07): ");

                        double balance = withdrawMoney.execute(SSN, accountNumber, amount);
                        System.out.println("Your balance after the operation is: " + balance);

                    } catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 4 :
                    try {
                        long SSN = shareholder.getSSN();
                        long accountNumber = UserInput.inputLong(" Enter Account Number: ");

                        double balance = checkBalance.execute(SSN, accountNumber);
                        System.out.println("Your balance is: " + balance);

                    } catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 5 :
                    try {
                        long SSN = shareholder.getSSN();
                        long accountNumber = UserInput.inputLong(" Enter account number: ");
                        double totalMortgageValue = UserInput.inputDouble("Enter the total amount of the property(e.g 100,07): ");
                        double years = UserInput.inputDouble("Enter the years to pay the property(e.g 2,3): ");
                        double initialDeposit = UserInput.inputDouble(" Enter the initial deposit(e.g 100,07): ");

                        String message = applyForMortgage.execute(SSN, accountNumber, totalMortgageValue, years, initialDeposit);
                        System.out.println(message);

                    } catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 6 :
                    try {
                        long SSN = shareholder.getSSN();
                        String accountName = UserInput.inputString("Enter the bank account name: ");

                        String message = applyForBankAccount.execute(SSN, accountName);
                        System.out.println(message);

                    }
                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 7 :
                    try{
                        long SSN = shareholder.getSSN();
                        ArrayList<BankAccount> customerBankAccounts = obtainCustomerBankAccounts.execute(SSN);
                        for (BankAccount currentBankAccount : customerBankAccounts){
                            System.out.println(currentBankAccount.toString());
                        }
                    }
                    catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 8 :
                    try{
                        long SSN = shareholder.getSSN();
                        long accountNumber = UserInput.inputLong("Enter the bank account number: ");
                        ArrayList<Transaction> transactionsList = checkTransactionHistory.execute(SSN,accountNumber);
                        for (Transaction currentTransaction : transactionsList){
                            System.out.println(currentTransaction.toString());
                        }
                    }
                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 9:
                    try{
                        String companyName = UserInput.inputString("Enter the name of the company: ");
                        int quantity = UserInput.inputInt("Enter the number of stocks you desire to buy: ");
                        long customerSSN = shareholder.getSSN();
                        long customerAccountNumber = UserInput.inputLong("Enter the account number: ");
                        String message = buyShares.execute(companyName, quantity, customerSSN, customerAccountNumber);
                        System.out.println(message);
                    }
                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 10:
                    try{
                        String companyName = UserInput.inputString("Enter the name of the company: ");
                        int quantity = UserInput.inputInt("Enter the number of stocks you desire to sell: ");
                        long customerSSN = shareholder.getSSN();
                        long customerAccountNumber = UserInput.inputLong("Enter the account number: ");
                        String message = sellShares.execute(companyName, quantity, customerSSN, customerAccountNumber);
                        System.out.println(message);
                    }
                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 11:
                    try{
                        ArrayList<Company> companiesList = obtainCompanies.execute();
                        for (Company currentCompany : companiesList){
                            System.out.println(currentCompany.toString());
                        }
                    }
                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 12:
                    try{
                        long SSN = shareholder.getSSN();
                        ArrayList<Stock> ownedStocks = obtainOwnedStocks.execute(SSN);
                        for (Stock currentStock : ownedStocks){
                            System.out.println(currentStock.toString());
                        }
                    }
                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    break;

                default:
                    System.out.println("Please enter valid option");
                    option = UserInput.inputInt("Enter an option: ");
                    break;
            }

        }while(option != 0);
    }

}
