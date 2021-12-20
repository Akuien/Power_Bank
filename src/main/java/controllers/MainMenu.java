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

        System.out.println("MainMenu options menu:" + EOL +
                "0. Quit" + EOL +
                "1. Customer Login Menu" + EOL +
                "2. Employee Login Menu");
    }

    public void menu(int option){
        do {
            switch (option) {

                case 0:
                    //closes whole system, if system is exited in successful way: parameter = 0
                    //if system is exited in unsuccessful way: parameter = 1 || -1
                    System.exit(0);
                    break;

                case 1:
                    customerLogInMenu.printMenu();
                    option = UserInput.inputInt("Enter option: ");
                    customerLogInMenu.menu(option);

                    printMenu();
                    option = UserInput.inputInt("Enter option: ");//redundant?
                    break;

                case 2:
                    employeeLogInMenu.printMenu();
                    option = UserInput.inputInt("Enter option: ");
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

    public static void main(String[] args) {

        MainMenu mainMenu = new MainMenu();
        mainMenu.printMenu();
        int option = UserInput.inputInt("Enter option: ");
        mainMenu.menu(option);

    }
}
