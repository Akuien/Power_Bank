package controllers;

import usecases.LogInCustomer;

public class CustomerMenuLogIn {

    private static LogInCustomer logInCustomer;
    private MainMenu mainmenu;

    public CustomerMenuLogIn(MainMenu mainmenu) {
        this.mainmenu = mainmenu;
    }


    public void MenuLogInCustomer() {

        int option = UserInput.inputInt("Log In Menu for Customer:" + System.lineSeparator() +
                "0. Return to Main Menu" + System.lineSeparator() +
                "1. Log in " + System.lineSeparator() +
                "2. Create new account ");

        while (option < 0 || option > 8) {

            option = UserInput.inputInt("Invalid option");

        }
        switch (option) {

            case 0: mainmenu.Menu();
                break;

            case 1: try {
                String email =UserInput.inputString(" Enter Email: ");
                String password = UserInput.inputString(" Enter Password: ");
                logInCustomer.execute(email, password);
            } catch (Exception exception){
                System.out.println(exception.getMessage());
            }

                break;
            case 2:
                ;
            default:
                System.out.println("Please enter valid option");
                break;
        }
    }

}
