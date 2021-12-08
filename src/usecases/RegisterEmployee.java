package usecases;

import domain.entities.Employee;
import repositories.EmployeeRepository;

import java.util.Date;


public class RegisterEmployee {

    private ValidateEmployee validateEmployee;
    private ValidatePassword validatePassword;
    private ValidateEmail validateEmail;
    private EmployeeRepository employeeRepository;
    private ValidatePhoneNumber validatePhoneNumber;
    public RegisterEmployee(){
        this.validateEmployee = new ValidateEmployee();
        this.validateEmail = new ValidateEmail();
        this.validatePassword = new ValidatePassword();
        this.employeeRepository = new EmployeeRepository();
        this.validatePhoneNumber = new ValidatePhoneNumber();
    }
    public String registerEmployee(String firstName, String lastName, long SSN, String password, String email, String phoneNumber, Date birthDate) {

        if (validateEmployee.execute(SSN)) {
            return "This customer is already registered";

        }  else if (firstName.isBlank()) {
            return "Please enter your first name";

        } else if (lastName.isBlank()) {
            return "Please enter your last name";

        } else if (!validatePassword.execute(password)) {
            return "Password is weak, must eat more protein";

        } else if (!validateEmail.execute(email)) {
            return "Invalid email";

        } else if (!validatePhoneNumber.execute(phoneNumber)) {
            return "Invalid number";

        } else {
            Employee employee = new Employee(firstName, lastName, SSN, password, email, phoneNumber, birthDate);
            employeeRepository.createProfile(employee);
            return "Customer registered successfully"; // add toString later
        }
    }

}
