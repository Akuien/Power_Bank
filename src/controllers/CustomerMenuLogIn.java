package controllers;

import usecases.LogInCustomer;

public class CustomerMenuLogIn {
    public static void MenuLogInCustomer() {

        int option = UserInput.inputInt("Log In Menu for Customer:" + System.lineSeparator() +
                "0. Return to Main Menu" + System.lineSeparator() +
                "1. " + System.lineSeparator() +
                "2. ");

        while (option < 0 || option > 8) {

            option = UserInput.inputInt("Invalid option");

        }
        switch (option) {

            case 0:
                ;
                break;
            case 1: ;
                ;
                break;
            case 2:
                ;
            default:
                System.out.println("Please enter valid option");
                break;
        }
    }

}
