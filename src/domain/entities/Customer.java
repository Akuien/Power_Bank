package domain.entities;

import domain.constants.UserType;
import java.util.Date;

public class Customer extends AbstractUser {

    protected String type;

    public Customer(String fullName, long SSN, String password, String email, String phoneNumber, Date birthDate){
        super(fullName, SSN, password, email, phoneNumber, birthDate);
        this.type = UserType.customer;
    }

    public String getType(){ //these methods inherit automatically to all classes extended from this first
        return this.type;
    }
}
