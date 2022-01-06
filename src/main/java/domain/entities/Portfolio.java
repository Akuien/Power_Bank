package domain.entities;

import java.util.ArrayList;

public class Portfolio {

    private ArrayList<Stock> stocks;
    private long customerSSN;

    public Portfolio (long customerSSN){
        stocks = new ArrayList<>();
        this.customerSSN = customerSSN;
    }

    public boolean equals(Portfolio portfolio){
        return this.getCustomerSSN() == portfolio.getCustomerSSN();
    }
    //Checks if portfolio is equal if customerSSN is equal


    public void addStock(Stock stock){
        stocks.add(stock);
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(ArrayList<Stock> stocks) {
        this.stocks = stocks;
    }

    public long getCustomerSSN() {
        return customerSSN;
    }

    public void setCustomerSSN(long customerSSN) {
        this.customerSSN = customerSSN;
    }

    public String toString(){
        return stocks.toString();
    }
}
