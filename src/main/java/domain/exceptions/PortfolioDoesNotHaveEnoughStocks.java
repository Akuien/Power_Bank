package domain.exceptions;

public class PortfolioDoesNotHaveEnoughStocks extends Exception{
    public PortfolioDoesNotHaveEnoughStocks(){
        super("The stocks demanded are more than the current stored ones.");
    }
}
