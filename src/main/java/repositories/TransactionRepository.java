package repositories;

import domain.entities.Transaction;

import java.util.ArrayList;

public class TransactionRepository extends AbstractRepository{

    public ArrayList<Transaction> getAll() {
        return TransactionRepository.persistenceData.getTransactions();
    }


    public ArrayList<Transaction> getTransactionsByAccountNumber(long accountNumber) {
        ArrayList<Transaction> transactions = TransactionRepository.persistenceData.getTransactions();
        ArrayList<Transaction> bankAccountTransactions = new ArrayList<Transaction>();
        for (Transaction currentTransaction : transactions){
            if (accountNumber == currentTransaction.getOriginBankAccountNumber() || accountNumber == currentTransaction.getFinalBankAccountNumber()){
                bankAccountTransactions.add(currentTransaction);
            }
        }
        return bankAccountTransactions;
    }


    public void createTransaction(Transaction transaction) {
        ArrayList<Transaction> transactions = TransactionRepository.persistenceData.getTransactions();
        transactions.add(transaction);
        TransactionRepository.persistenceData.setTransactions(transactions);
    }

}
