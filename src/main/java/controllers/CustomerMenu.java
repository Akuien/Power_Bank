package controllers;

import.domain.entities.BankAccount;
import domain.entities.Customer;
import domain.entities.Transaction;
import repositories.BankAccountRepository;
import usecases.*;

import java.util.ArrayList;

public class CustomerMenu {
    private LogOutCustomer logOutCustomer;
    private CheckBalance checkBalance;
    private ApplyForBankAccount applyForBankAccount;
    private AppleForMortgage appleForMortgage;
    private TransferMoneyToAnotherAccount transferMoneyToAnotherAccount;
    private DepositMoney depositMoney;
    private WithdrawMoney withdrawMoney;
    private ObtainCustomerBankAccounts obtainCustomerBankAccounts;
    private CheckTransactionHistory checkTransactionHistory;

    public static final String EOL = System.lineSeparator();

    public CustomerMenu(){
        this.logOutCustomer = new LogOutCustomer();
        this.transferMoneyToAnotherAccount = new TransferMoneyToAnotherAccount();
        this.depositMoney = new DepositMoney();
        this.withdrawMoney = new WithdrawMoney();
        this.checkBalance = new CheckBalance();
        this.appleForMortgage = new ApplyForMortgage();
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
                // Dot or no dot? End of each option text.

    }

    public void menu(int option, Customer customer){
        do {
            switch(option){ //Switch-case menu

                case 0:
                    try {
                        String accessToken = customer.getAccessToken();
                        logOutCustomer.execute(accessToken);
                    }
                    catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 1: // up to case 8.

                    }
            }
        }while(option != 0);
    }

}