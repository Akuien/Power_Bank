{
    private CustomerLogInMenu customerLogInMenu;
    private EmployeeLogInMenu employeeLogInMenu;

    public MainMenu() {
        this.customerLogInMenu = new CustomerLogInMenu();
        this.employeeLogInMenu = new EmployeeLogInMenu();
        }

        public void printMenu() {

        System.out.println("Main Menu options menu:" + System.lineSeparator() +
              "0. Quit" + EOL +
              "1. Customer Login Menu" + EOL +
              "2. Employee Login Menu");
        }
        public void menu(int option){
        do {
            switch (option) {

                case 0:
        //This line closes the whole system, and we take a 0 as a parameter because it has executed in a successful way
        //If the parameter had been 1 or -1 it meant it was exited in an unsuccessful way.

        }
        }
        }


        }