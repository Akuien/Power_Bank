package repositories;

import data.PersistenceData;
import domain.entities.BankAccount;
import domain.entities.Company;
import domain.entities.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransactionRepositoryTest {

    private ArrayList<Transaction> transactions;
    private PersistenceData data;
    private TransactionRepository transactionRepository;

    @org.junit.jupiter.api.BeforeEach
    void setUp(){

        transactionRepository = new TransactionRepository();
        transactions = new ArrayList<>();

        //Create 2 transactions
        //which will later be stored in the transactions.json file

        LocalDateTime now = LocalDateTime.now();
        Date transactionDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());


        Transaction transaction1 = new Transaction(991046478, 880375897, 50, "debit", transactionDate);
        Transaction transaction2 = new Transaction(880375897, 991046478, 20, "debit", transactionDate);

        transactions.add(transaction1);
        transactions.add(transaction2);

        transactionRepository.createTransaction(transaction1);
        transactionRepository.createTransaction(transaction2);
    }

    @org.junit.jupiter.api.Test
    void getAll() {

        ArrayList<Transaction> temp = transactionRepository.getAll();

        assertEquals(transactions.get(transactions.size()-1).toString(), temp.get(temp.size()-1).toString());

        assertEquals(transactions.get(transactions.size()-2).toString(), temp.get(temp.size()-2).toString());

        //Remove the last 2 added transactions from setup()
        //used for the test
        temp.remove(temp.size()-1);
        temp.remove(temp.size()-1);
        TransactionRepository.persistenceData.setTransactions(temp);
    }


    @org.junit.jupiter.api.Test
    void getTransactionsByAccountNumber() {

        ArrayList<Transaction> customer1Transactions = transactionRepository.getTransactionsByAccountNumber(991046478);
        ArrayList<Transaction> customer2Transactions = transactionRepository.getTransactionsByAccountNumber(880375897);


        assertEquals(transactions.get(0).toString(), customer1Transactions.get(customer1Transactions.size()-2).toString());

        assertEquals(transactions.get(1).toString(), customer2Transactions.get(customer2Transactions.size()-1).toString());


        ArrayList<Transaction> temp = transactionRepository.getAll();

        //Remove the last 2 added transactions from setup()
        //used for the test
        temp.remove(temp.size()-1);
        temp.remove(temp.size()-1);
        TransactionRepository.persistenceData.setTransactions(temp);
    }

    @org.junit.jupiter.api.Test
    void createTransaction() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Date transactionDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        Transaction test = new Transaction(991046478, 880375897, 24, "debit", transactionDate);

        transactionRepository.createTransaction(test);

        ArrayList<Transaction> customer1Transactions = transactionRepository.getTransactionsByAccountNumber(991046478);

        assertEquals(test.toString(), customer1Transactions.get(customer1Transactions.size()-1).toString());

        ArrayList<Transaction> temp = transactionRepository.getAll();

        //Remove the last 3 added transaction: (the first 2 from the setup() and the last one from this test)
        //used for the test
        temp.remove(temp.size()-1);
        temp.remove(temp.size()-1);
        temp.remove(temp.size()-1);
        TransactionRepository.persistenceData.setTransactions(temp);
    }
}