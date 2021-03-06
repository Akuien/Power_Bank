package usecases;

import domain.entities.Customer;

import java.util.ArrayList;

public class ValidateCustomer {

    // This and below includes are Validation classes, which are used in many other classes,
    // to check that requirements for various tasks are met before they execute and change our system.

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
