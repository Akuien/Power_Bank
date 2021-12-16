package domain.entities;



public class Company {

    private String name;
    private double stockPrice;

    public Company (String name, double stockPrice){
        this.name = name;
        this.stockPrice = stockPrice;
    }

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
