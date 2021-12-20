package domain.entities;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractUser {
    private String firstName;
    private String lastName;
    private long SSN;
    private String password;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private String accessToken; //This token is used for the user to be able to have all the functionalities after log in.

    public AbstractUser(String firstName, String lastName, long SSN, String password, String email, String phoneNumber, Date birthDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.SSN = SSN;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.accessToken = null;
    }

    public String toString(){
        return firstName + lastName + " " + SSN;
    }

    public long getAge(){
        Date now = new Date(); //gives the current date
        return (now.getTime() - birthDate.getTime()) / 86400000 / 365;
    }

    public boolean equals(AbstractUser abstractUser){
        return this.getSSN() == abstractUser.getSSN();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public long getSSN() {
        return SSN;
    }

    public void setSSN(long SSN) {
        this.SSN = SSN;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
