package controllers;

public class CustomerMenu {

    public static void CustomerMenu(){

        int option = UserInput.inputInt("Customer menu:" + System.lineSeparator()+
                "0. Log Out." + System.lineSeparator()+
                "1. Transfer Money." + System.lineSeparator()+
                "2. Deposit Money." + System.lineSeparator()+
                "3. Withdraw Money." + System.lineSeparator()+
                "4. Loan approximation." + System.lineSeparator()+
                "5. Buy Shares." + System.lineSeparator()+
                "6. Sell Shares. " + System.lineSeparator()+
                "7. Browse Stocks" + System.lineSeparator()+
                "8. " + System.lineSeparator()+
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

