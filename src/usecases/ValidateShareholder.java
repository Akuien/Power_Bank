package usecases;

import domain.constants.UserType;
import domain.entities.Customer;
import repositories.CustomerRepository;

public class ValidateShareholder {

    private ValidateCustomer validateCustomer;
    private CustomerRepository customerRepository;

    public ValidateShareholder() {
        this.validateCustomer = new ValidateCustomer();
        this.customerRepository = new CustomerRepository();
    }

    public boolean execute(long SSN){
        boolean customerExists = validateCustomer.execute(SSN);
        if(!customerExists){
            return false;
        }

        Customer shareholder = this.customerRepository.getBySSN(SSN);
        return shareholder.getType().equals(UserType.shareholder);
    }

}
