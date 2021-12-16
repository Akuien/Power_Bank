package domain.entities;

import domain.constants.UserType;

import java.util.Date;

public class Manager extends Employee{

    public Manager(String firstName, String lastName, long SSN, String password, String email, String phoneNumber, Date birthDate){
        super(firstName,lastName, SSN, password, email, phoneNumber, birthDate);
        this.position = UserType.manager;
    }
}
