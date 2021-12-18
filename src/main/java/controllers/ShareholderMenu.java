package controllers;
import data.PersistenceData;
import domain.entities.Portfolio;
import domain.entities.Stock;
import repositories.PortfolioRepository;
import usecases.*;

import usecases.BuyShares;
import usecases.SellShares;

public class ShareholderMenu {

    public static final String EOL = System.lineSeparator();
    private BuyShares buyShares;
    private SellShares sellShares;
    private MainMenu mainMenu;
    private PersistenceData persistenceData;
    private PortfolioRepository portfolioRepository;


    public ShareholderMenu(){
        this.buyShares = new BuyShares();
        this.sellShares = new SellShares();
    }

    public void printShareHolderMenu () {


        int option = UserInput.inputInt("Shareholder menu:" + EOL +
                "0. Return." + EOL +
                "1. Browse Company." + EOL +
                "2. Buy Shares." + EOL +
                "3. Sell Shares. " + EOL +
                "4. See Owned Stocks." + EOL +
                "Type an option number: ");

    }
    public void shareholderMenu(int option){

        do {
            switch (option) {

                case 0:
                    mainMenu.Menu();
                    break;
                case 1:
                    persistenceData.getCompanies();
                    break;
                case 2:
                    try {
                        String companyName = UserInput.inputString("Enter Company Stock you would like to purchase");
                        int quantity = UserInput.inputInt("Enter amount of shares");
                        long customerSSN = UserInput.inputLong("Enter your SSN ");
                        long customerAccountNumber = UserInput.inputLong("Enter from which account you will purchase the stock with ");

                        buyShares.execute(companyName, quantity, customerSSN, customerAccountNumber);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 3:
                    try {
                        String companyName = UserInput.inputString("Enter Company Stock you would like to Sell");
                        int quantity = UserInput.inputInt("Enter amount of shares you wish to sell");
                        long customerSSN = UserInput.inputLong("Enter your SSN ");
                        long customerAccountNumber = UserInput.inputLong("Enter from which account you wish to deposit your money");
                        //Stock stock = UserInput.input("Enter stock"); !!! How do i fix this?
                        sellShares.execute(companyName, quantity, customerSSN, customerAccountNumber, stock);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 4:
                    long customerSSN = UserInput.inputLong("Enter your SSN ");
                    portfolioRepository.getPortfolioBySSN(customerSSN).getStocks();
                    break;


                default:
                    System.out.println("Please enter valid option");
                    break;

            }
        }while(option < 0 || option > 8);

    }

}
