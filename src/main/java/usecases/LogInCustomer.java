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
        if (customer == null || !customer.getPassword().equals(password)){
            throw new EmailPasswordDoesNotExistException();
        }
        customer.setAccessToken(UUID.randomUUID().toString());
        customerRepository.updateProfile(customer);
        return customer.getAccessToken();
        //We return the token so that javaFX can keep it for future operations
    }

}
