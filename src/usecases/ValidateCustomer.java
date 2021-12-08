package usecases;

import domain.entities.Customer;

import java.util.ArrayList;

public class ValidateCustomer {
    private ObtainCustomers obtainCustomers;

    public ValidateCustomer(){
        this.obtainCustomers = new ObtainCustomers();
    }

    public boolean execute(long SSN){
        ArrayList<Customer> customers = this.obtainCustomers.execute();
        for (Customer currentCustomer : customers){
            if (currentCustomer.getSSN() == SSN){
                return true;
            }
        }
        return false;
    }
}
