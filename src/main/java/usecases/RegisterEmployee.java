package usecases;

import domain.entities.Employee;
import domain.exceptions.*;
import repositories.EmployeeRepository;

import java.util.Date;


public class RegisterEmployee {

    private ValidateManager validateManager;
    private ValidateEmployee validateEmployee;
    private ValidatePassword validatePassword;
    private ValidateEmail validateEmail;
    private EmployeeRepository employeeRepository;
    private ValidatePhoneNumber validatePhoneNumber;

    public RegisterEmployee(){
        this.validateManager = new ValidateManager();
        this.validateEmployee = new ValidateEmployee();
        this.validateEmail = new ValidateEmail();
        this.validatePassword = new ValidatePassword();
        this.employeeRepository = new EmployeeRepository();
        this.validatePhoneNumber = new ValidatePhoneNumber();
    }

    public String execute(String firstName, String lastName, long employeeSSN, String password, String email, String phoneNumber, Date birthDate, long managerSSN) throws Exception {
        // Validates if the manager who is in charge of registering the employee exists.
        boolean managerExists = validateManager.execute(managerSSN);
        if (!managerExists) {
            throw new ManagerDoesNotExistException(managerSSN);
        }
        //Validates if the employee who is being registered already exists or not.
        boolean employeeExists = validateEmployee.execute(employeeSSN);
        if (employeeExists){
            throw new EmployeeAlreadyExistsException(employeeSSN);
        }
        //Checks if the input is empty or not.
        boolean firstNameIsCorrect = !firstName.isBlank();
        if (!firstNameIsCorrect) {
            throw new NameIsBlankException();
        }
        //Checks if the input is empty or not.
        boolean lastNameIsCorrect = !lastName.isBlank();
        if (!lastNameIsCorrect) {
            throw new NameIsBlankException();
        }
        //Checks if the password introduced by the customer fulfills all the conditions established in the specific validate password use case.
        boolean passwordIsCorrect = validatePassword.execute(password);
        if (!passwordIsCorrect){
            throw new IncorrectPasswordException();
        }
        //Checks if the email introduced by the customer fulfills all the conditions established in the specific validate email use case.
        boolean emailIsCorrect = validateEmail.execute(email);
        if (!emailIsCorrect) {
            throw new IncorrectEmailException();
        }
        //Checks if the phone number introduced by the customer fulfills all the conditions established in the specific validate phone number use case.
        boolean phoneNumberIsCorrect = validatePhoneNumber.execute(phoneNumber);
        if (!phoneNumberIsCorrect) {
            throw new IncorrectPhoneNumberException();
        }

        Employee employee = new Employee(firstName, lastName, employeeSSN, password, email, phoneNumber, birthDate);
        employeeRepository.createProfile(employee);
        return "Employee registered successfully";

        // Employee profile is created once every requirement is met.
        // Because of throw-exceptions, code will stop executing if a requirement is not meant.
        // This prevents the system from creating bad profiles. Same with the previous Class.

    }

}
