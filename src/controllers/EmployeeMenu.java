package controllers;

import static controllers.CustomerMenu.EOL;

public class EmployeeMenu {

    public void EmployeeMenu(){

        int option = UserInput.inputInt("Employee menu:" + System.lineSeparator()+
                "0. Log Out." + EOL +
                "1. Register Customer." + EOL +
                "2. Validate customer." + EOL +
                "3. Validate Customer bank account." + EOL +
                "4. Approve mortgage  ." + EOL +
                "5. Customer Menu" + EOL +
                "6. Validate Email  " + EOL +
                "7. Look at transaction history" + EOL +
                "8. " + EOL +
                "Type an option number: ");

        while(option < 0 || option > 8){

            option = UserInput.inputInt("Invalid option");

        }switch(option){

        }

}



}
