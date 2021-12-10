package domain.exceptions;

public class PortfolioDoesNotExistException extends Exception{
    public PortfolioDoesNotExistException(){
        super("The portfolio requested does not exist.");
    }
}
