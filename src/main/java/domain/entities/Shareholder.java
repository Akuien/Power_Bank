package domain.entities;

import domain.constants.UserType;
import java.util.Date;

public class Shareholder extends Customer{

    public Shareholder(String firstName, String lastName, long SSN, String password, String email, String phoneNumber, Date birthDate){
        super(firstName, lastName, SSN, password, email, phoneNumber, birthDate);
        this.type = UserType.shareholder;
    }


}
