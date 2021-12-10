package usecases;

import domain.constants.BankAccountStatus;
import domain.entities.BankAccount;
import domain.entities.Mortgage;
import domain.exceptions.BankAccountDoesNotExistException;
import domain.exceptions.CustomerDoesNotExistException;
import domain.exceptions.DoesNotHaveEnoughFundsException;
import domain.exceptions.EmployeeDoesNotExistException;

public class ChangeStatusMortgage {

    ValidateCustomer validateCustomer;
    ValidateCustomerBankAccount validateCustomerBankAccount;
    ValidateFunds validateFunds;
    ValidateEmployee validateEmployee;

    public ChangeStatusMortgage(){
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.validateFunds = new ValidateFunds();
        this.validateEmployee = new ValidateEmployee();
    }

    public void execute(BankAccount bankAccount, Mortgage mortgage, long employeeSSN, int choice) throws Exception {

        //Checks if customer exists
        boolean customerExists = validateCustomer.execute(bankAccount.getCustomerSSN());
        if (!customerExists){
            throw new CustomerDoesNotExistException(bankAccount.getCustomerSSN());
        }

        //Check if the bank account exists
        boolean bankAccountExists = validateCustomerBankAccount.execute(bankAccount.getCustomerSSN(), bankAccount.getAccountNumber());
        if (!bankAccountExists){
            throw new BankAccountDoesNotExistException(bankAccount.getAccountNumber());
        }

        //Checks if the employee exists
        boolean employeeExists = validateEmployee.execute(employeeSSN);
        if (!employeeExists){
            throw new EmployeeDoesNotExistException(employeeSSN);
        }

        //Checks if bank account has enough funds
        boolean hasEnoughFunds = validateFunds.execute(bankAccount.getAccountNumber(), mortgage.getInitialDeposit());
        if (!hasEnoughFunds){
            throw new DoesNotHaveEnoughFundsException();
        }

        //Decides whether the worker accepts or rejects the bank account.
        if(choice == 1){
            mortgage.setStatus(BankAccountStatus.approved);
        }
        else if (choice == 2){
            mortgage.setStatus(BankAccountStatus.rejected);
        }
    }
}
