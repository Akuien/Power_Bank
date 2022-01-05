package usecases;

import domain.entities.Manager;
import domain.exceptions.*;
import repositories.EmployeeRepository;

import java.util.Date;


public class RegisterManager {


        private ValidateManager validateManager;
        private ValidatePassword validatePassword;
        private ValidateEmail validateEmail;
        private EmployeeRepository employeeRepository;
        private ValidatePhoneNumber validatePhoneNumber;

        public RegisterManager() {
            this.validateManager = new ValidateManager();
            this.validateEmail = new ValidateEmail();
            this.validatePassword = new ValidatePassword();
            this.employeeRepository = new EmployeeRepository();
            this.validatePhoneNumber = new ValidatePhoneNumber();
        }

    public String execute(String firstName, String lastName, long newManagerSSN, long managerSSN, String password, String email, String phoneNumber, Date birthDate) throws Exception {
        // Validates if the manager who is in charge of registering the employee exists.
        boolean managerExists = validateManager.execute(managerSSN);
        if (!managerExists) {
            throw new ManagerDoesNotExistException(managerSSN);
        }
        //Validates if the manager who is being registered already exists or not.
        boolean newManagerExists = validateManager.execute(newManagerSSN);
        if (newManagerExists) {
            throw new ManagerAlreadyExistsException(newManagerSSN);
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
        if (!passwordIsCorrect) {
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

            Manager manager = new Manager(firstName, lastName, newManagerSSN, password, email, phoneNumber, birthDate);
            employeeRepository.createProfile(manager);
            return "Manager registered successfully"; // The exact same logic as other "register" Classes.

        }
    }

