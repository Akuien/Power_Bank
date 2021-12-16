package usecases;

import domain.constants.BankAccountStatus;
import domain.constants.MortgageStatus;
import domain.entities.BankAccount;
import domain.entities.Mortgage;
import domain.exceptions.*;
import repositories.MortgageRepository;

public class ChangeStatusMortgage {
    private ValidateCustomer validateCustomer;
    private ValidateCustomerBankAccount validateCustomerBankAccount;
    private ValidateFunds validateFunds;
    private ValidateEmployee validateEmployee;
    private MortgageRepository mortgageRepository;

    public ChangeStatusMortgage(){
        this.validateCustomer = new ValidateCustomer();
        this.validateCustomerBankAccount = new ValidateCustomerBankAccount();
        this.validateFunds = new ValidateFunds();
        this.validateEmployee = new ValidateEmployee();
        this.mortgageRepository = new MortgageRepository();
    }

    public String execute(long customerSSN, long accountNumber, long mortgageLoanID, long employeeSSN, String choice) throws Exception {

        //Checks if the employee exists
        boolean employeeExists = validateEmployee.execute(employeeSSN);
        if (!employeeExists){
            throw new EmployeeDoesNotExistException(employeeSSN);
        }

        //Checks if customer exists
        boolean customerExists = validateCustomer.execute(customerSSN);
        if (!customerExists){
            throw new CustomerDoesNotExistException(customerSSN);
        }

        //Check if the bank account exists
        boolean bankAccountExists = validateCustomerBankAccount.execute(customerSSN, accountNumber);
        if (!bankAccountExists){
            throw new BankAccountDoesNotExistException(accountNumber);
        }

        //Checks if the mortgage exists
        boolean mortgageExists = validateMortgage(mortgageLoanID);
        if (!mortgageExists){
            throw new MortgageDoesNotExistException(mortgageLoanID);
        }

        Mortgage mortgage = mortgageRepository.getMortgageByLoanID(mortgageLoanID);

        //Checks if bank account has enough funds
        boolean hasEnoughFunds = validateFunds.execute(accountNumber, mortgage.getInitialDeposit());
        if (!hasEnoughFunds){
            mortgage.setStatus(MortgageStatus.rejected);
            mortgageRepository.updateMortgage(mortgage);
            return "Mortgage has been rejected due to the lack of funds";
        }

        //Checks if status is either rejected or approved
        if (!choice.toLowerCase().equals(MortgageStatus.approved) || !choice.toLowerCase().equals(MortgageStatus.rejected)){
            throw new StatusNotAllowedException();
        }

        mortgage.setStatus(choice);
        mortgageRepository.updateMortgage(mortgage);

        if (mortgage.getStatus().equals(MortgageStatus.approved)){
            return "Mortgage "+ mortgageLoanID + " approved successfully";
        }
        else{
            return "Mortgage "+ mortgageLoanID + " rejected successfully";
        }
    }

    private boolean validateMortgage(long mortgageLoanID){
        return mortgageRepository.getMortgageByLoanID(mortgageLoanID) != null;
    }
}
