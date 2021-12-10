package repositories;

import domain.entities.Portfolio;
import domain.entities.Stock;

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

    public ArrayList<Stock> getStocksByCompanyName(long customerSSN, String companyName){
        Portfolio ownedPortfolio = getPortfolioBySSN(customerSSN);
        ArrayList<Stock> ownedStocks = ownedPortfolio.getStocks();
        //This arrayList will store the amount of stocks that a shareholder has from a specified business
        ArrayList<Stock> ownedCompanyStocks = new ArrayList<>();
        for (Stock currentStock : ownedStocks) {
            if (currentStock.getCompany().equals(companyName)){
                ownedCompanyStocks.add(currentStock);
            }
        }
        return ownedCompanyStocks;
    }

}
