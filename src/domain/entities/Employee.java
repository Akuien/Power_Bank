package domain.entities;

import domain.constants.UserType;

import java.util.Date;

public class Employee extends AbstractUser{
    protected String position;

    public Employee(String fullName, long SSN, String password, String email, String phoneNumber, Date birthDate){
        super(fullName, SSN, password, email, phoneNumber, birthDate);
        this.position = UserType.employee;
    }

    public String getPosition(){ //these methods inherit automatically to all classes extended from this first
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
