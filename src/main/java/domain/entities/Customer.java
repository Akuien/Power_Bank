package domain.entities;

import domain.constants.UserType;
import java.util.Date;

public class Customer extends AbstractUser {

    protected String type;
    // type is used to define the specific type of user eg customer, employee

    public Customer(String firstName, String lastName, long SSN, String password, String email, String phoneNumber, Date birthDate){
        super(firstName, lastName, SSN, password, email, phoneNumber, birthDate);
        this.type = UserType.customer;
    }

    public String getType(){ //these methods inherit automatically to all classes extended from this first
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
