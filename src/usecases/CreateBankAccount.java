package usecases;

import domain.constants.BankAccountStatus;
import domain.entities.BankAccount;
import domain.exceptions.CustomerDoesNotExistException;
import domain.exceptions.EmployeeDoesNotExistException;
import repositories.BankAccountRepository;

import java.util.concurrent.ThreadLocalRandom;

public class CreateBankAccount {

    private ValidateCustomer validateCustomer;
    private ValidateEmployee validateEmployee;
    private BankAccountRepository bankAccountRepository;

    public CreateBankAccount() {
        this.validateCustomer = new ValidateCustomer();
        this.validateEmployee = new ValidateEmployee();
        this.bankAccountRepository = new BankAccountRepository();
    }

    public String execute(long employeeSSN, long customerSSN, String bankAccountName) throws Exception {
        //Checks if the employee exists
        boolean employeeExists = validateEmployee.execute(employeeSSN);
        if (!employeeExists){
            throw new EmployeeDoesNotExistException(employeeSSN);
        }

        //We check if the customer who is doing the application exists
        boolean customerExists = validateCustomer.execute(customerSSN);
        if (!customerExists){
            throw new CustomerDoesNotExistException(customerSSN);
        }

        //With this method we can generate an accountNumber
        long accountNumber = ThreadLocalRandom.current().nextLong(100000000,999999999);

        BankAccount bankAccount = new BankAccount(0, bankAccountName, accountNumber, customerSSN);
        bankAccount.setStatus(BankAccountStatus.approved);
        bankAccountRepository.createBankAccount(bankAccount);
        return "Bank account with " + accountNumber + " created successfully.";
    }

}
