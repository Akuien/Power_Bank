package usecases;

import domain.entities.Portfolio;
import domain.entities.Stock;
import repositories.PortfolioRepository;

import java.util.ArrayList;

public class ObtainOwnedStocks {
    private PortfolioRepository portfolioRepository;

    public ObtainOwnedStocks(){
        this.portfolioRepository = new PortfolioRepository();
    }

    public ArrayList<Stock> execute(long customerSSN){
        Portfolio ownedPortfolio = portfolioRepository.getPortfolioBySSN(customerSSN);
        return ownedPortfolio.getStocks();
    }
}
