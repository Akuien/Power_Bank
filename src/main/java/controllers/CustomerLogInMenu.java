package controllers;

import domain.constants.UserType;
import domain.entities.Customer;
import domain.entities.Shareholder;
import repositories.CustomerRepository;
import usecases.LogInCustomer;
import usecases.RegisterCustomer;

import java.text.SimpleDateFormat;
import java.util.Date;

// Class used as user interface for logging in and registering
// for Customer and Shareholder type of users.

public class CustomerLogInMenu {

    private LogInCustomer logInCustomer;
    private RegisterCustomer registerCustomer;
    private CustomerRepository customerRepository;
    private CustomerMenu customerMenu;
    private ShareholderMenu shareholderMenu;

    // Constructor to initialize the log in menu.
    public CustomerLogInMenu() {
        this.registerCustomer = new RegisterCustomer();
        this.logInCustomer = new LogInCustomer();
        this.customerMenu = new CustomerMenu();
        this.customerRepository = new CustomerRepository();
        this.shareholderMenu = new ShareholderMenu();
    }


    public void printMenu() {

        System.out.println("Log In Menu for Customer:" + System.lineSeparator() +
                "0. Return to Main Menu" + System.lineSeparator() +
                "1. Log in " + System.lineSeparator() +
                "2. Create new account ");
    }

    public void menu(int option){ // Menu uses Switch-statement, "default" option is assigned to non-existing user input options.

        do {
            switch (option) {

                case 0:
                    break;

                case 1:
                    try {
                        String email =UserInput.inputString("Enter Email: ");
                        String password = UserInput.inputString("Enter Password: ");
                        String accessToken = logInCustomer.execute(email, password);
                        Customer customer = customerRepository.getByAccessToken(accessToken);

                        System.out.println("Success! User: " + customer.getFirstName() + " logged in successfully!\n To start using your bank account, pick one of the following options:");
                        if (customer.getType().equals(UserType.customer)){
                            customerMenu.printMenu();
                            option = UserInput.inputInt("Enter an option: ");
                            customerMenu.menu(option, customer);
                        }
                        else{
                            shareholderMenu.printMenu();
                            option = UserInput.inputInt("Enter an option: ");
                            Shareholder shareholder = new Shareholder(
                                    customer.getFirstName(),
                                    customer.getLastName(),
                                    customer.getSSN(),
                                    customer.getPassword(),
                                    customer.getEmail(),
                                    customer.getPhoneNumber(),
                                    customer.getBirthDate()
                            );
                            shareholderMenu.menu(option, shareholder);
                        }
                        printMenu();
                        option = UserInput.inputInt("Enter an option: ");

                    } catch (Exception exception){
                        System.out.println(exception.getMessage());
                        System.out.println("Wrong email/password. Please review your input and try again");
                    }
                    break;

                case 2:
                    try {
                        String firstName = UserInput.inputString("Enter First Name: ");
                        String lastName = UserInput.inputString("Enter Last Name: ");
                        long SSN = UserInput.inputLong("Enter SSN: ");
                        String password = UserInput.inputString("Enter Password: ");
                        String email = UserInput.inputString("Enter Email: ");
                        String phoneNumber = UserInput.inputString("Enter Phone Number: ");
                        String birthDateString = UserInput.inputString("Enter Birth Date: ");
                        Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthDateString);

                        String message = registerCustomer.execute(firstName, lastName, SSN, password, email, phoneNumber, birthDate);
                        String accessToken = logInCustomer.execute(email, password);
                        Customer customer = customerRepository.getByAccessToken(accessToken);
                        System.out.println(message);

                        if (customer.getType().equals(UserType.customer)){
                            customerMenu.printMenu();
                            option = UserInput.inputInt("Enter an option: ");
                            customerMenu.menu(option, customer);
                        }
                        else{
                            shareholderMenu.printMenu();
                            option = UserInput.inputInt("Enter an option: ");
                            Shareholder shareholder = new Shareholder(
                                    customer.getFirstName(),
                                    customer.getLastName(),
                                    customer.getSSN(),
                                    customer.getPassword(),
                                    customer.getEmail(),
                                    customer.getPhoneNumber(),
                                    customer.getBirthDate()
                            );
                            shareholderMenu.menu(option, shareholder);
                        }
                        printMenu();
                        option = UserInput.inputInt("Enter an option: ");
                    }
                    catch (Exception exception) {
                        System.out.println("Invalid input!");
                        System.out.println("Some of the information provided is invalid!");
                        System.out.println("Please check if input for each field complies with the provided format, and then try again.");
                    }
                    break;

                default:
                    System.out.println("Please enter valid option");
                    option = UserInput.inputInt("Enter an option: ");
                    break;
            }

        }while (option !=0);
        // 0 = exit or return to MainMenu.
        // When selected, prompts the user to the Main Menu.
    }

}

