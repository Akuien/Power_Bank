package data;

import domain.entities.Customer;
import domain.entities.Employee;
import domain.entities.Manager;
import domain.entities.Shareholder;

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
        Employee employee2 = new Employee("Jack", "Black", 730309321, "black", "jack.black@gmail.com", "07470923784", birthDateEmployee2);
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        //Populate the employee.json file with an initial data set

        data.setEmployees(employees);

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

        //Populate the employee.json file with an initial data set

        data.setCustomers(customers);


        //Create 2 shareholders and add them to the arraylist
        //which will later be stored in the shareholders.json file


        //We use a calendar object,
        // due to the date constructor which is deprecated
        cal.set(Calendar.YEAR, 1994);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 5);

        //Then we get the date object values from the calendar object
        Date birthDateShareholder1 = cal.getTime();

        cal.set(Calendar.YEAR, 1979);
        cal.set(Calendar.MONTH, Calendar.MAY);
        cal.set(Calendar.DAY_OF_MONTH, 4);

        Date birthDateShareholder2 = cal.getTime();

        //We create 2 shareholders,
        // and then add them to the shareholder arraylist
        Shareholder shareholder1 = new Shareholder("Ellen", "Henderson", 941205153, "clouds", "ellen.henderson@gmail.com", "07467893120", birthDateShareholder1);
        Shareholder shareholder2 = new Shareholder("Paul", "Southgate", 790504871, "koala", "paul.southgate@gmail.com", "07489023492", birthDateShareholder2);
        ArrayList<Shareholder> shareholders = new ArrayList<>();
        shareholders.add(shareholder1);
        shareholders.add(shareholder2);

        //Populate the shareholder.json file with an initial data set

        data.setShareHolders(shareholders);


        //Create 2 managers and add them to the arraylist
        //which will later be stored in the managers.json file


        //We use a calendar object,
        // due to the date constructor which is deprecated
        cal.set(Calendar.YEAR, 1977);
        cal.set(Calendar.MONTH, Calendar.NOVEMBER);
        cal.set(Calendar.DAY_OF_MONTH, 23);

        //Then we get the date object values from the calendar object
        Date birthDateManager1 = cal.getTime();

        cal.set(Calendar.YEAR, 1990);
        cal.set(Calendar.MONTH, Calendar.AUGUST);
        cal.set(Calendar.DAY_OF_MONTH, 17);

        Date birthDateManager2 = cal.getTime();

        //We create 2 managers,
        // and then add them to the manager arraylist
        Manager manager1 = new Manager("Amber", "Lockhart", 941205401, "victory", "amber.lockhart@gmail.com", "07417896331", birthDateManager1);
        Manager manager2 = new Manager("Madison", "Fletcher", 790504297, "loophole", "madison.fletcher@gmail.com", "07405612934", birthDateManager2);
        ArrayList<Manager> managers = new ArrayList<>();
        managers.add(manager1);
        managers.add(manager2);

        //Populate the managers.json file with an initial data set

        data.setManagers(managers);

    }
}