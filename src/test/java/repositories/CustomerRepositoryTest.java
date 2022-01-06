package repositories;

import domain.entities.Customer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CustomerRepositoryTest {

    private CustomerRepository customerRepository;
    private Calendar calendar;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        customerRepository = new CustomerRepository();

        calendar = Calendar.getInstance();

    }

    @org.junit.jupiter.api.Test
    void getAll() {
        //Create 2 customers and add them to the arraylist
        //which will later be stored in the customer.json file


        //We use a calendar object,
        // due to the date constructor which is deprecated

        calendar.set(Calendar.YEAR, 1997);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 15);

        //Then we get the date object values from the calendar object
        Date birthDateCustomer1 = calendar.getTime();

        calendar.set(Calendar.YEAR, 1988);
        calendar.set(Calendar.MONTH, Calendar.JULY);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        Date birthDateCustomer2 = calendar.getTime();

        //We create 2 employees,
        // and then add them to the employee arraylist
        Customer customer1 = new Customer("Hannah", "Franklin", 970215742, "harbor", "hannah.franlin@gmail.com", "07410036992", birthDateCustomer1);
        Customer customer2 = new Customer("Kate", "Barnes", 880709813, "castle", "kate.barnes@gmail.com", "07429909567", birthDateCustomer2);

        ArrayList<Customer> temp = new ArrayList<>();

        temp.add(customer1);
        temp.add(customer2);

        customerRepository.createProfile(customer1);
        customerRepository.createProfile(customer2);

        ArrayList<Customer> temp2 = customerRepository.getAll();

        assertEquals(temp.get(temp.size()-1).toString(), temp2.get(temp2.size()-1).toString());

        assertEquals(temp.get(temp.size()-2).toString(), temp2.get(temp2.size()-2).toString());

        //Remove the last 2 added customers
        //used for the test
        temp2.remove(temp2.size()-1);
        temp2.remove(temp2.size()-1);
        CustomerRepository.persistenceData.setCustomers(temp2);
    }

    @org.junit.jupiter.api.Test
    void getBySSN() {
        //We use a calendar object,
        // due to the date constructor which is deprecated

        calendar.set(Calendar.YEAR, 1997);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 13);

        //Then we get the date object values from the calendar object
        Date birthDateCustomer = calendar.getTime();

        Customer test = new Customer("Jane", "Doe", 770707777, "password", "jane.doe@gmail.com", "07112233445", birthDateCustomer);

        customerRepository.createProfile(test);

        assertEquals(test.toString(), customerRepository.getBySSN(770707777).toString());

        ArrayList<Customer> temp = customerRepository.getAll();

        //Remove the last added customer
        //used for the test
        temp.remove(temp.size()-1);
        CustomerRepository.persistenceData.setCustomers(temp);

    }

    @org.junit.jupiter.api.Test
    void getByAccessToken() {
        //We use a calendar object,
        // due to the date constructor which is deprecated

        calendar.set(Calendar.YEAR, 1997);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 13);

        //Then we get the date object values from the calendar object
        Date birthDateCustomer = calendar.getTime();

        Customer test = new Customer("Jane", "Doe", 770707777, "password", "jane.doe@gmail.com", "07112233445", birthDateCustomer);
        test.setAccessToken("987654321");

        customerRepository.createProfile(test);

        assertEquals(test.toString(), customerRepository.getByAccessToken("987654321").toString());

        ArrayList<Customer> temp = customerRepository.getAll();

        //Remove the last added customer
        //used for the test
        temp.remove(temp.size()-1);
        CustomerRepository.persistenceData.setCustomers(temp);

    }

    @org.junit.jupiter.api.Test
    void getByEmail() {

        //We use a calendar object,
        // due to the date constructor which is deprecated

        calendar.set(Calendar.YEAR, 1997);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 13);

        //Then we get the date object values from the calendar object
        Date birthDateCustomer = calendar.getTime();

        Customer test = new Customer("Jane", "Doe", 770707777, "password", "jane.doe@gmail.com", "07112233445", birthDateCustomer);
        test.setAccessToken("987654321");

        customerRepository.createProfile(test);

        assertEquals(test.toString(), customerRepository.getByEmail("jane.doe@gmail.com").toString());

        ArrayList<Customer> temp = customerRepository.getAll();

        //Remove the last added customer
        //used for the test
        temp.remove(temp.size()-1);
        CustomerRepository.persistenceData.setCustomers(temp);

    }

    @org.junit.jupiter.api.Test
    void createProfile() {

        //We use a calendar object,
        // due to the date constructor which is deprecated

        calendar.set(Calendar.YEAR, 1997);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 13);

        //Then we get the date object values from the calendar object
        Date birthDateCustomer = calendar.getTime();

        Customer test = new Customer("Jane", "Doe", 770707777, "password", "jane.doe@gmail.com", "07112233445", birthDateCustomer);

        customerRepository.createProfile(test);

        assertEquals(test.toString(), customerRepository.getBySSN(770707777).toString());

        ArrayList<Customer> temp = customerRepository.getAll();

        //Remove the last added customer
        //used for the test
        temp.remove(temp.size()-1);
        CustomerRepository.persistenceData.setCustomers(temp);
    }

    @org.junit.jupiter.api.Test
    void updateProfile() {

        //We use a calendar object,
        // due to the date constructor which is deprecated

        calendar.set(Calendar.YEAR, 1997);
        calendar.set(Calendar.MONTH, Calendar.FEBRUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 13);

        //Then we get the date object values from the calendar object
        Date birthDateCustomer = calendar.getTime();

        Customer test = new Customer("Jane", "Doe", 770707777, "password", "jane.doe@gmail.com", "07112233445", birthDateCustomer);

        customerRepository.createProfile(test);

        Customer test2 = new Customer("Jane", "Davies", 770707777, "saferPassword", "jane.davis@gmail.com", "07112233454", birthDateCustomer);

        customerRepository.updateProfile(test2);

        assertEquals(test2.toString(), customerRepository.getBySSN(770707777).toString());

        ArrayList<Customer> temp = customerRepository.getAll();

        //Remove the last added customer
        //used for the test
        temp.remove(temp.size()-1);
        CustomerRepository.persistenceData.setCustomers(temp);
    }
}