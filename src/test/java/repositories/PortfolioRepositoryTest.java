package repositories;

import domain.entities.Company;
import domain.entities.Portfolio;
import domain.entities.Stock;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PortfolioRepositoryTest {

    private PortfolioRepository portfolioRepository;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        portfolioRepository = new PortfolioRepository();
    }


    @Test
    void getPortfolioBySSN() {
        //Create 2 portfolios for each shareholder and add them to the arraylist
        //which will later be stored in the portfolios.json file

        //We create 2 portfolios,
        // and then add them to the portfolios arraylist
        Portfolio portfolio1 = new Portfolio(941205153);
        Portfolio portfolio2 = new Portfolio(790504871);

        portfolioRepository.createPortfolio(portfolio1);
        portfolioRepository.createPortfolio(portfolio2);

        assertEquals(portfolio1.toString(), portfolioRepository.getPortfolioBySSN(941205153).toString());

        assertEquals(portfolio2.toString(), portfolioRepository.getPortfolioBySSN(790504871).toString());
    }

    @Test
    void createPortfolio() {
        Portfolio portfolio1 = new Portfolio(941205153);

        portfolioRepository.createPortfolio(portfolio1);

        assertEquals(portfolio1.toString(), portfolioRepository.getPortfolioBySSN(941205153).toString());
    }

    @Test
    void updatePortfolio() {
        Portfolio test1 = new Portfolio(941205153);

        portfolioRepository.createPortfolio(test1);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 27);

        Date stockDate = cal.getTime();

        Company company1 = new Company("Tesla", 30.45);


        Stock stock1 = new Stock(30.45, stockDate, "Tesla", 11, 941205153, 481046478);

        ArrayList<Stock> stocks =new ArrayList<>();

        stocks.add(stock1);

        Portfolio test2 = new Portfolio(941205153);

        test2.setStocks(stocks);

        portfolioRepository.updatePortfolio(test2);

        assertEquals(test2.toString(), portfolioRepository.getPortfolioBySSN(790504871).toString());
    }

    @Test
    void getStocksByCompanyName() {
        Portfolio test1 = new Portfolio(941205153);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 27);

        Date stockDate = cal.getTime();

        Company company1 = new Company("Tesla", 30.45);


        Stock stock1 = new Stock(30.45, stockDate, "Tesla", 11, 941205153, 481046478);

        ArrayList<Stock> stocks =new ArrayList<>();

        stocks.add(stock1);

        test1.setStocks(stocks);

        portfolioRepository.createPortfolio(test1);

        assertEquals(test1.getStocks().toString(), portfolioRepository.getStocksByCompanyName(941205153, "Tesla").toString());
    }
}