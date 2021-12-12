package controllers;

public class EmployeeMenuLogIn {
    public static void MenuLogInEmployee() {

        int option = UserInput.inputInt("Log In Menu for Employee:" + System.lineSeparator() +
                "0. Return to Main Menu" + System.lineSeparator() +
                "1. Log in " + System.lineSeparator() +
                "2.  ");

        while (option < 0 || option > 8) {

            option = UserInput.inputInt("Invalid option");

        }
        switch (option) {

            case 0:MainMenu.Menu();
                break;
            case 1:
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
