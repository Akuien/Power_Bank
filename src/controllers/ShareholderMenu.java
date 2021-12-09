package controllers;

public class ShareholderMenu {

    public static void ShareHolderMenu (){


        int option = UserInput.inputInt("Shareholder menu:" + System.lineSeparator()+
                "0. Return." + System.lineSeparator()+
                "1. Browse Company." + System.lineSeparator()+
                "2. Buy Shares." + System.lineSeparator()+
                "3. Sell Shares. " + System.lineSeparator()+
                "4. See Owned Stocks." + System.lineSeparator()+
                "Type an option number: ");

        while(option < 0 || option > 8){

            option = UserInput.inputInt("Invalid option");

        }switch(option){

            case 0 : ;
                break;
            case 1 : ;
                break;
            case 2 : ;
                break;
            case 3 : ;
                break;
            case 4 : ;
                break;
            case 5 : ;
                break;
            case 6 : ;
                break;
            case 7 : ;
                break;
            case 8 : ;
            default: System.out.println("Please enter valid option");
                break;
        }

    }

}
