package usecases;

import domain.entities.Customer;
import domain.exceptions.AccessTokenDoesNotExistException;
import repositories.CustomerRepository;

public class LogOutCustomer {

    private CustomerRepository customerRepository;

    public LogOutCustomer(){
        this.customerRepository = new CustomerRepository();
    }

    public void execute(String accessToken) throws Exception {
        Customer customer = customerRepository.getByAccessToken(accessToken);
        if (customer == null){
            throw new AccessTokenDoesNotExistException();
        }
        customer.setAccessToken(null);
        customerRepository.updateProfile(customer);
    }

}
