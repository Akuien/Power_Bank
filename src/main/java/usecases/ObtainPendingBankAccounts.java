package usecases;

import domain.constants.BankAccountStatus;
import domain.entities.BankAccount;
import repositories.BankAccountRepository;

import java.util.ArrayList;

public class ObtainPendingBankAccounts {

        //Calling BankAccountRepository class as a component.
        private BankAccountRepository bankAccountRepository;
        //Creating a constructor.
        public ObtainPendingBankAccounts( ){
            this.bankAccountRepository = new BankAccountRepository();
        }
        //Making a method where it returns a list of bank accounts of type "pending".
        public ArrayList<BankAccount> execute(){
            return bankAccountRepository.getAccountsByStatus(BankAccountStatus.pending) ;
        }


}
