package repositories;

import domain.entities.Employee;

import java.util.ArrayList;

public class EmployeeRepository extends AbstractRepository {


    public ArrayList<Employee> getAll() {
        return EmployeeRepository.persistenceData.getEmployees();
    }


    public Employee getBySSN(long SSN) {
        ArrayList<Employee> employees = EmployeeRepository.persistenceData.getEmployees();
        Employee foundEmployee = null;
        for (Employee employee : employees){
            if (employee.getSSN() == SSN){
                foundEmployee = employee;
            }
        }
        return foundEmployee;
    }


    public Employee getByAccessToken(String accessToken) {
        ArrayList<Employee> employees = EmployeeRepository.persistenceData.getEmployees();
        Employee foundEmployee = null;
        for (Employee employee : employees){
            if (employee.getAccessToken() == accessToken){
                foundEmployee = employee;
            }
        }
        return foundEmployee;
    }


    public Employee getByEmail(String email) {
        ArrayList<Employee> employees = EmployeeRepository.persistenceData.getEmployees();
        Employee foundEmployee = null;
        for (Employee employee : employees){
            if (employee.getEmail() == email){
                foundEmployee = employee;
            }
        }
        return foundEmployee;
    }


    public void createProfile(Employee employee) {
        ArrayList<Employee> employees = EmployeeRepository.persistenceData.getEmployees();
        employees.add(employee);
        EmployeeRepository.persistenceData.setEmployees(employees);
    }


    public void updateProfile(Employee employee) {
        ArrayList<Employee> employees = EmployeeRepository.persistenceData.getEmployees();
        for (Employee currentEmployee : employees){
            if (currentEmployee.equals(employee)){
                currentEmployee.setFullName(employee.getFullName());
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
