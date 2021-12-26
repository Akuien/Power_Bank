package controllers;


import data.PersistenceData;
import domain.constants.MortgageStatus;
import domain.constants.UserType;
import domain.entities.*;
import usecases.*;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static controllers.CustomerMenu.EOL;

public class ManagerMenu {

    private LogOutEmployee logOutEmployee;
    private ObtainCustomers obtainCustomers;
    private ObtainCustomerBankAccounts obtainCustomerBankAccounts;
    private ObtainPendingMortgages obtainPendingMortgages;
    private ObtainPendingBankAccounts obtainPendingBankAccounts;
    private ObtainEmployees obtainEmployees;
    private ChangeStatusMortgage changeStatusMortgage;
    private ChangeStatusBankAccount changeStatusBankAccount;
    private PromoteEmployee promoteEmployee;
    private RegisterEmployee registerEmployee;
    private RegisterManager registerManager;
    private CreateBankAccount createBankAccount;

    public ManagerMenu() {
        this.logOutEmployee = new LogOutEmployee();
        this.obtainCustomers = new ObtainCustomers();
        this.obtainCustomerBankAccounts = new ObtainCustomerBankAccounts();
        this.obtainPendingMortgages = new ObtainPendingMortgages();
        this.obtainPendingBankAccounts = new ObtainPendingBankAccounts();
        this.obtainEmployees = new ObtainEmployees();
        this.changeStatusMortgage = new ChangeStatusMortgage();
        this.changeStatusBankAccount = new ChangeStatusBankAccount();
        this.promoteEmployee = new PromoteEmployee();
        this.registerEmployee = new RegisterEmployee();
        this.registerManager = new RegisterManager();
        this.createBankAccount = new CreateBankAccount();
    }

    public void printMenu() {
        // Prints the menu.
        System.out.println("Employee menu:" + System.lineSeparator() +
                "0. Log Out." + EOL +
                "1. List customers" + EOL +
                "2. List customer's bank account(s)" + EOL +
                "3. List mortgage applications." + EOL +
                "4. List bank account applications." + EOL +
                "5. List employees." + EOL +
                "6. Change status mortgage." + EOL +
                "7. Change status bank account." + EOL +
                "8. Promote employee." + EOL +
                "9. Create employee account" + EOL +
                "10. Create manager account" + EOL +
                "11. Create customer bank account (customer at office)");
    }

    public void menu(int option, Manager manager) {


        do {
            switch (option) {

                case 0:
                    try {
                        String accessToken = manager.getAccessToken();
                        logOutEmployee.execute(accessToken);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 1:
                    try {
                        ArrayList<Customer> customersList = obtainCustomers.execute();
                        for (Customer currentCustomer : customersList) {
                            System.out.println(currentCustomer.toString());
                        }

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 2:
                    try {
                        long customerSSN = UserInput.inputLong("Introduce the customer's SSN: ");
                        ArrayList<BankAccount> customersBankAccounts = obtainCustomerBankAccounts.execute(customerSSN);
                        for (BankAccount currentBankAccount : customersBankAccounts) {
                            System.out.println(currentBankAccount.toString());
                        }
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 3:
                    try {
                        ArrayList<Mortgage> pendingMortgages = obtainPendingMortgages.execute();
                        for (Mortgage currentMortgage : pendingMortgages) {
                            System.out.println(currentMortgage.toString());
                        }
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 4:
                    try {
                        ArrayList<BankAccount> pendingBankAccounts = obtainPendingBankAccounts.execute();
                        for (BankAccount currentBankAccount : pendingBankAccounts) {
                            System.out.println(currentBankAccount.toString());
                        }
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 5:
                    try {
                        ArrayList<Employee> employeesList = obtainEmployees.execute();
                        for (Employee currentEmployee : employeesList) {
                            System.out.println(currentEmployee.toString());
                        }
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }


                case 6:
                    try {
                        long customerSSN = UserInput.inputLong("Enter the customer's SSN: ");
                        long accountNumber = UserInput.inputLong("Enter the customer's bank account number: ");
                        long mortgageLoanID = UserInput.inputInt("Enter the mortgage ID: ");
                        long managerSSN = manager.getSSN();
                        String choice = UserInput.inputString("Enter the next status of the mortgage (Accepted/Rejected)");

                        String message = changeStatusMortgage.execute(customerSSN, accountNumber, mortgageLoanID, managerSSN, choice);
                        System.out.println(message);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 7:
                    try {
                        long customerSSN = UserInput.inputLong("Enter the customer's SSN: ");
                        long accountNumber = UserInput.inputLong("Enter the account number of the desired bank account: ");
                        long managerSSN = manager.getSSN();
                        String choice = UserInput.inputString("Enter the next status of the bank account (Accepted/Rejected)");

                        String message = changeStatusBankAccount.execute(accountNumber, customerSSN, managerSSN, choice);
                        System.out.println(message);

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 8:
                    try{
                        long managerSSN = manager.getSSN();
                        long employeeSSN = UserInput.inputLong("Enter the employee's SSN: ");
                        String message = promoteEmployee.execute(managerSSN, employeeSSN);
                        System.out.println(message);
                    }
                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }

                case 9:
                    try {
                        long managerSSN = manager.getSSN();
                        String firstName = UserInput.inputString("Enter First Name: ");
                        String lastName = UserInput.inputString("Enter Last Name: ");
                        long SSN = UserInput.inputLong("Enter SSN: ");
                        String password = UserInput.inputString("Enter Password: ");
                        String email = UserInput.inputString("Enter Email: ");
                        String phoneNumber = UserInput.inputString("Enter Phone Number");
                        String birthDateString = UserInput.inputString("Enter Birth Date");
                        Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthDateString);

                        String message = registerEmployee.execute(firstName, lastName, SSN, password,email, phoneNumber, birthDate, managerSSN);

                        System.out.println(message);

                    }
                    catch (Exception exception) {
                        System.out.println("Information introduced is not correct.");
                        System.out.println("Please introduce again the correct information.");
                    }

                case 10:
                    try {
                        long managerSSN = manager.getSSN();
                        String firstName = UserInput.inputString("Enter First Name: ");
                        String lastName = UserInput.inputString("Enter Last Name: ");
                        long newManagerSSN = UserInput.inputLong("Enter SSN: ");
                        String password = UserInput.inputString("Enter Password: ");
                        String email = UserInput.inputString("Enter Email: ");
                        String phoneNumber = UserInput.inputString("Enter Phone Number");
                        String birthDateString = UserInput.inputString("Enter Birth Date");
                        Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthDateString);

                        String message = registerManager.execute(firstName, lastName, newManagerSSN, managerSSN, password, email, phoneNumber, birthDate);

                        System.out.println(message);

                    }
                    catch (Exception exception) {
                        System.out.println("Information introduced is not correct.");
                        System.out.println("Please introduce again the correct information.");
                    }

                case 11:
                    try{
                        long managerSSN = manager.getSSN();
                        long customerSSN = UserInput.inputLong("Enter the customer's SSN: ");
                        String bankAccountName = UserInput.inputString("Enter the name of the bank account: ");
                        createBankAccount.execute(managerSSN, customerSSN, bankAccountName);
                    }

                    catch (Exception exception){
                        System.out.println(exception.getMessage());
                    }


                default:
                    System.out.println("Please enter valid option");
                    option = UserInput.inputInt("Enter an option: ");
                    break;
            }

        } while (option != 0);
    }
}