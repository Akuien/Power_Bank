package domain.entities;

import java.util.Date;

public class Stock {

    private Date date;
    private String company;
    private int quantity;
    private long customerSSN;
    private long customerAccountNumber;

    public Stock(Date date, String company, int quantity, long customerSSN, long customerAccountNumber){
        this.date = date;
        this.company = company;
        this.quantity = quantity;
        this.customerSSN = customerSSN;
        this.customerAccountNumber = customerAccountNumber;

    }

    public boolean equals(double companyStockPrice, long customerSSN, Date date, String company){
        return (this.getCustomerSSN() == customerSSN && (this.getDate().equals(date) && (this.getCompany().equals(company))));
    }

    public String toString(){
        return "Company: " + this.company + ", Quantity: " + this.quantity + " - " + this.date;
    }


    //Getters and Setters

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getCustomerSSN() {
        return customerSSN;
    }

    public void setCustomerSSN(long customerSSN) {
        this.customerSSN = customerSSN;
    }

    public long getCustomerAccountNumber() {
        return customerAccountNumber;
    }

    public void setCustomerAccountNumber(long customerAccountNumber) {
        this.customerAccountNumber = customerAccountNumber;
    }
}
