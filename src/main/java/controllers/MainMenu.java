package controllers;

import static controllers.CustomerMenu.EOL;

public class MainMenu implements IControllers{
    private CustomerLogInMenu customerLogInMenu;
    private EmployeeLogInMenu employeeLogInMenu;
    public MainMenu() {
        this.customerLogInMenu = new CustomerLogInMenu();
        this.employeeLogInMenu = new EmployeeLogInMenu();
    }

    public void printMenu() {

        System.out.println("MainMenu options menu:" + System.lineSeparator() +
                "0. Quit" + EOL +
                "1. Customer Login Menu" + EOL +
                "2. Employee Login Menu");
    }
    public void menu(int option){
        do {
            switch (option) {

                case 0:
                    // This line closes the whole system, and we take a 0 as a parameter because it has executed in a successful way.
                    // If the parameter had been 1 or -1 it meant it was exited in an unsuccessful way.
                    System.exit(0);
                    break;

                case 1:
                    customerLogInMenu.printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    customerLogInMenu.menu(option);

                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;

                case 2:

                    employeeLogInMenu.printMenu();
                    option = UserInput.inputInt("Enter option");
                    employeeLogInMenu.menu(option);

                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;

                default:
                    System.out.println("Please enter valid option");

                    printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    break;
            }
        } while (option != 0);
    }

    public static void main(String[] args) { // Code that starts the whole program.

        MainMenu mainMenu = new MainMenu();
        mainMenu.printMenu();
        int option = UserInput.inputInt("Enter option: ");
        mainMenu.menu(option);

    }
}