package repositories;

import data.PersistenceData;
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
        //Create 2 transactions
        //which will later be stored in the transactions.json file

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Date transactionDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());


        Transaction transaction1 = new Transaction(740375897, 481046478, 50, "debit", transactionDate);
        Transaction transaction2 = new Transaction(481046478, 740375897, 20, "debit", transactionDate);
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);

        data = new PersistenceData();
        data.setTransactions(transactions);
        transactionRepository = new TransactionRepository();
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        assertEquals(transactions.toString(), transactionRepository.getAll().toString());
    }


    @org.junit.jupiter.api.Test
    void getTransactionsByAccountNumber() {
        assertEquals(transactions.get(0).toString(), transactionRepository.getTransactionsByAccountNumber(740375897).toString());

        assertEquals(transactions.get(1).toString(), transactionRepository.getTransactionsByAccountNumber(481046478).toString());
    }

    @org.junit.jupiter.api.Test
    void createTransaction() {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Date transactionDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        Transaction test = new Transaction(740375897, 481046478, 24, "debit", transactionDate);

        transactionRepository.createTransaction(test);

        assertEquals(test.toString(), transactionRepository.getTransactionsByAccountNumber(740375897).toString());
    }
}