package controllers;
import domain.constants.UserType;
import domain.entities.Employee;
import domain.entities.Manager;
import repositories.EmployeeRepository;
import usecases.LogInEmployee;

public class EmployeeLogInMenu implements IControllers{
    private static LogInEmployee logInEmployee;
    private EmployeeRepository employeeRepository;
    private EmployeeMenu employeeMenu;
    private ManagerMenu managerMenu;
    public EmployeeLogInMenu(){
        this.logInEmployee = new LogInEmployee();
        this.employeeRepository = new EmployeeRepository();
        this.employeeMenu = new EmployeeMenu();
        this.managerMenu = new ManagerMenu();
    }

    public void printMenu() {

        System.out.println("Log In Menu for Employee:" + System.lineSeparator() +
                "0. Return to Main Menu" + System.lineSeparator() +
                "1. Log in " + System.lineSeparator());
    }
    public void menu (int option) {
        do {
            switch (option) {

                case 0: // When "break", it will be redirected to main menu.
                    ;
                    break;
                case 1: // Log in employees, you log-in by giving your email and password, then we call the access token
                    // which determines if you will be sent to employee menu or manager menu.
                    try {
                        String email = UserInput.inputString("Enter Email: ");
                        String password = UserInput.inputString("Enter Password: ");
                        String accessToken = logInEmployee.execute(email, password);
                        Employee employee = employeeRepository.getByAccessToken(accessToken);
                        System.out.println("Employee logged in! These are your available functionalities: ");

                        if (employee.getPosition().equals(UserType.employee)) { // Checks if it is an employee logging in.
                            employeeMenu.printMenu();
                            option = UserInput.inputInt("Enter option: ");
                            employeeMenu.menu(option, employee);
                        } else {
                            managerMenu.printMenu(); // Checks if it is a manager who logs in.
                            option = UserInput.inputInt("Enter option: ");
                            Manager manager = new Manager(
                                    employee.getFirstName(),
                                    employee.getLastName(),
                                    employee.getSSN(),
                                    employee.getPassword(),
                                    employee.getEmail(),
                                    employee.getPhoneNumber(),
                                    employee.getBirthDate()
                            );
                            managerMenu.menu(option, manager);
                        }
                        printMenu();
                        option = UserInput.inputInt("Enter an option: ");

                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                        System.out.println("Please introduce again the correct credentials.");
                        // Since option remains to be 1 the credentials are requested again.
                    }

                    break;

                default:
                    System.out.println("Please enter valid option");
                    break;
            }
        } while (option != 0) ;
        // We use option != 0 because it means the exit of this menu and therefore no option will be available.

    }
}




