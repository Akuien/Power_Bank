package domain.entities;

import java.util.Date;

public class Transaction {

    private long transactionId;
    private long originBankAccountNumber;
    private long finalBankAccountNumber;
    private double amount;
    private String type;
    private Date createdAt;

    public Transaction(long originBankAccountNumber, long finalBankAccountNumber, double amount, String type, Date createdAt){
        this.originBankAccountNumber = originBankAccountNumber;
        this.finalBankAccountNumber = finalBankAccountNumber;
        this.amount = amount;
        this.type = type;
        this.createdAt = createdAt;
    }

    public String toString(){
        return type + " Transaction " + transactionId + " from " + originBankAccountNumber + " to " + finalBankAccountNumber + " with " + amount + " SEK.";
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public long getOriginBankAccountNumber() {
        return originBankAccountNumber;
    }

    public void setOriginBankAccountNumber(long originBankAccountNumber) {
        this.originBankAccountNumber = originBankAccountNumber;
    }

    public long getFinalBankAccountNumber() {
        return finalBankAccountNumber;
    }

    public void setFinalBankAccountNumber(long finalBankAccountNumber) {
        this.finalBankAccountNumber = finalBankAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
