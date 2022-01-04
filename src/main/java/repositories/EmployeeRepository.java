package repositories;

import domain.entities.Employee;

import java.util.ArrayList;

public class EmployeeRepository extends AbstractRepository { // This Repository handles Employee data.


    public ArrayList<Employee> getAll() {
        return EmployeeRepository.persistenceData.getEmployees();
    } // Returns entire Employee list.


    public Employee getBySSN(long SSN) { // Search for employee by SSN.
        ArrayList<Employee> employees = EmployeeRepository.persistenceData.getEmployees();
        Employee foundEmployee = null;
        for (Employee employee : employees){
            if (employee.getSSN() == SSN){
                foundEmployee = employee;
            }
        }
        return foundEmployee;
    }


    public Employee getByAccessToken(String accessToken) { // Checks the Employees access token.
        ArrayList<Employee> employees = EmployeeRepository.persistenceData.getEmployees();
        Employee foundEmployee = null;
        for (Employee employee : employees){
            if (employee.getAccessToken() != null && employee.getAccessToken().equals(accessToken)){
                foundEmployee = employee;
            }
        }
        return foundEmployee;
    }


    public Employee getByEmail(String email) {
        ArrayList<Employee> employees = EmployeeRepository.persistenceData.getEmployees();
        Employee foundEmployee = null;
        for (Employee employee : employees){
            if (employee.getEmail().equals(email)){
                foundEmployee = employee;
            }
        }
        return foundEmployee;
    }


    public void createProfile(Employee employee) { // Creates an Employee profile inside the corresponding list.
        ArrayList<Employee> employees = EmployeeRepository.persistenceData.getEmployees();
        employees.add(employee);
        EmployeeRepository.persistenceData.setEmployees(employees);
    }


    public void updateProfile(Employee employee) { // Updates an Employee profile information.
        ArrayList<Employee> employees = EmployeeRepository.persistenceData.getEmployees();
        for (Employee currentEmployee : employees){
            if (currentEmployee.equals(employee)){
                currentEmployee.setFirstName(employee.getFirstName());
                currentEmployee.setLastName(employee.getLastName());
                currentEmployee.setPassword(employee.getPassword());
                currentEmployee.setEmail(employee.getEmail());
                currentEmployee.setPhoneNumber(employee.getPhoneNumber());
                currentEmployee.setBirthDate(employee.getBirthDate());
                currentEmployee.setAccessToken(employee.getAccessToken());
                currentEmployee.setPosition(employee.getPosition());
            }
        }
        EmployeeRepository.persistenceData.setEmployees(employees);
    }
}
