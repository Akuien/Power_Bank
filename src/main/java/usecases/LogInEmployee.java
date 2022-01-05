package usecases;

import domain.entities.Employee;
import domain.exceptions.EmailPasswordDoesNotExistException;
import repositories.EmployeeRepository;

import java.util.UUID;

public class LogInEmployee {

    EmployeeRepository employeeRepository;

    public LogInEmployee(){
        this.employeeRepository = new EmployeeRepository();
    }

    public String execute(String email, String password) throws Exception {
        Employee employee = employeeRepository.getByEmail(email);
        // We check if the employee assigned to the email exists and if the password matches the one kept in the database.
        if (employee == null || !employee.getPassword().equals(password)){
            throw new EmailPasswordDoesNotExistException();
        }
        // When an employee logs in we create him/her an access token to access the rest of the functionalities.
        employee.setAccessToken(UUID.randomUUID().toString());
        employeeRepository.updateProfile(employee);
        return employee.getAccessToken();
    }

}
