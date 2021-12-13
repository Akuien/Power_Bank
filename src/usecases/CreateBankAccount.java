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
}
