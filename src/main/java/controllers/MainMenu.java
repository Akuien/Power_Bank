package controllers;

import static controllers.CustomerMenu.EOL;

public class MainMenu {
    private CustomerLogInMenu customerMenuLogIn;
    private EmployeeLogInMenu employeeMenuLogIn;

    public MainMenu() {
    }

    public void Menu(){

        int option = UserInput.inputInt("MainMenu options menu:" + System.lineSeparator()+
                "0. Quit" + EOL +
                "1. Customer Login Menu" + EOL +
                "2. Employee Login Menu" + EOL +
                "3. Manager Login Menu");

        while(option < 0 || option > 8){

            option = UserInput.inputInt("Invalid option");

        }switch(option){

            case 0: // Terminate Program
                break;
            case 1 : customerMenuLogIn.printMenuLogInCustomer();
                break;
            case 2 : employeeMenuLogIn.MenuLogInEmployee();
                break;
            default: System.out.println("Please enter valid option");
                break;
        }

    }

    public static void main(String[] args) {

        MainMenu menuClass = new MainMenu();
        menuClass.Menu();

    }
}
