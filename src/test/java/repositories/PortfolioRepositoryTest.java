package repositories;

import domain.entities.Company;
import domain.entities.Portfolio;
import domain.entities.Stock;

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


    @org.junit.jupiter.api.Test
    void getPortfolioBySSN() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 27);

        Date stockDate = cal.getTime();

        Company company1 = new Company("Systematic", 10.45);
        Company company2 = new Company("Linak", 9.25);

        Stock stock1 = new Stock(stockDate, "Systematic", 11, 970215742, 991046478);
        Stock stock2 = new Stock(stockDate, "Linak", 3, 880709813, 880375897);


        ArrayList<Stock> stocks =new ArrayList<>();

        stocks.add(stock1);

        //Create 2 portfolios for each shareholder and add them to the arraylist
        //which will later be stored in the portfolios.json file

        //We create 2 portfolios,
        // and then add them to the portfolios arraylist
        Portfolio portfolio1 = new Portfolio(970215742);
        Portfolio portfolio2 = new Portfolio(880709813);

        portfolio1.setStocks(stocks);
        stocks.set(0, stock2);
        portfolio2.setStocks(stocks);

        portfolioRepository.createPortfolio(portfolio1);
        portfolioRepository.createPortfolio(portfolio2);

        assertEquals(portfolio1.toString(), portfolioRepository.getPortfolioBySSN(970215742).toString());

        assertEquals(portfolio2.toString(), portfolioRepository.getPortfolioBySSN(880709813).toString());

        ArrayList<Portfolio> temp = PortfolioRepository.persistenceData.getPortfolios();

        //Remove the last 2 added portfolios
        //used for the test
        temp.remove(temp.size()-1);
        temp.remove(temp.size()-1);
        PortfolioRepository.persistenceData.setPortfolios(temp);
    }

    @org.junit.jupiter.api.Test
    void createPortfolio() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 27);

        Date stockDate = cal.getTime();

        Company company1 = new Company("Systematic", 10.45);


        Stock stock1 = new Stock(stockDate, "Systematic", 11, 970215742, 991046478);

        ArrayList<Stock> stocks =new ArrayList<>();

        stocks.add(stock1);

        Portfolio portfolio1 = new Portfolio(970215742);

        portfolio1.setStocks(stocks);

        portfolioRepository.createPortfolio(portfolio1);

        assertEquals(portfolio1.toString(), portfolioRepository.getPortfolioBySSN(970215742).toString());

        ArrayList<Portfolio> temp = PortfolioRepository.persistenceData.getPortfolios();

        //Remove the last added portfolio
        //used for the test
        temp.remove(temp.size()-1);
        PortfolioRepository.persistenceData.setPortfolios(temp);
    }

    @org.junit.jupiter.api.Test
    void updatePortfolio() {
        Portfolio test1 = new Portfolio(970215742);

        portfolioRepository.createPortfolio(test1);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 27);

        Date stockDate = cal.getTime();

        Company company1 = new Company("Systematic", 10.45);


        Stock stock1 = new Stock(stockDate, "Systematic", 11, 970215742, 991046478);
        Stock stock2 = new Stock(stockDate, "Systematic", 15, 970215742, 991046478);


        ArrayList<Stock> stocks =new ArrayList<>();

        stocks.add(stock1);

        test1.setStocks(stocks);

        Portfolio test2 = new Portfolio(970215742);

        stocks.set(0, stock2);
        test2.setStocks(stocks);

        portfolioRepository.updatePortfolio(test2);

        assertEquals(test2.toString(), portfolioRepository.getPortfolioBySSN(970215742).toString());

        ArrayList<Portfolio> temp = PortfolioRepository.persistenceData.getPortfolios();

        //Remove the last added portfolio
        //used for the test
        temp.remove(temp.size()-1);
        PortfolioRepository.persistenceData.setPortfolios(temp);
    }

    @org.junit.jupiter.api.Test
    void getStocksByCompanyName() {
        Portfolio test1 = new Portfolio(970215742);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 2021);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 27);

        Date stockDate = cal.getTime();

        Company company1 = new Company("Systematic", 10.45);


        Stock stock1 = new Stock(stockDate, "Systematic", 11, 970215742, 991046478);

        ArrayList<Stock> stocks =new ArrayList<>();

        stocks.add(stock1);

        test1.setStocks(stocks);

        portfolioRepository.createPortfolio(test1);

        assertEquals(test1.getStocks().toString(), portfolioRepository.getStocksByCompanyName(970215742, "Systematic").toString());

        ArrayList<Portfolio> temp = PortfolioRepository.persistenceData.getPortfolios();

        //Remove the last added portfolio
        //used for the test
        temp.remove(temp.size()-1);
        PortfolioRepository.persistenceData.setPortfolios(temp);
    }
}