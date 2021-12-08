package repositories;

import domain.entities.Portfolio;

import java.util.ArrayList;

public class PortfolioRepository extends AbstractRepository{

    public Portfolio getPortfolioBySSN(long SSN) {
        ArrayList<Portfolio> portfolios = PortfolioRepository.persistenceData.getPortfolios();
        Portfolio portfolio = null;
        for (Portfolio currentPortfolio : portfolios){
            if (currentPortfolio.getCustomerSSN() == SSN){
                portfolio = currentPortfolio;
            }
        }
        return portfolio;
    }


    public void createPortfolio(Portfolio portfolio) {
        ArrayList<Portfolio> portfolios = PortfolioRepository.persistenceData.getPortfolios();
        portfolios.add(portfolio);
        PortfolioRepository.persistenceData.setPortfolios(portfolios);
    }


    public void updatePortfolio(Portfolio portfolio) {
        ArrayList<Portfolio> portfolios = PortfolioRepository.persistenceData.getPortfolios();
        for (Portfolio currentPortfolio : portfolios){
            if (currentPortfolio.equals(portfolio)){
                currentPortfolio.setStocks(portfolio.getStocks());
            }
        }
        PortfolioRepository.persistenceData.setPortfolios(portfolios);
    }
}
