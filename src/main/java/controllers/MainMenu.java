package controllers;

import static controllers.CustomerMenu.EOL;

public class MainMenu {
    private CustomerMenuLogIn customerMenuLogIn;
    private EmployeeMenuLogIn employeeMenuLogIn;
    private ManagerMenuLogIn managerMenuLogIn;

    public MainMenu() {
        this.customerMenuLogIn = customerMenuLogIn;
        this.employeeMenuLogIn = employeeMenuLogIn;
        this.managerMenuLogIn = managerMenuLogIn;
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
            case 1 : customerMenuLogIn.MenuLogInCustomer();
                break;
            case 2 : EmployeeMenuLogIn.MenuLogInEmployee();
                break;
            case 3 : ManagerMenuLogIn.MenuLogInManager();

            default: System.out.println("Please enter valid option");
                break;
        }

    }

    public static void main(String[] args) {

        MainMenu menuClass = new MainMenu();
        menuClass.Menu();

    }
}
