package repositories;

import domain.entities.Customer;

import java.util.ArrayList;

public class CustomerRepository extends AbstractRepository { // this Repository works with customer-related data.

    public ArrayList<Customer> getAll() {
        return CustomerRepository.persistenceData.getCustomers();
    }


    public Customer getBySSN(long SSN) {
        ArrayList<Customer> customers = CustomerRepository.persistenceData.getCustomers(); // Checks ArrayList of customers.
        Customer foundCustomer = null;
        for (Customer customer : customers){
            if (customer.getSSN() == SSN){
                foundCustomer = customer;
            }
        }
        return foundCustomer;
    }


    public Customer getByAccessToken(String accessToken) {

        // Every user  gets an accessToken once they sign a contract with the bank.
        // The Use Cases will always ask for an accessToken before performing a task.
        // If a user does not have an accessToken, they cannot access the Use Cases and will be logged out.


        ArrayList<Customer> customers = CustomerRepository.persistenceData.getCustomers();
        Customer foundCustomer = null;
        for (Customer customer : customers){
            if (customer.getAccessToken() != null && customer.getAccessToken().equals(accessToken)){
                foundCustomer = customer;
            }
        }
        return foundCustomer;
    }


    public Customer getByEmail(String email) {
        ArrayList<Customer> customers = CustomerRepository.persistenceData.getCustomers();
        Customer foundCustomer = null;
        for (Customer customer : customers){
            if (customer.getEmail().equals(email)){
                foundCustomer = customer;
            }
        }
        return foundCustomer;
    }


    public void createProfile(Customer customer) { // Creates a customer inside Customer ArrayList.
        ArrayList<Customer> customers = CustomerRepository.persistenceData.getCustomers();
        customers.add(customer);
        CustomerRepository.persistenceData.setCustomers(customers);
    }


    public void updateProfile(Customer customer) { // Let's you update profile details of a Customer.
        ArrayList<Customer> customers = CustomerRepository.persistenceData.getCustomers();
        for (Customer currentCustomer : customers){
            if (currentCustomer.equals(customer)){
                currentCustomer.setFirstName(customer.getFirstName());
                currentCustomer.setLastName(customer.getLastName());
                currentCustomer.setPassword(customer.getPassword());
                currentCustomer.setEmail(customer.getEmail());
                currentCustomer.setPhoneNumber(customer.getPhoneNumber());
                currentCustomer.setBirthDate(customer.getBirthDate());
                currentCustomer.setAccessToken(customer.getAccessToken());
                currentCustomer.setType(customer.getType());
            }
        }
        CustomerRepository.persistenceData.setCustomers(customers); // Setter-method for changing Customer parameters.
    }
}
