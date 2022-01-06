package repositories;

import data.DataSetInitializer;
import domain.entities.BankAccount;
import domain.entities.Employee;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountRepositoryTest {

    private BankAccountRepository bankAccountRepository;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        bankAccountRepository = new BankAccountRepository();
    }

    @org.junit.jupiter.api.Test
    void getAccountByName() {
        //Create 2 bankAccounts
        //which will be stored in the bankAccounts.json file

        BankAccount account1 = new BankAccount(100, "Hannah Franklin", 991046478, 970215742);
        BankAccount account2 = new BankAccount(300, "Kate Barnes", 880375897, 880709813);

        bankAccountRepository.createBankAccount(account1);
        bankAccountRepository.createBankAccount(account2);

        assertEquals(account1.toString(), bankAccountRepository.getAccountByName("Hannah Franklin").toString());

        assertEquals(account2.toString(), bankAccountRepository.getAccountByName("Kate Barnes").toString());

        ArrayList<BankAccount> temp = bankAccountRepository.getAll();

        //Remove the last 2 added bankAccounts
        //used for the test
        temp.remove(temp.size()-1);
        temp.remove(temp.size()-1);
        BankAccountRepository.persistenceData.setBankAccounts(temp);
    }

    @org.junit.jupiter.api.Test
    void getAccountByAccountNumber() {
        //Create 2 bankAccounts
        //which will be stored in the bankAccounts.json file

        BankAccount account1 = new BankAccount(100, "Hannah Franklin", 991046478, 970215742);
        BankAccount account2 = new BankAccount(300, "Kate Barnes", 880375897, 880709813);

        bankAccountRepository.createBankAccount(account1);
        bankAccountRepository.createBankAccount(account2);

        assertEquals(account1.toString(), bankAccountRepository.getAccountByAccountNumber(991046478).toString());

        assertEquals(account2.toString(), bankAccountRepository.getAccountByAccountNumber(880375897).toString());

        ArrayList<BankAccount> temp = bankAccountRepository.getAll();

        //Remove the last 2 added bankAccounts
        //used for the test
        temp.remove(temp.size()-1);
        temp.remove(temp.size()-1);
        BankAccountRepository.persistenceData.setBankAccounts(temp);

    }

    @org.junit.jupiter.api.Test
    void getAccountsBySSN() {
        //Create 2 bankAccounts
        //which will be stored in the bankAccounts.json file

        BankAccount account1 = new BankAccount(100, "Hannah Franklin", 991046478, 970215742);
        BankAccount account2 = new BankAccount(300, "Kate Barnes", 880375897, 880709813);

        bankAccountRepository.createBankAccount(account1);
        bankAccountRepository.createBankAccount(account2);

        assertEquals(account1.toString(), bankAccountRepository.getAccountsBySSN(970215742).get(0).toString());

        assertEquals(account2.toString(), bankAccountRepository.getAccountsBySSN(880709813).get(0).toString());

        ArrayList<BankAccount> temp = bankAccountRepository.getAll();

        //Remove the last 2 added bankAccounts
        //used for the test
        temp.remove(temp.size()-1);
        temp.remove(temp.size()-1);
        BankAccountRepository.persistenceData.setBankAccounts(temp);
    }

    @org.junit.jupiter.api.Test
    void getAccountsByStatus() {
        //Create 3 bankAccounts
        //which will be stored in the bankAccounts.json file

        BankAccount account1 = new BankAccount(100, "Hannah Franklin", 991046478, 970215742);
        BankAccount account2 = new BankAccount(300, "Kate Barnes", 880375897, 880709813);
        BankAccount account3 = new BankAccount(50, "Kate Barnes", 740375888, 880709813);


        account1.setStatus("approved");
        account2.setStatus("pending");
        account3.setStatus("rejected");

        bankAccountRepository.createBankAccount(account1);
        bankAccountRepository.createBankAccount(account2);
        bankAccountRepository.createBankAccount(account3);

        ArrayList<BankAccount> approvedAccounts = bankAccountRepository.getAccountsByStatus("approved");
        ArrayList<BankAccount> pendingAccounts = bankAccountRepository.getAccountsByStatus("pending");
        ArrayList<BankAccount> rejectedAccounts = bankAccountRepository.getAccountsByStatus("rejected");

        assertEquals(account1.toString(), approvedAccounts.get(approvedAccounts.size()-1).toString());

        assertEquals(account2.toString(), pendingAccounts.get(pendingAccounts.size()-1).toString());

        assertEquals(account3.toString(), rejectedAccounts.get(rejectedAccounts.size()-1).toString());

        ArrayList<BankAccount> temp = bankAccountRepository.getAll();

        //Remove the last 3 added bankAccounts
        //used for the test
        temp.remove(temp.size()-1);
        temp.remove(temp.size()-1);
        temp.remove(temp.size()-1);
        BankAccountRepository.persistenceData.setBankAccounts(temp);
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        //Create 2 bankAccounts and add them to the arraylist
        //which will later be stored in the bankAccounts.json file


        //We create 2 bankAccounts,
        // and then add them to the bankAccounts arraylist
        BankAccount account1 = new BankAccount(100, "Hannah Franklin", 991046478, 970215742);
        BankAccount account2 = new BankAccount(300, "Kate Barnes", 880375897, 880709813);
        ArrayList<BankAccount> temp = new ArrayList<>();
        temp.add(account1);
        temp.add(account2);

        bankAccountRepository.createBankAccount(account1);
        bankAccountRepository.createBankAccount(account2);

        ArrayList<BankAccount> temp2 = bankAccountRepository.getAll();

        assertEquals(temp.get(temp.size()-1).toString(), temp2.get(temp2.size()-1).toString());

        assertEquals(temp.get(temp.size()-2).toString(), temp2.get(temp2.size()-2).toString());

        //Remove the last 2 added bankAccounts
        //used for the test
        temp2.remove(temp2.size()-1);
        temp2.remove(temp2.size()-1);
        BankAccountRepository.persistenceData.setBankAccounts(temp2);
    }

    @org.junit.jupiter.api.Test
    void createBankAccount() {
        BankAccount account1 = new BankAccount(100, "Hannah Franklin", 991046478, 970215742);

        bankAccountRepository.createBankAccount(account1);

        assertEquals(account1.toString(), bankAccountRepository.getAccountByAccountNumber(991046478).toString());

        ArrayList<BankAccount> temp = bankAccountRepository.getAll();

        //Remove the last added bankAccount
        //used for the test
        temp.remove(temp.size()-1);
        BankAccountRepository.persistenceData.setBankAccounts(temp);
    }

    @org.junit.jupiter.api.Test
    void updateBankAccount() {
        BankAccount test1 = new BankAccount(100, "Hannah Franklin", 991046478, 970215742);

        bankAccountRepository.createBankAccount(test1);

        BankAccount test2 = new BankAccount(250, "Hannah Franklin", 991046478, 970215742);

        bankAccountRepository.updateBankAccount(test2);

        assertEquals(test2.toString(), bankAccountRepository.getAccountByAccountNumber(991046478).toString());

        ArrayList<BankAccount> temp = bankAccountRepository.getAll();

        //Remove the last added bankAccount
        //used for the test
        temp.remove(temp.size()-1);
        BankAccountRepository.persistenceData.setBankAccounts(temp);
    }
}