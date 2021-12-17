package controllers;


import usecases.LogInEmployee;

import static controllers.CustomerMenu.EOL;

public class ManagerMenuLogIn {

    private static LogInEmployee logInEmployee;
    private MainMenu mainMenu;

    public ManagerMenuLogIn() {
        logInEmployee = new LogInEmployee();
    }

    public void MenuLogInManager() {

        int option = UserInput.inputInt("Log In Menu for Manager:" + EOL +
                "0. Return to Main Menu" + EOL +
                "1. Log In " );


        while (option < 0 || option > 1) {

            option = UserInput.inputInt("Invalid option");

        }
        switch (option) {

            case 0:mainMenu.Menu();
                break;


            case 1: // Used employee login; Manager is an employee.
                try {
                    String email =UserInput.inputString(" Enter Email: ");
                    String password = UserInput.inputString(" Enter Password: ");
                    logInEmployee.execute(email, password);
                } catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
                break;


            default:
                System.out.println("Please enter valid option");
                break;
        }
    }
}
