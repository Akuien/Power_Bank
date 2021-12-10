package usecases;

import domain.constants.BankAccountStatus;
import domain.entities.BankAccount;
import domain.exceptions.CustomerDoesNotExistException;
import domain.exceptions.EmployeeDoesNotExistException;

public class ChangeStatusBankAccount {

    ValidateCustomer validateCustomer;
    ValidateEmployee validateEmployee;

    public ChangeStatusBankAccount(){
        this.validateCustomer = new ValidateCustomer();
        this.validateEmployee = new ValidateEmployee();
    }

    public void execute(BankAccount bankAccount, long customerSSN, long employeeSSN, int choice) throws Exception {
        //Checks if customer exists
        boolean customerExists = validateCustomer.execute(customerSSN);
        if (!customerExists){
            throw new CustomerDoesNotExistException(customerSSN);
        }
        //Checks if the employee exists
        boolean employeeExists = validateEmployee.execute(employeeSSN);
        if (!employeeExists){
            throw new EmployeeDoesNotExistException(employeeSSN);
        }
        //Decides whether the worker accepts or rejects the bank account.
        if(choice == 1){
            bankAccount.setStatus(BankAccountStatus.approved);
        }
        else if (choice == 2){
            bankAccount.setStatus(BankAccountStatus.rejected);
        }
    }
}
