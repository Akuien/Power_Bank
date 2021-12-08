package repositories;

import domain.entities.Customer;

import java.util.ArrayList;

public class CustomerRepository extends AbstractRepository {

    public ArrayList<Customer> getAll() {
        return CustomerRepository.persistenceData.getCustomers();
    }
}
