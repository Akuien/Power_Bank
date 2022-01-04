package domain.entities;

import domain.constants.BankAccountStatus;

public class BankAccount {

    private double balance;
    private String name;
    private long accountNumber;
    private String status;
    private long customerSSN;

    public BankAccount (double balance, String name, long accountNumber, long customerSSN){
        this.balance = balance;
        this.name = name;
        this.accountNumber = accountNumber;
        this.status = BankAccountStatus.pending;
        this.customerSSN = customerSSN;
    }

    public String toString(){
        return this.status + ": " + "Account(" +this.name+ ") - " + this.accountNumber +": has " + this.balance + " SEK.";
    }

    public boolean equals(BankAccount bankAccount){
        return this.getCustomerSSN() == bankAccount.getCustomerSSN() && this.getAccountNumber() == bankAccount.getAccountNumber();
        //Checks if bank account is the same using the customerSSN and bankAccountNumber
    }

    //Getters and setters
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCustomerSSN() {
        return customerSSN;
    }

    public void setCustomerSSN(long customerSSN) {
        this.customerSSN = customerSSN;
    }

    // FixedDeposit is a class within the bankAccount class
    private static class FixedDeposit{

        private static final double interestRate = 0.05;
        private static final int compoundInterest = 4;
        private double maturityValue;

        double calculateFixedDeposit(double initialDeposit, int period){

            return maturityValue = Math.pow(initialDeposit*(1 + interestRate/compoundInterest), compoundInterest*period);
        }

        double getMaturityValue() {
            return maturityValue;
        }

    }


}

