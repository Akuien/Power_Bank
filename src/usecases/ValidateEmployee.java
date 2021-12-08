package usecases;

import domain.entities.Employee;

import java.util.ArrayList;

public class ValidateEmployee {

    private ObtainEmployees obtainEmployees;

    public ValidateEmployee( ){
        this.obtainEmployees = new ObtainEmployees();
    }

    public boolean execute(long SSN){
        ArrayList<Employee> employees = this.obtainEmployees.execute();
        for (Employee currentEmployee : employees){
            if (currentEmployee.getSSN() == SSN){
                return true;
            }
        }
        return false;
    }

}
