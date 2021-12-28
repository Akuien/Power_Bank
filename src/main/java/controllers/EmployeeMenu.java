package controllers;

import data.PersistenceData;
import domain.constants.MortgageStatus;
import domain.entities.BankAccount;
import domain.entities.Customer;
import domain.entities.Employee;
import domain.entities.Mortgage;
import usecases.*;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static controllers.CustomerMenu.EOL;

public class EmployeeMenu {

    private LogOutEmployee logOutEmployee;
    private ObtainCustomers obtainCustomers;
    private ObtainCustomerBankAccounts obtainCustomerBankAccounts;
    private ObtainPendingMortgages obtainPendingMortgages;
    private ObtainPendingBankAccounts obtainPendingBankAccounts;
    private ChangeStatusMortgage changeStatusMortgage;
    private ChangeStatusBankAccount changeStatusBankAccount;
    private CreateBankAccount createBankAccount;

    public EmployeeMenu(){
        this.logOutEmployee = new LogOutEmployee();
        this.obtainCustomers = new ObtainCustomers();
        this.obtainCustomerBankAccounts = new ObtainCustomerBankAccounts();
        this.obtainPendingMortgages = new ObtainPendingMortgages();
        this.obtainPendingBankAccounts = new ObtainPendingBankAccounts();
        this.changeStatusMortgage = new ChangeStatusMortgage();
        this.changeStatusBankAccount = new ChangeStatusBankAccount();
        this.createBankAccount = new CreateBankAccount();
    }

    public void printMenu() {


        System.out.println("Employee menu:" + System.lineSeparator() +
                "0. Log Out." + EOL +
                "1. List customers" + EOL +
                "2. List customer's bank account(s)" + EOL +
                "3. List mortgage applications." + EOL +
                "4. List bank account applications." + EOL +
                "5. Change status mortgage." + EOL +
                "6. Change status bank account." + EOL +
                "7. Create customer bank account (customer at office)" + EOL +
                "Type an option number: ");
    }

    public void menu(int option, Employee employee) {

        do {
            switch (option) {
                //In order for the user to log out, first we call the access token from employee class, and then logOutEmployee class.
                case 0:
                    try {
                        String accessToken = employee.getAccessToken();
                        logOutEmployee.execute(accessToken);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;
                // Customer list address is now pointing to obtainCustomers execute method,
                // and then the for loop loops through the list and print out every customer.
                case 1:
                    try {
                        ArrayList<Customer> customersList = obtainCustomers.execute();
                        for (Customer currentCustomer : customersList){
                            System.out.println(currentCustomer.toString());
                        }

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;
                // take input from the user, and loop through all the bank accounts in the list and print them out.
                case 2:
                    try {
                        long customerSSN = UserInput.inputLong("Enter the customer's SSN: ");
                        ArrayList<BankAccount> customersBankAccounts = obtainCustomerBankAccounts.execute(customerSSN);
                        for (BankAccount currentBankAccount : customersBankAccounts){
                            System.out.println(currentBankAccount.toString());
                        }
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;
                // loop through the list of pending mortgages and print each one of them.
                case 3:
                    try{
                        ArrayList<Mortgage> pendingMortgages = obtainPendingMortgages.execute();
                        for (Mortgage currentMortgage : pendingMortgages){
                            System.out.println(currentMortgage.toString());
                        }
                    }
                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;
                // loop through the list of pending bank accounts and print them out.
                case 4:
                    try{
                        ArrayList<BankAccount>pendingBankAccounts = obtainPendingBankAccounts.execute();
                        for (BankAccount currentBankAccount : pendingBankAccounts){
                            System.out.println(currentBankAccount.toString());
                        }
                    }
                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;
                // entering all the values for changing the mortgage status, and then calling the change mortgage status class.
                case 5:
                    try {
                        long customerSSN = UserInput.inputLong("Enter the customer's SSN: ");
                        long accountNumber = UserInput.inputLong("Enter the customer's bank account number: ");
                        long mortgageLoanID = UserInput.inputInt("Enter the mortgage ID: ");
                        long employeeSSN = employee.getSSN();
                        String choice = UserInput.inputString("Enter the next status of the mortgage (Accepted/Rejected)");

                        String message = changeStatusMortgage.execute(customerSSN, accountNumber, mortgageLoanID, employeeSSN, choice);
                        System.out.println(message);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;
                // entering all the values for changing the bank account status, and the calling change bank account status class.
                case 6:
                    try {
                        long customerSSN = UserInput.inputLong("Enter the customer's SSN: ");
                        long accountNumber = UserInput.inputLong("Enter the account number of the desired bank account: ");
                        long employeeSSN = employee.getSSN();
                        String choice = UserInput.inputString("Enter the next status of the bank account (Accepted/Rejected)");

                        String message = changeStatusBankAccount.execute(accountNumber, customerSSN, employeeSSN, choice);
                        System.out.println(message);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;

                case 7:
                    try{
                        long employeeSSN = employee.getSSN();
                        long customerSSN = UserInput.inputLong("Enter the customer's SSN: ");
                        String bankAccountName = UserInput.inputString("Enter the name of the bank account: ");
                        createBankAccount.execute(employeeSSN, customerSSN, bankAccountName);
                    }

                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }

                    printMenu();
                    option = UserInput.inputInt("Enter option: ");

                    break;

                // if user enters anything above 6, below 0 or not a digit, then the system will ask the user to print out a valid option.
                default:
                    System.out.println("Please enter valid option");
                    option = UserInput.inputInt("Enter an option: ");
                    break;
            }
        } while (option != 0);
    }
}




