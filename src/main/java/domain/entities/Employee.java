package domain.entities;

import domain.constants.UserType;

import java.io.Serializable;
import java.util.Date;

public class Employee extends AbstractUser {
    protected String position;

    public Employee(String firstName, String lastName, long SSN, String password, String email, String phoneNumber, Date birthDate){
        super(firstName, lastName, SSN, password, email, phoneNumber, birthDate);
        this.position = UserType.employee;
    }

    public String getPosition(){ //these methods inherit automatically to all classes extended from this first
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
