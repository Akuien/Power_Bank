package repositories;

import domain.entities.BankAccount;

import java.util.ArrayList;

public class BankAccountRepository extends AbstractRepository {


    public BankAccount getAccountByName(String accountName) {
        ArrayList<BankAccount> bankAccounts = BankAccountRepository.persistenceData.getBankAccounts();

        for (BankAccount currentBankAccount : bankAccounts){
            if (currentBankAccount.getName().equals(accountName)){
                return currentBankAccount;
            }
        }
        return null;
    }


    public BankAccount getAccountByAccountNumber(long accountNumber) {
        ArrayList<BankAccount> bankAccounts = BankAccountRepository.persistenceData.getBankAccounts();

        for (BankAccount currentBankAccount : bankAccounts){
            if (currentBankAccount.getAccountNumber() == accountNumber){
                return currentBankAccount;
            }
        }
        return null;
    }


    public ArrayList<BankAccount> getAccountsBySSN(long SSN) {
        ArrayList<BankAccount> bankAccounts = BankAccountRepository.persistenceData.getBankAccounts();
        ArrayList<BankAccount> customerBankAccounts = new ArrayList<BankAccount>();
        for (BankAccount currentBankAccount : bankAccounts){
            if (currentBankAccount.getCustomerSSN() == SSN){
                customerBankAccounts.add(currentBankAccount);
            }
        }
        return customerBankAccounts;
    }


    public ArrayList<BankAccount> getAccountsByStatus(String status) {
        ArrayList<BankAccount> bankAccounts = BankAccountRepository.persistenceData.getBankAccounts();
        ArrayList<BankAccount> statusBankAccounts = new ArrayList<>();
        for (BankAccount currentBankAccount : bankAccounts){
            if (currentBankAccount.getStatus().equals(status)){
                statusBankAccounts.add(currentBankAccount);
            }
        }
        return statusBankAccounts;
    }


    public ArrayList<BankAccount> getAll() {
        return BankAccountRepository.persistenceData.getBankAccounts();
    }


    public void createBankAccount(BankAccount bankAccount) {
        ArrayList<BankAccount> bankAccounts = BankAccountRepository.persistenceData.getBankAccounts();
        bankAccounts.add(bankAccount);
        BankAccountRepository.persistenceData.setBankAccounts(bankAccounts);
    }


    //receives updated Bank Account
    public void updateBankAccount(BankAccount bankAccount) {
        ArrayList<BankAccount> bankAccounts = BankAccountRepository.persistenceData.getBankAccounts();
        for (BankAccount currentBankAccount : bankAccounts){
            if (currentBankAccount.equals(bankAccount)){
                currentBankAccount.setBalance(bankAccount.getBalance());
                currentBankAccount.setName(bankAccount.getName());
                currentBankAccount.setStatus(bankAccount.getStatus());
            }
        }
        BankAccountRepository.persistenceData.setBankAccounts(bankAccounts);
    }
}
