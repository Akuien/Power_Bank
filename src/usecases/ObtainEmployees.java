package usecases;

import domain.entities.Customer;
import domain.entities.Employee;
import repositories.CustomerRepository;
import repositories.EmployeeRepository;

import java.util.ArrayList;

public class ObtainEmployees {

    private EmployeeRepository employeeRepository;

    public ObtainEmployees(){
        this.employeeRepository = new EmployeeRepository();
    }

    public ArrayList<Employee> execute(){
        return this.employeeRepository.getAll();
    }
}
