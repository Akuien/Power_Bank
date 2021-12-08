package domain.entities;

import domain.constants.UserType;

import java.util.Date;

public class Manager extends Employee{

    public Manager(String fullName, long SSN, String password, String email, String phoneNumber, Date birthDate){
        super(fullName, SSN, password, email, phoneNumber, birthDate);
        this.position = UserType.manager;
    }
}
