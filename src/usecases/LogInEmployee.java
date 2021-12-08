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
        if (employee == null || !employee.getPassword().equals(password)){
            throw new EmailPasswordDoesNotExistException();
        }
        employee.setAccessToken(UUID.randomUUID().toString());
        employeeRepository.updateProfile(employee);
        return employee.getAccessToken();
    }

}
