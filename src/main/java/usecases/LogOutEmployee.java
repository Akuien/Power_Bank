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
        //We check if the employee who is trying to log out has an access token or not.
        if (employee == null){
            throw new AccessTokenDoesNotExistException();
        }
        //We set the access token of the employee to null and therefore this customer is logged out
        employee.setAccessToken(null);
        employeeRepository.updateProfile(employee);
    }

}
