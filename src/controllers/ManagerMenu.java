package controllers;

import usecases.ObtainEmployees;
import usecases.RegisterEmployee;

import java.text.SimpleDateFormat;
import java.util.Date;

import static controllers.CustomerMenu.EOL;

public class ManagerMenu {

    private RegisterEmployee registerEmployee;
    private ObtainEmployees obtainEmployees;
    /*private*/


    public ManagerMenu() {
        this.registerEmployee = new RegisterEmployee();
        this.obtainEmployees = new ObtainEmployees();


    }

    public static void MenuLogInManager() {

        int option = UserInput.inputInt("Log In Menu for Manager:" + EOL +
                "0. Return to Main Menu" + EOL +
                "1. Employee Menu " + EOL  +
                "2. Register Employee" + EOL  +
                "3. Obtain Employee." + EOL  +
                "4. Promote Employee." + EOL  +
                "5. Validate Employee." + EOL  +
                "6. Validate Manager. " + EOL  +
                "7. Log Out");

        while (option < 0 || option > 7) {

            option = UserInput.inputInt("Invalid option");

        }
        switch (option) {

            case 0: MainMenu.Menu();
                break;
            case 1:
                EmployeeMenu.EmployeeMenu();
                break;
            case 2:
                String firstName = UserInput.inputString("");
                String lastName = UserInput.inputString("");
                long SSN = UserInput.inputLong("");
                String password = UserInput.inputString("");
                String email = UserInput.inputString("");
                String phoneNumber = UserInput.inputString("");
                String inputBirthDate = UserInput.inputString("");
                Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputBirthDate);
                RegisterEmployee.execute(firstName, lastName, SSN, password, email, phoneNumber, birthDate);
                break;

            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;

            default:
                System.out.println("Please enter valid option");
                break;
        }
    }
}
