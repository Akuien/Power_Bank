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
        //We check if the customer who is trying to log out has an access token or not.
        if (customer == null){
            throw new AccessTokenDoesNotExistException();
        }
        //We set the access token of the customer to null and therefore this customer is logged out
        customer.setAccessToken(null);
        customerRepository.updateProfile(customer);
    }

}
