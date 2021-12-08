package usecases;

import domain.constants.UserType;
import domain.entities.Employee;
import repositories.EmployeeRepository;

public class ValidateManager {

    private ValidateEmployee validateEmployee;
    private EmployeeRepository employeeRepository;

    public ValidateManager() {
        this.validateEmployee = new ValidateEmployee();
        this.employeeRepository = new EmployeeRepository();
    }

    public boolean execute(long SSN){
        boolean employeeExists = validateEmployee.execute(SSN);
        if(!employeeExists){
            return false;
        }

        Employee manager = this.employeeRepository.getBySSN(SSN);
        return manager.getPosition().equals(UserType.manager);
    }
}
