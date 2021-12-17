package controllers;

import usecases.LogInCustomer;
import usecases.RegisterCustomer;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomerMenuLogIn {

    private static LogInCustomer logInCustomer;
    private MainMenu mainmenu;
    private RegisterCustomer registerCustomer;

    public CustomerMenuLogIn(MainMenu mainmenu) {

        this.mainmenu = mainmenu;
        this.registerCustomer = new RegisterCustomer();
    }


    public void printMenuLogInCustomer() {

        System.out.println("Log In Menu for Customer:" + System.lineSeparator() +
                "0. Return to Main Menu" + System.lineSeparator() +
                "1. Log in " + System.lineSeparator() +
                "2. Create new account ");
    }

    public void customerMenuLogIn(int option){

        do {
            switch (option) {

                case 0: mainmenu.Menu();
                    break;

                case 1:
                    try {
                        String email =UserInput.inputString(" Enter Email: ");
                        String password = UserInput.inputString(" Enter Password: ");
                        logInCustomer.execute(email, password);
                        printMenuLogInCustomer();
                        option = UserInput.inputInt("Enter an option: ");
                    }

                    catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
                    break;

                case 2:
                    try {
                        String firstName = UserInput.inputString("Enter First Name: ");
                        String lastName = UserInput.inputString("Enter Last Name: ");
                        long SSN = UserInput.inputLong("Enter SSN: ");
                        String password = UserInput.inputString("Enter Password: ");
                        String email = UserInput.inputString("Enter Email: ");
                        String phoneNumber = UserInput.inputString("Enter Phone Number");
                        String birthDateString = UserInput.inputString("Enter Birth Date");
                        Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthDateString);
                        registerCustomer.execute(firstName, lastName, SSN, password, email, phoneNumber, birthDate);

                        printMenuLogInCustomer();
                        option = UserInput.inputInt("Enter an option: ");
                    }
                    catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }

                default:
                    System.out.println("Please enter valid option");
                    option = UserInput.inputInt("Enter an option: ");
                    break;
            }

        }while (option < 0 || option > 8);

    }

}

