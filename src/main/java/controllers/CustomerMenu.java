package controllers;

import.domain.entities.BankAccount;
import domain.entities.Customer;
import domain.entities.Transaction;
import repositories.BankAccountRepository;
import usecases.*;

import java.util.ArrayList;

public class CustomerMenu {
    private LogOutCustomer logOutCustomer; // will put all private attributes here

    public static final String EOL = System.lineSeparator();

    public CustomerMenu(){
        this.logOutCustomer = new LogOutCustomer // constructor parameters
    }

    public void printMenu() {

        System.out.println("Customer menu:" + System.lineSeparator() +
                "0. Log Out." + EOL +
                "1. Transfer Money." + EOL +
                // will put rest of menu options here
                )
    }

    public void menu(int option, Customer customer){
        do {
            switch(option){ //Switch-case menu

                case 0:
                    try {
                        String accessToken = customer.getAccessToken();
                        logOutCustomer.execute(accessToken);
                    }
                    catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;

                case 1: // up to case 8.

                    }
            }
        }while(option != 0);
    }

}