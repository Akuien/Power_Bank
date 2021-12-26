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
        boolean customerExists = validateCustomer.execute(SSN);
        if (customerExists) {
            throw new CustomerAlreadyExistsException(SSN);
        }

        boolean firstNameIsCorrect = !firstName.isBlank();
        if (!firstNameIsCorrect) {
            throw new NameIsBlankException();
        }

        boolean lastNameIsCorrect = !lastName.isBlank();
        if (!lastNameIsCorrect) {
            throw new NameIsBlankException();
        }

        //It does not show why the password is incorrect.
        boolean passwordIsCorrect = validatePassword.execute(password);
        if (!passwordIsCorrect) {
            throw new IncorrectPasswordException();
        }

        boolean emailIsCorrect = validateEmail.execute(email);
        if (!emailIsCorrect) {
            throw new IncorrectEmailException();
        }

        boolean phoneNumberIsCorrect = validatePhoneNumber.execute(phoneNumber);
        if (!phoneNumberIsCorrect) {
            throw new IncorrectPhoneNumberException();

        } else {
            Customer customer = new Customer(firstName, lastName, SSN, password, email, phoneNumber, birthDate);
            customerRepository.createProfile(customer);
            return "Customer registered successfully"; // add toString later
        }
    }
}
