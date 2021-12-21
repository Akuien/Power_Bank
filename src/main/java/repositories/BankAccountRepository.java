package repositories;

import domain.entities.BankAccount;

import java.util.ArrayList;

public class BankAccountRepository extends AbstractRepository { // Each repository outside AbstractRepository is a child class of it.


    public BankAccount getAccountByName(String accountName) { // Method that checks all bank accounts to find an account with corresponding name.
        ArrayList<BankAccount> bankAccounts = BankAccountRepository.persistenceData.getBankAccounts();

        for (BankAccount currentBankAccount : bankAccounts){
            if (currentBankAccount.getName().equals(accountName)){
                return currentBankAccount;
            }
        }
        return null; // Returns null when bank accounts of that name doesn't exist.
    }


    public BankAccount getAccountByAccountNumber(long accountNumber) { // Same as above, but searches by account number.
        ArrayList<BankAccount> bankAccounts = BankAccountRepository.persistenceData.getBankAccounts();

        for (BankAccount currentBankAccount : bankAccounts){
            if (currentBankAccount.getAccountNumber() == accountNumber){
                return currentBankAccount;
            }
        }
        return null;
    }


    public ArrayList<BankAccount> getAccountsBySSN(long SSN) { // Method to search account by SSN number.
        ArrayList<BankAccount> bankAccounts = BankAccountRepository.persistenceData.getBankAccounts();
        ArrayList<BankAccount> customerBankAccounts = new ArrayList<>();
        for (BankAccount currentBankAccount : bankAccounts){
            if (currentBankAccount.getCustomerSSN() == SSN){
                customerBankAccounts.add(currentBankAccount);
            }
        }
        return customerBankAccounts;
    }


    public ArrayList<BankAccount> getAccountsByStatus(String status) { // Method to search bank accounts by status.
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
    } // Method to see all bank accounts.


    public void createBankAccount(BankAccount bankAccount) { // Creates new account and adds it to bankAccounts list.
        ArrayList<BankAccount> bankAccounts = BankAccountRepository.persistenceData.getBankAccounts();
        bankAccounts.add(bankAccount);
        BankAccountRepository.persistenceData.setBankAccounts(bankAccounts);
    }


    public void updateBankAccount(BankAccount bankAccount) { // Receives updated Bank Account.
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
