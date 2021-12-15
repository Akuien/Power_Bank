package controllers;

import usecases.*;

public class CustomerMenu {
    private LogInCustomer logInCustomer;
    private LogOutCustomer logOutCustomer;
    private TransferMoneyToAnotherAccount transferMoneyToAnotherAccount;
    private DepositMoney depositMoney;
    private WithdrawMoney withdrawMoney;
    public static final String EOL = System.lineSeparator();

    public LogOutCustomer getLogOutCustomer() {
        return logOutCustomer;
    }

    public CustomerMenu(){
        this.logInCustomer = new LogInCustomer();
        this.logOutCustomer = new LogOutCustomer();
        this.transferMoneyToAnotherAccount = new TransferMoneyToAnotherAccount();
        this.depositMoney = new DepositMoney();
        this.withdrawMoney = new WithdrawMoney();
    }

    public static void CustomerMenu(){

        int option = UserInput.inputInt("Customer menu:" + System.lineSeparator()+
                "0. Log Out." + EOL +
                "1. Transfer Money." + EOL +
                "2. Deposit Money." + EOL +
                "3. Withdraw Money." + EOL +
                "4. Loan approximation." + EOL +
                "5. ShareHolder Menu" + EOL +
                "6. List of bank accounts " + EOL +
                "7. Look at transaction history" + EOL +
                "8. " + EOL +
                "Type an option number: ");

        while(option < 0 || option > 8){

            option = UserInput.inputInt("Invalid option");

        }switch(option){

            case 0 :
              try {
                  String accessToken = UserInput.inputString(" ");
                  logOutCustomer.execute(accessToken);

              }  catch (Exception exception){
                  System.out.println(exception.getMessage());
              }
                break;

            case 1 :
             try {
                 long originSSN = UserInput.inputLong("Enter sender's SSN: ");
                 long originAccountNumber = UserInput.inputLong(" Enter Sender's account Number: ");
                 long finalSSN = UserInput.inputLong(" Enter Receiver's SSN: ");
                 long finalAccountNumber = UserInput.inputLong(" Enter receiver's account Number: ");
                 double amount = UserInput.inputLong(" Enter amount: ");

                 transferMoneyToAnotherAccount.execute(originSSN, originAccountNumber, finalSSN,  finalAccountNumber,  amount);

             } catch (Exception exception){
                 System.out.println(exception.getMessage());
             }

                break;

            case 2 :
                try {
                    long SSN = UserInput.inputLong(" Enter SSN: ");
                    long accountNumber = UserInput.inputInt(" Enter account Number: ");
                    double amount = UserInput.inputLong(" Enter Amount: ");

                    depositMoney.execute(SSN, accountNumber, amount);

                } catch (Exception exception){
                    System.out.println(exception.getMessage());
            }

                break;

            case 3 :
                try {
                    long SSN = UserInput.inputLong(" Enter SSN: ");
                    long accountNumber = UserInput.inputLong(" Enter account Number: ");
                    double amount = UserInput.inputDouble(" Enter Amount: ");

                    withdrawMoney.execute(SSN, accountNumber, amount);

                } catch (Exception exception){
                    System.out.println(exception.getMessage());
                }
                break;

            case 4 : // No feature on this.
                break;

            case 5 : ShareholderMenu.ShareHolderMenu();
                break;

            case 6 : // No List.
                break;

            case 7 :
                // Undone
                break;

            case 8 :
            default: System.out.println("Please enter valid option");
                break;
        }

    }

}

