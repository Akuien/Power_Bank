package domain.entities;



public class Company {

    private String name;
    private double stockPrice;

    public Company (String name, double stockPrice){
        this.name = name;
        this.stockPrice = stockPrice;
    }

    public String toString(){
        return this.name + " has shares at " + this.stockPrice + " SEK.";
    }

    //Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }
}
