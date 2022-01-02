package controllers;

import domain.entities.*;
import usecases.*;

import java.util.ArrayList;

public class CustomerMenu {
    private LogOutCustomer logOutCustomer;
    private CheckBalance checkBalance;
    private ApplyForBankAccount applyForBankAccount;
    private ApplyForMortgage applyForMortgage;
    private TransferMoneyToAnotherAccount transferMoneyToAnotherAccount;
    private DepositMoney depositMoney;
    private WithdrawMoney withdrawMoney;
    private ObtainCustomerBankAccounts obtainCustomerBankAccounts;
    private CheckTransactionHistory checkTransactionHistory;
    private BuyShares buyShares;
    private ObtainCompanies obtainCompanies;
    private ShareholderMenu shareholderMenu;

    public static final String EOL = System.lineSeparator();

    public CustomerMenu() {
        this.logOutCustomer = new LogOutCustomer();
        this.transferMoneyToAnotherAccount = new TransferMoneyToAnotherAccount();
        this.depositMoney = new DepositMoney();
        this.withdrawMoney = new WithdrawMoney();
        this.checkBalance = new CheckBalance();
        this.applyForMortgage = new ApplyForMortgage();
        this.applyForBankAccount = new ApplyForBankAccount();
        this.obtainCustomerBankAccounts = new ObtainCustomerBankAccounts();
        this.checkTransactionHistory = new CheckTransactionHistory();
        this.buyShares = new BuyShares();
        this.obtainCompanies = new ObtainCompanies();
        this.shareholderMenu = new ShareholderMenu();
    }

    public void printMenu() {

        System.out.println("Customer menu:" + System.lineSeparator() +
                "0. Log Out" + EOL +
                "1. Transfer Money" + EOL +
                "2. Deposit Money" + EOL +
                "3. Withdraw Money" + EOL +
                "4. Check Balance" + EOL +
                "5. Apply for Mortgage" + EOL +
                "6. Apply for Bank Account" + EOL +
                "7. List of Bank Accounts" + EOL +
                "8. Check Transaction History" + EOL +
                "9. Buy Shares" + EOL +
                "10. List companies with stocks" + EOL +
                "Type an option number: ");

    }

    public void menu(int option, Customer customer) {
        do {
            switch (option) {

                case 0:
                    try {
                        String accessToken = customer.getAccessToken();
                        logOutCustomer.execute(accessToken);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 1:
                    try {
                        long originSSN = customer.getSSN();
                        long originAccountNumber = UserInput.inputLong(" Enter sender's Account Number: ");
                        long finalSSN = UserInput.inputLong(" Enter receiver's SSN: ");
                        long finalAccountNumber = UserInput.inputLong(" Enter receiver's Account Number: ");
                        double amount = UserInput.inputDouble(" Enter Amount(e.g 100,07): ");

                        double balance = transferMoneyToAnotherAccount.execute(originSSN, originAccountNumber, finalSSN, finalAccountNumber, amount);
                        System.out.println("Your balance after the operation is: " + balance);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;

                case 2:
                    try {
                        long SSN = customer.getSSN();
                        long accountNumber = UserInput.inputLong(" Enter Account Number: ");
                        double amount = UserInput.inputDouble(" Enter Amount(e.g 100,07): ");

                        double balance = depositMoney.execute(SSN, accountNumber, amount);
                        System.out.println("Your balance after the operation is: " + balance);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;

                case 3:
                    try {
                        long SSN = customer.getSSN();
                        long accountNumber = UserInput.inputLong(" Enter Account Number: ");
                        double amount = UserInput.inputDouble(" Enter Amount(e.g 100,07): ");

                        double balance = withdrawMoney.execute(SSN, accountNumber, amount);
                        System.out.println("Your balance after the operation is: " + balance);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;

                case 4:
                    try {
                        long SSN = customer.getSSN();
                        long accountNumber = UserInput.inputLong(" Enter Account Number: ");

                        double balance = checkBalance.execute(SSN, accountNumber);
                        System.out.println("Your balance is: " + balance + " SEK.");

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;

                case 5:
                    try {
                        long SSN = customer.getSSN();
                        long accountNumber = UserInput.inputLong(" Enter account number: ");
                        double totalMortgageValue = UserInput.inputDouble("Enter the total amount of the mortgage(e.g 100,07): ");
                        double years = UserInput.inputDouble("Enter amount of years to pay off mortgage(e.g 2,3): ");
                        double initialDeposit = UserInput.inputDouble(" Enter the initial deposit(e.g 100,07): ");

                        String message = applyForMortgage.execute(SSN, accountNumber, totalMortgageValue, years, initialDeposit);
                        System.out.println(message);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;

                case 6:
                    try {
                        long SSN = customer.getSSN();
                        String accountName = UserInput.inputString("Enter the bank account name: ");

                        String message = applyForBankAccount.execute(SSN, accountName);
                        System.out.println(message);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;

                case 7:
                    try {
                        long SSN = customer.getSSN();
                        ArrayList<BankAccount> customerBankAccounts = obtainCustomerBankAccounts.execute(SSN);
                        if (customerBankAccounts.size() > 0){
                            for (BankAccount currentBankAccount : customerBankAccounts) {
                                System.out.println(currentBankAccount.toString());
                            }
                        }
                        else{
                            System.out.println("No bank accounts registered yet.");
                        }
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;

                case 8:
                    try {
                        long SSN = customer.getSSN();
                        long accountNumber = UserInput.inputLong("Enter the number of bank account: ");
                        ArrayList<Transaction> transactionsList = checkTransactionHistory.execute(SSN, accountNumber);
                        if (transactionsList.size() > 0){
                            for (Transaction currentTransaction : transactionsList) {
                                System.out.println(currentTransaction.toString());
                            }
                        }
                        else {
                            System.out.println("No transactions registered yet for this account.");
                        }
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;

                case 9:
                    try{
                        String companyName = UserInput.inputString("Enter the name of the company: ");
                        int quantity = UserInput.inputInt("Enter the number of stocks you desire to buy: ");
                        long customerSSN = customer.getSSN();
                        long customerAccountNumber = UserInput.inputLong("Enter the account number: ");
                        String message = buyShares.execute(companyName, quantity, customerSSN, customerAccountNumber);
                        System.out.println(message);

                        //Goes to the shareholder menu since by buying shares his/her type automatically changed to a shareholder.
                        shareholderMenu.printMenu();
                        option = UserInput.inputInt("Enter an option: ");
                        //We need to pass a Shareholder object so we cast downcast the already obtained customer through the menu
                        Shareholder shareholder = new Shareholder(
                                customer.getFirstName(),
                                customer.getLastName(),
                                customer.getSSN(),
                                customer.getPassword(),
                                customer.getEmail(),
                                customer.getPhoneNumber(),
                                customer.getBirthDate()
                        );
                        shareholderMenu.menu(option, shareholder);
                    }
                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;

                case 10:
                    try{
                        ArrayList<Company> companiesList = obtainCompanies.execute();
                        for (Company currentCompany : companiesList){
                            System.out.println(currentCompany.toString());
                        }
                    }
                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;

                default:
                    System.out.println("Please enter valid option!");
                    option = UserInput.inputInt("Enter an option: ");
                    break;
            }

        } while (option != 0);
    }
}
