package controllers;

import static controllers.CustomerMenu.EOL;

public class ManagerMenu {

        public void printMenu() {

            System.out.println("Employee menu:" + System.lineSeparator() +
                    "0. Log Out." + EOL +
                    "1. List customers" + EOL +
                    "2. List customer's bank account(s)" + EOL +
                    "3. List mortgage applications." + EOL +
                    "4. List bank account applications." + EOL +
                    "5. List employees." + EOL +
                    "6. Change status mortgage." + EOL +
                    "7. Change status bank account." + EOL +
                    "8. Promote employee." + EOL +
                    "9. Create employee account");
        }


}








