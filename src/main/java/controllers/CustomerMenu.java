package controllers;

import domain.entities.BankAccount;
import domain.entities.Customer;
import domain.entities.Transaction;
import repositories.BankAccountRepository;
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
                        double amount = UserInput.inputLong(" Enter Amount: ");

                        double balance = transferMoneyToAnotherAccount.execute(originSSN, originAccountNumber, finalSSN, finalAccountNumber, amount);
                        System.out.println("Your balance after the operation is: " + balance);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 2:
                    try {
                        long SSN = customer.getSSN();
                        long accountNumber = UserInput.inputInt(" Enter Account Number: ");
                        double amount = UserInput.inputDouble(" Enter Amount: ");

                        double balance = depositMoney.execute(SSN, accountNumber, amount);
                        System.out.println("Your balance after the operation is: " + balance);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 3:
                    try {
                        long SSN = customer.getSSN();
                        long accountNumber = UserInput.inputLong(" Enter Account Number: ");
                        double amount = UserInput.inputDouble(" Enter Amount: ");

                        double balance = withdrawMoney.execute(SSN, accountNumber, amount);
                        System.out.println("Your balance after the operation is: " + balance);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 4:
                    try {
                        long SSN = customer.getSSN();
                        long accountNumber = UserInput.inputLong(" Enter Account Number: ");

                        double balance = checkBalance.execute(SSN, accountNumber);
                        System.out.println("Your balance is: " + balance);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 5:
                    try {
                        long SSN = customer.getSSN();
                        long accountNumber = UserInput.inputLong(" Enter account number: ");
                        double totalMortgageValue = UserInput.inputDouble("Enter the total amount of the mortgage: ");
                        double years = UserInput.inputDouble("Enter amount of years to pay off mortgage: ");
                        double initialDeposit = UserInput.inputDouble(" Enter the initial deposit: ");

                        String message = applyForMortgage.execute(SSN, accountNumber, totalMortgageValue, years, initialDeposit);
                        System.out.println(message);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
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
                    break;

                case 7:
                    try {
                        long SSN = customer.getSSN();
                        ArrayList<BankAccount> customerBankAccounts = obtainCustomerBankAccounts.execute(SSN);
                        for (BankAccount currentBankAccount : customerBankAccounts) {
                            System.out.println(currentBankAccount.toString());
                        }
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 8:
                    try {
                        long SSN = customer.getSSN();
                        long accountNumber = UserInput.inputLong("Enter the number of bank account: ");
                        ArrayList<Transaction> transactionsList = checkTransactionHistory.execute(SSN, accountNumber);
                        for (Transaction currentTransaction : transactionsList) {
                            System.out.println(currentTransaction.toString());
                        }
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                default:
                    System.out.println("Please enter valid option!");
                    option = UserInput.inputInt("Enter an option: ");
                    break;
            }

        } while (option != 0);
    }
}
