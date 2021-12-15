package controllers;

import data.PersistenceData;
import domain.constants.MortgageStatus;
import domain.entities.Customer;
import domain.entities.Mortgage;
import usecases.*;


import java.util.Date;

import static controllers.CustomerMenu.EOL;

public class EmployeeMenu {
    private LogInEmployee logInEmployee;
    private LogOutEmployee logOutEmployee;
    private RegisterCustomer registerCustomer;
    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private CustomerMenu customerMenu;
    private ValidateEmail validateEmail;
    private CheckTransactionHistory checkTransactionHistory;
    private PersistenceData listOfCustomers;


    public void employeeMenu(){



        int option = UserInput.inputInt("Employee menu:" + System.lineSeparator()+
                "0. Log Out." + EOL +
                "1. Register Customer." + EOL +
                "2. Validate customer." + EOL +
                "3. Validate Customer bank account." + EOL +
                "4. Approve mortgage." + EOL +
                "5. Customer Menu." + EOL +
                "6. Validate Email.  " + EOL +
                "7. Look at transaction history." + EOL +
                "8. " + EOL +
                "Type an option number: ");

        while(option < 0 || option > 8){

            option = UserInput.inputInt("Invalid option");



        }switch(option){

            case 0:
                try{
                    String accessToken = UserInput.inputString(" ");
                    logOutEmployee.execute(accessToken);
                }
                catch (Exception exception){
                    System.out.println(exception.getMessage());

                }
                break;
            case 1:
                try{
                    String firstName = UserInput.inputString("Enter the first name of the customer: ");
                    String lastName = UserInput.inputString("Enter the last name of the customer: ");
                    long ssn = UserInput.inputLong("Enter ssn of the customer: ");
                    String password = UserInput.inputString("Enter the password of the customer: ");
                    String email = UserInput.inputString("Enter the email of the customer: ");
                    String phoneNumber = UserInput.inputString("Enter the password of the customer: ");
                    //Date birthdate = UserInput.inputObject("Enter the password of the customer: ");

                    registerCustomer.execute(firstName, lastName, ssn, password, email, phoneNumber, /*birthdate*/);


                }
                catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
                break;
            case 2:
                try{
                    long ssn = UserInput.inputLong("Enter ssn of the customer: ");

                    validateCustomer.execute(ssn);
                }
                catch(Exception exception){
                    System.out.println(exception.getMessage());
                }
                break;
            case 3:
                try{
                    long ssn = UserInput.inputLong("Enter ssn of the customer: ");
                    long bankAcc = UserInput.inputLong("Enter bank account of the customer: ");
                    validateCustomerBankAccount.execute(ssn, bankAcc);
                }
                catch(Exception exception){
                    System.out.println(exception.getMessage());
                }
                break;
            case 4:
                try{
                    /*Code semantics: If there exists a customer with the entered id, and if his mortgage status is pending,
                    change the status to approved.*/
                    //the customer should have a mortgage as a component inside the customer, in order for this to work.
                   /* long ssn = UserInput.inputLong("Enter ssn of the customer: ");
                    if(listOfCustomers.getCustomers().contains(ssn)) {
                        if()
                    }*/
                }
                catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
                break;
            case 5:
                try{
                    CustomerMenu.CustomerMenu();
                }
                catch(Exception exception){
                    System.out.println(exception.getMessage());
                }
                break;
            case 6:
                try{
                    String email = UserInput.inputString("Enter the email of the customer: ");
                    validateEmail.execute(email);
                }
                catch(Exception exception){
                    System.out.println(exception.getMessage());
                }
                break;
            case 7:
                try{
                    long ssn = UserInput.inputLong("Enter ssn of the customer: ");
                    long accNum = UserInput.inputLong("Enter account number of the customer: ");
                    checkTransactionHistory.execute(ssn, accNum);
                }
                catch(Exception exception){
                    System.out.println(exception.getMessage());
                }



        }

}



}
