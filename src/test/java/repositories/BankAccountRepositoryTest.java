package repositories;

import domain.entities.BankAccount;

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

        BankAccount account1 = new BankAccount(100, "Elizabeth Banks", 481046478, 970213742);
        BankAccount account2 = new BankAccount(300, "Michaela Snow", 740375897, 880702813);

        bankAccountRepository.createBankAccount(account1);
        bankAccountRepository.createBankAccount(account2);

        assertEquals(account1.toString(), bankAccountRepository.getAccountByName("Elizabeth Banks").toString());

        assertEquals(account2.toString(), bankAccountRepository.getAccountByName("Michaela Snow").toString());
    }

    @org.junit.jupiter.api.Test
    void getAccountByAccountNumber() {
        //Create 2 bankAccounts
        //which will be stored in the bankAccounts.json file

        BankAccount account1 = new BankAccount(100, "Elizabeth Banks", 481046478, 970213742);
        BankAccount account2 = new BankAccount(300, "Michaela Snow", 740375897, 880702813);

        bankAccountRepository.createBankAccount(account1);
        bankAccountRepository.createBankAccount(account2);

        assertEquals(account1.toString(), bankAccountRepository.getAccountByAccountNumber(481046478).toString());

        assertEquals(account2.toString(), bankAccountRepository.getAccountByAccountNumber(740375897).toString());
    }

    @org.junit.jupiter.api.Test
    void getAccountsBySSN() {
        //Create 2 bankAccounts
        //which will be stored in the bankAccounts.json file

        BankAccount account1 = new BankAccount(100, "Elizabeth Banks", 481046478, 970213742);
        BankAccount account2 = new BankAccount(300, "Michaela Snow", 740375897, 880702813);

        bankAccountRepository.createBankAccount(account1);
        bankAccountRepository.createBankAccount(account2);

        assertEquals(account1.toString(), bankAccountRepository.getAccountsBySSN(970213742).toString());

        assertEquals(account2.toString(), bankAccountRepository.getAccountsBySSN(880702813).toString());
    }

    @org.junit.jupiter.api.Test
    void getAccountsByStatus() {
        //Create 3 bankAccounts
        //which will be stored in the bankAccounts.json file

        BankAccount account1 = new BankAccount(100, "Elizabeth Banks", 481046478, 970213742);
        BankAccount account2 = new BankAccount(300, "Michaela Snow", 740375897, 880702813);
        BankAccount account3 = new BankAccount(50, "Michaela Snow", 740375888, 880702813);


        account1.setStatus("accepted");
        account2.setStatus("pending");
        account3.setStatus("rejected");

        bankAccountRepository.createBankAccount(account1);
        bankAccountRepository.createBankAccount(account2);
        bankAccountRepository.createBankAccount(account3);

        assertEquals(account1.toString(), bankAccountRepository.getAccountsByStatus("accepted").get(0).toString());

        assertEquals(account2.toString(), bankAccountRepository.getAccountsByStatus("pending").get(0).toString());

        assertEquals(account3.toString(), bankAccountRepository.getAccountsByStatus("rejected").get(0).toString());
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        //Create 2 bankAccounts and add them to the arraylist
        //which will later be stored in the bankAccounts.json file


        //We create 2 bankAccounts,
        // and then add them to the bankAccounts arraylist
        BankAccount account1 = new BankAccount(100, "Elizabeth Banks", 481046478, 970213742);
        BankAccount account2 = new BankAccount(300, "Michaela Snow", 740375897, 880702813);
        ArrayList<BankAccount> temp = new ArrayList<>();
        temp.add(account1);
        temp.add(account2);

        bankAccountRepository.createBankAccount(account1);
        bankAccountRepository.createBankAccount(account2);

        assertEquals(temp.size(), bankAccountRepository.getAll().size());

        assertEquals(temp.get(0).toString(), bankAccountRepository.getAll().get(0).toString());

        assertEquals(temp.toString(), bankAccountRepository.getAll().toString());
    }

    @org.junit.jupiter.api.Test
    void createBankAccount() {
        BankAccount account1 = new BankAccount(100, "Elizabeth Banks", 481046478, 970213742);

        bankAccountRepository.createBankAccount(account1);

        assertEquals(account1.toString(), bankAccountRepository.getAccountByAccountNumber(481046478).toString());
    }

    @org.junit.jupiter.api.Test
    void updateBankAccount() {
        BankAccount test1 = new BankAccount(100, "Elizabeth Banks", 481046478, 970213742);

        bankAccountRepository.createBankAccount(test1);

        BankAccount test2 = new BankAccount(250, "Elizabeth Banks", 481046478, 970213742);

        bankAccountRepository.updateBankAccount(test2);

        assertEquals(test2.toString(), bankAccountRepository.getAccountByAccountNumber(481046478).toString());
    }
}