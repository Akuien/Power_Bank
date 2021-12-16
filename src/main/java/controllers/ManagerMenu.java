package controllers;

import repositories.EmployeeRepository;
import usecases.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import static controllers.CustomerMenu.EOL;

public class ManagerMenu {

    private RegisterEmployee registerEmployee;
    private ObtainEmployees obtainEmployees;
    private EmployeeMenu employeeMenu;
    private MainMenu mainMenu;
    private EmployeeRepository employeeRepository;
    private PromoteEmployee promoteEmployee;
    private LogOutEmployee logOutEmployee;


    /*private*/


    public ManagerMenu() {
        this.registerEmployee = new RegisterEmployee();
        this.employeeMenu = new EmployeeMenu();
        this.mainMenu = new MainMenu();
        this.obtainEmployees = new ObtainEmployees();
        this.employeeRepository = new EmployeeRepository();
    }

    public void printManagerMenu() {

        int option = UserInput.inputInt("Log In Menu for Manager:" + EOL +
                "0. Return to Main Menu" + EOL +
                "1. Employee Menu " + EOL +
                "2. Register Employee." + EOL +
                "3. Obtain Employee by SSN. " + EOL +
                "4. Promote Employee." + EOL +
                "5. Show all employees. " + EOL +
                "6. Log Out. ");
        //int input = UserInput.inputInt("Choose an option: ");
    }

    public void managerMainMenu(int option){
            do {
                switch (option) {
                    case 0:
                        mainMenu.Menu();
                        break;
                    case 1:
                        employeeMenu.employeeMenu();
                        break;
                    case 2:
                        try {
                            String firstName = UserInput.inputString("Enter the first name: ");
                            String lastName = UserInput.inputString("Enter the last name: ");
                            long SSN = UserInput.inputLong("Enter the ssn: ");
                            String password = UserInput.inputString("Enter the password: ");
                            String email = UserInput.inputString("Enter the email: ");
                            String phoneNumber = UserInput.inputString("Enter the phone number: ");
                            String inputBirthDate = UserInput.inputString("Enter the birthdate: ");
                            Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(inputBirthDate);
                            RegisterEmployee.execute(firstName, lastName, SSN, password, email, phoneNumber, birthDate);
                            option = UserInput.inputInt("Enter the desired option: ");
                        }
                        catch (Exception exception){
                            System.out.println(exception.getMessage());
                        }
                        break;

                    case 3:
                        try {
                            long ssn = UserInput.inputLong("Enter ssn of the employee: ");
                            employeeRepository.getBySSN(ssn);
                        }
                        catch(Exception exception){
                            System.out.println(exception.getMessage());
                        }

                        break;
                    case 4:
                        try{
                        long employeeSSN = UserInput.inputLong("Enter ssn of the employee: ");
                        long managerSSN = UserInput.inputLong("Enter ssn of the manager: ");
                        promoteEmployee.execute(managerSSN, employeeSSN);
                        }
                        catch(Exception exception){
                            System.out.println(exception.getMessage());
                        }
                        break;
                    case 5:
                        employeeRepository.getAll();
                        break;
                    case 6:
                        try{
                        String accessToken = UserInput.inputString(" ");
                        logOutEmployee.execute(accessToken);}
                        catch(Exception exception){
                            System.out.println(exception.getMessage());
                        }
                        break;

                    default:
                        System.out.println("Please enter valid option");
                        break;
                }
            } while (option < 0 || option > 6);
    }
}
