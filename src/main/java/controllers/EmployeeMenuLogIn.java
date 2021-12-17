package controllers;

import usecases.LogInEmployee;

public class EmployeeMenuLogIn {
    private static LogInEmployee logInEmployee;
    private MainMenu mainMenu;

    public void MenuLogInEmployee() {

        int option = UserInput.inputInt("Log In Menu for Employee:" + System.lineSeparator() +
                "0. Return to Main Menu" + System.lineSeparator() +
                "1. Log in " + System.lineSeparator() +
                "2.  ");

        while (option < 0 || option > 8) {

            option = UserInput.inputInt("Invalid option");

        }
        switch (option) {

            case 0: mainMenu.Menu();
                break;
            case 1: try {
                String email =UserInput.inputString(" Enter Email: ");
                String password = UserInput.inputString(" Enter Password: ");
                logInEmployee.execute(email, password);
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
