package usecases;

import domain.entities.Customer;
import domain.exceptions.EmailPasswordDoesNotExistException;
import repositories.CustomerRepository;

import java.util.UUID;

public class LogInCustomer {

    private CustomerRepository customerRepository;

    public LogInCustomer(){
        this.customerRepository = new CustomerRepository();
    }

    public String execute(String email, String password) throws Exception {
        Customer customer = customerRepository.getByEmail(email);
        // We check if the customer assigned to the email exists and if the password matches the one kept in the database.
        if (customer == null || !customer.getPassword().equals(password)){
            throw new EmailPasswordDoesNotExistException();
        }
        // When a customer logs in we create him/her an access token to access the rest of the functionalities.
        customer.setAccessToken(UUID.randomUUID().toString());
        customerRepository.updateProfile(customer);
        return customer.getAccessToken();
    }

}
