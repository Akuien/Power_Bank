package usecases;

import domain.entities.Employee;
import domain.exceptions.AccessTokenDoesNotExistException;
import repositories.EmployeeRepository;

public class LogOutEmployee {

    private EmployeeRepository employeeRepository;

    public LogOutEmployee( ){
        this.employeeRepository = new EmployeeRepository();
    }

    public void execute(String accessToken) throws Exception {
        Employee employee = employeeRepository.getByAccessToken(accessToken);
        if (employee == null){
            throw new AccessTokenDoesNotExistException();
        }
        employee.setAccessToken(null);
        employeeRepository.updateProfile(employee);
    }

}
