package usecases;

import domain.entities.Customer;
import repositories.CustomerRepository;

import java.util.ArrayList;

public class ObtainCustomers {
    private CustomerRepository customerRepository;

    public ObtainCustomers(){
        this.customerRepository = new CustomerRepository();
    }

    public ArrayList<Customer> execute() {
        return this.customerRepository.getAll();
    }
}
