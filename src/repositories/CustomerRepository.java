package repositories;

import domain.entities.Customer;

import java.util.ArrayList;

public class CustomerRepository extends AbstractRepository {

    public ArrayList<Customer> getAll() {
        return CustomerRepository.persistenceData.getCustomers();
    }


    public Customer getBySSN(long SSN) {
        ArrayList<Customer> customers = CustomerRepository.persistenceData.getCustomers();
        Customer foundCustomer = null;
        for (Customer customer : customers){
            if (customer.getSSN() == SSN){
                foundCustomer = customer;
            }
        }
        return foundCustomer;
    }


    public Customer getByAccessToken(String accessToken) {
        ArrayList<Customer> customers = CustomerRepository.persistenceData.getCustomers();
        Customer foundCustomer = null;
        for (Customer customer : customers){
            if (customer.getAccessToken() == accessToken){
                foundCustomer = customer;
            }
        }
        return foundCustomer;
    }


    public Customer getByEmail(String email) {
        ArrayList<Customer> customers = CustomerRepository.persistenceData.getCustomers();
        Customer foundCustomer = null;
        for (Customer customer : customers){
            if (customer.getEmail() == email){
                foundCustomer = customer;
            }
        }
        return foundCustomer;
    }


    public void createProfile(Customer customer) {
        ArrayList<Customer> customers = CustomerRepository.persistenceData.getCustomers();
        customers.add(customer);
        CustomerRepository.persistenceData.setCustomers(customers);
    }


    public void updateProfile(Customer customer) {
        ArrayList<Customer> customers = CustomerRepository.persistenceData.getCustomers();
        for (Customer currentCustomer : customers){
            if (currentCustomer.equals(customer)){
                currentCustomer.setFullName(customer.getFullName());
                currentCustomer.setPassword(customer.getPassword());
                currentCustomer.setEmail(customer.getEmail());
                currentCustomer.setPhoneNumber(customer.getPhoneNumber());
                currentCustomer.setBirthDate(customer.getBirthDate());
                currentCustomer.setAccessToken(customer.getAccessToken());
                currentCustomer.setType(customer.getType());
            }
        }
        CustomerRepository.persistenceData.setCustomers(customers);
    }
}
