package usecases;

import domain.constants.BankAccountStatus;
import domain.constants.MortgageStatus;
import domain.entities.BankAccount;
import domain.exceptions.BankAccountDoesNotExistException;
import domain.exceptions.CustomerDoesNotExistException;
import domain.exceptions.EmployeeDoesNotExistException;
import domain.exceptions.StatusNotAllowedException;
import repositories.BankAccountRepository;

public class ChangeStatusBankAccount {

    private ValidateCustomer validateCustomer;
    private ValidateEmployee validateEmployee;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private BankAccountRepository bankAccountRepository;

    public ChangeStatusBankAccount(){
        this.validateCustomer = new ValidateCustomer();
        this.validateEmployee = new ValidateEmployee();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.bankAccountRepository = new BankAccountRepository();
    }

    public String execute(long accountNumber, long customerSSN, long employeeSSN, String choice) throws Exception {
        // Checks if the employee exists.
        boolean employeeExists = validateEmployee.execute(employeeSSN);
        if (!employeeExists){
            throw new EmployeeDoesNotExistException(employeeSSN);
        }
        // Checks if bank account exists.
        boolean bankAccountExists = validateCustomerBankAccount.execute(customerSSN, accountNumber);
        if (!bankAccountExists){
            throw new BankAccountDoesNotExistException(accountNumber);
        }
        // Checks if customer exists.
        boolean customerExists = validateCustomer.execute(customerSSN);
        if (!customerExists){
            throw new CustomerDoesNotExistException(customerSSN);
        }
        // Checks if status is either rejected or approved.
        if (!choice.toLowerCase().equals(BankAccountStatus.approved) || !choice.toLowerCase().equals(BankAccountStatus.rejected)){
            throw new StatusNotAllowedException();
        }

        BankAccount bankAccount = bankAccountRepository.getAccountByAccountNumber(accountNumber);
        bankAccount.setStatus(choice);
        bankAccountRepository.updateBankAccount(bankAccount);

        if (bankAccount.getStatus().equals(BankAccountStatus.approved)){
            return "Bank account "+ accountNumber + " approved successfully";
        }
        else{
            return "Bank account "+ accountNumber + " rejected successfully";
        }
    }
}
