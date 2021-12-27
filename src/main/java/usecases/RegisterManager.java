package usecases;

import domain.entities.Employee;
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

        boolean managerExists = validateManager.execute(managerSSN);
        if (!managerExists) {
            throw new ManagerDoesNotExistException(managerSSN);
        }

        boolean newManagerExists = validateManager.execute(newManagerSSN);
        if (newManagerExists) {
            throw new ManagerAlreadyExistsException(newManagerSSN);
        }

        boolean firstNameIsCorrect = !firstName.isBlank();
        if (!firstNameIsCorrect) {
            throw new NameIsBlankException();
        }

        boolean lastNameIsCorrect = !lastName.isBlank();
        if (!lastNameIsCorrect) {
            throw new NameIsBlankException();
        }

        boolean passwordIsCorrect = validatePassword.execute(password);
        if (!passwordIsCorrect) {
            throw new IncorrectPasswordException();
        }

        boolean emailIsCorrect = validateEmail.execute(email);
        if (!emailIsCorrect) {
            throw new IncorrectEmailException();
        }

        boolean phoneNumberIsCorrect = validatePhoneNumber.execute(phoneNumber);
        if (!phoneNumberIsCorrect) {
            throw new IncorrectPhoneNumberException();
        }

            Employee employee = new Employee(firstName, lastName, newManagerSSN, password, email, phoneNumber, birthDate);
            employeeRepository.createProfile(employee);
            return "Customer registered successfully"; // The exact same logic as other "register" Classes.

        }
    }

