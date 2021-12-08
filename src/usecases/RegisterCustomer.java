package usecases;
import domain.entities.Customer;
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
    public String registerCustomer(String firstName, String lastName, long SSN, String password, String email, String phoneNumber, Date birthDate) {
        if (validateCustomer.execute(SSN)) { //checks for letters in ssn and if existing is there
            return "This customer is already registered";

        }  else if (firstName.isBlank()) {
            return "Please enter your first name";

        } else if (lastName.isBlank()) {
            return "Please enter your last name";

        } else if (!validatePassword.execute(password)) {  // We can expend this later, number, sign !%#
            return "Password is weak, must eat more protein";

        } else if (!validateEmail.execute(email)) {
            return "Invalid email";

        } else if (!validatePhoneNumber.execute(phoneNumber)) {
            return "Invalid number";

        } else {
            Customer customer = new Customer(firstName, lastName, SSN, password, email, phoneNumber, birthDate);
            customerRepository.createProfile(customer);
            return "Customer registered successfully"; // add toString later
        }
    }

}
