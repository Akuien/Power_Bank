package data;

import domain.constants.BankAccountStatus;
import domain.entities.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//Class used to populate the json files for each entity
//And have an initial data set to work with
//in the first runs of the app

public class DataSetInitializer {
    public static void main(String[] args) {
        PersistenceData data = new PersistenceData();

        //Create 2 employees and add them to the arraylist
        //which will later be stored in the employee.json file

        //We use a calendar object,
        // due to the date constructor which is deprecated
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1986);
        cal.set(Calendar.MONTH, Calendar.APRIL);
        cal.set(Calendar.DAY_OF_MONTH, 9);

        //Then we get the date object values from the calendar object
        Date birthDateEmployee1 = cal.getTime();

        cal.set(Calendar.YEAR, 1973);
        cal.set(Calendar.MONTH, Calendar.MARCH);
        cal.set(Calendar.DAY_OF_MONTH, 9);

        Date birthDateEmployee2 = cal.getTime();

        //We create 2 employees,
        // and then add them to the employee arraylist
        Employee employee1 = new Employee("Jeff", "Bloom", 860409123, "boom", "jeff.bloom@gmail.com", "07459734015", birthDateEmployee1);
        Employee manager1 = new Manager("Jack", "Black", 730309321, "black", "jack.black@gmail.com", "07470923784", birthDateEmployee2);
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(manager1);

        //Populate the employee.json file with an initial data set

        data.setEmployees(employees);

        employees = data.getEmployees();

        Employee test = employees.get(1);

        //Create 2 customer and add them to the arraylist
        //which will later be stored in the customer.json file


        //We use a calendar object,
        // due to the date constructor which is deprecated
        cal.set(Calendar.YEAR, 1997);
        cal.set(Calendar.MONTH, Calendar.FEBRUARY);
        cal.set(Calendar.DAY_OF_MONTH, 13);

        //Then we get the date object values from the calendar object
        Date birthDateCustomer1 = cal.getTime();

        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JULY);
        cal.set(Calendar.DAY_OF_MONTH, 2);

        Date birthDateCustomer2 = cal.getTime();

        //We create 2 employees,
        // and then add them to the employee arraylist
        Customer customer1 = new Customer("Elizabeth", "Banks", 970213742, "harbor", "elizabeth.banks@gmail.com", "07410036992", birthDateCustomer1);
        Customer customer2 = new Customer("Michaela", "Snow", 880702813, "castle", "michaela.snow@gmail.com", "07429909567", birthDateCustomer2);
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        //Populate the customers.json file with an initial data set

        data.setCustomers(customers);

        //Create 2 bankAccounts and add them to the arraylist
        //which will later be stored in the bankAccounts.json file


        //We create 2 bankAccounts,
        // and then add them to the bankAccounts arraylist
        BankAccount account1 = new BankAccount(10000, "Savings", 481046478,970213742);
        account1.setStatus(BankAccountStatus.approved);
        BankAccount account2 = new BankAccount(0, "Car", 740375897, 970213742);
        BankAccount account3 = new BankAccount(5500, "Holidays", 522386136, 880702813);
        account3.setStatus(BankAccountStatus.approved);
        ArrayList<BankAccount> bankAccounts = new ArrayList<>();
        bankAccounts.add(account1);
        bankAccounts.add(account2);
        bankAccounts.add(account3);

        //Populate the bankAccounts.json file with an initial data set

        data.setBankAccounts(bankAccounts);

        //Create 2 transactions and add them to the arraylist
        //which will later be stored in the transactions.json file

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Date transactionDate = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());

        //We create 2 transactions,
        // and then add them to the transactions arraylist
        Transaction transaction1 = new Transaction(481046478, 481046478, 10000, "credit", transactionDate);
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);

        //Populate the bankAccounts.json file with an initial data set

        data.setTransactions(transactions);

        //Create 2 companies to use them for stock trading and add them to the arraylist
        //which will later be stored in the companies.json file

        //We create 2 companies,
        // and then add them to the companies arraylist
        Company company1 = new Company("Microsoft", 100.0);
        Company company2 = new Company("Apple", 194.0);
        Company company3 = new Company("Amazon", 3000);
        Company company4 = new Company("Google", 3000.0);
        ArrayList<Company> companies = new ArrayList<>();
        companies.add(company1);
        companies.add(company2);
        companies.add(company3);
        companies.add(company4);

        //Populate the companies.json file with an initial data set

        data.setCompanies(companies);

        System.out.println("Data loaded successfully");
    }
}