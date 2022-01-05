package usecases;
import domain.entities.Customer;
import domain.exceptions.*;
import repositories.CustomerRepository;

import java.util.Date;


public class RegisterCustomer {

    private ValidateCustomer validateCustomer;
    private ValidatePassword validatePassword;
    private ValidateEmail validateEmail;
    private CustomerRepository customerRepository;
    private ValidatePhoneNumber validatePhoneNumber;

    public RegisterCustomer(){
        this.validateCustomer = new ValidateCustomer();
        this.validateEmail = new ValidateEmail();
        this.validatePassword = new ValidatePassword();
        this.customerRepository = new CustomerRepository();
        this.validatePhoneNumber = new ValidatePhoneNumber();
    }
    public String execute (String firstName, String lastName, long SSN, String password, String email, String phoneNumber, Date birthDate) throws Exception {
        // Checks if the customer we are trying to register already exists.
        boolean customerExists = validateCustomer.execute(SSN);
        if (customerExists) {
            throw new CustomerAlreadyExistsException(SSN);
        }
        // Checks if the input is empty or not.
        boolean firstNameIsCorrect = !firstName.isBlank(); // These methods check so that customer doesn't enter an empty name.
        if (!firstNameIsCorrect) {
            throw new NameIsBlankException();
        }
        // Checks if the input is empty or not.
        boolean lastNameIsCorrect = !lastName.isBlank();
        if (!lastNameIsCorrect) {
            throw new NameIsBlankException();
        }
        //Checks if the password introduced by the customer fulfills all the conditions established in the specific validate password use case.
        boolean passwordIsCorrect = validatePassword.execute(password);
        if (!passwordIsCorrect) {
            throw new IncorrectPasswordException();
        }
        //Checks if the email introduced by the customer fulfills all the conditions established in the specific validate email use case.
        boolean emailIsCorrect = validateEmail.execute(email);
        if (!emailIsCorrect) {
            throw new IncorrectEmailException();
        }
        //Checks if the phone number introduced by the customer fulfills all the conditions established in the specific validate phone number use case.
        boolean phoneNumberIsCorrect = validatePhoneNumber.execute(phoneNumber);
        if (!phoneNumberIsCorrect) {
            throw new IncorrectPhoneNumberException();

        } else {
            Customer customer = new Customer(firstName, lastName, SSN, password, email, phoneNumber, birthDate);
            customerRepository.createProfile(customer);
            return "Customer registered successfully"; // When all the requirements are met, a new customer profile is created in our system.
        }
    }
}
