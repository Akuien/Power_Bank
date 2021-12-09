package controllers;

public class MainMenu {

    public static void Menu(){

        int option = UserInput.inputInt("MainMenu options menu:" + System.lineSeparator()+
                "0. Quit" + System.lineSeparator()+
                "1. Customer Login Menu" + System.lineSeparator()+
                "2. Employee Login Menu" + System.lineSeparator()+
                "3. Manager Login Menu");

        while(option < 0 || option > 8){

            option = UserInput.inputInt("Invalid option");

        }switch(option){

            case 0: // Terminate Program
                break;
            case 1 : CustomerMenuLogIn.MenuLogInCustomer();
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
