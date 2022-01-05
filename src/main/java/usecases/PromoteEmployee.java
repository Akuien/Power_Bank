package usecases;

import domain.constants.UserType;
import domain.entities.Employee;
import domain.exceptions.EmployeeDoesNotExistException;
import domain.exceptions.EmployeeIsManagerException;
import repositories.EmployeeRepository;

public class PromoteEmployee {

        private ValidateEmployee validateEmployee;
        private ValidateManager validateManager;
        private EmployeeRepository employeeRepository;

        public PromoteEmployee(){
            this.validateEmployee = new ValidateEmployee();
            this.validateManager = new ValidateManager();
            this.employeeRepository = new EmployeeRepository();
        }

        public String execute(long managerSSN, long employeeSSN) throws Exception {

            // Checks if the manager exists.
            boolean managerExists = validateManager.execute(managerSSN);
            if (!managerExists){
                throw new EmployeeDoesNotExistException(managerSSN);
            }

            // Checks if the employee exists.
            boolean employeeExists = validateEmployee.execute(employeeSSN);
            if (!employeeExists){
                throw new EmployeeDoesNotExistException(employeeSSN);
            }

            //Checks if the employee that we are trying to promote to manager is already a manager or not
            Employee employee = employeeRepository.getBySSN(employeeSSN);
            if (employee.getPosition().equals(UserType.manager)){
                throw new EmployeeIsManagerException(employeeSSN);
            }
            employee.setPosition(UserType.manager);
            employeeRepository.updateProfile(employee);

            return "Employee with " + employeeSSN + " was successfully promoted to manager.";
        }

}
