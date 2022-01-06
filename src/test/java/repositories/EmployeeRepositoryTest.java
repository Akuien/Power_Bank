package repositories;

import data.PersistenceData;
import domain.entities.Customer;
import domain.entities.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest {

    private PersistenceData data;
    private EmployeeRepository employeeRepository;
    private Calendar calendar;
    private ArrayList<Employee> employees;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        data = new PersistenceData();

        employeeRepository = new EmployeeRepository();

        calendar = Calendar.getInstance();

        employees = employeeRepository.getAll();

    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        //Create 2 employees and add them to the arraylist
        //which will later be stored in the employee.json file

        //We use a calendar object,
        // due to the date constructor which is deprecated
        calendar.set(Calendar.YEAR, 1986);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.DAY_OF_MONTH, 3);

        //Then we get the date object values from the calendar object
        Date birthDateEmployee1 = calendar.getTime();

        calendar.set(Calendar.YEAR, 1973);
        calendar.set(Calendar.MONTH, Calendar.MARCH);
        calendar.set(Calendar.DAY_OF_MONTH, 2);

        Date birthDateEmployee2 = calendar.getTime();

        Employee employee1 = new Employee("Aleksey", "Zorin", 860403123, "boom", "aleksey.zorin@gmail.com", "07459734015", birthDateEmployee1);
        Employee employee2 = new Employee("Hulk", "Hogan", 730302321, "black", "hulk.hogan@gmail.com", "07470923784", birthDateEmployee2);

        ArrayList<Employee> temp = new ArrayList<>();

        temp.add(employee1);
        temp.add(employee2);

        employeeRepository.createProfile(employee1);
        employeeRepository.createProfile(employee2);

        ArrayList<Employee> temp2 = employeeRepository.getAll();

        assertEquals(temp.get(temp.size()-1).toString(), temp2.get(temp2.size()-1).toString());

        assertEquals(temp.get(temp.size()-2).toString(), temp2.get(temp2.size()-2).toString());

        //Remove the last 2 added employees
        //used for the test
        temp2.remove(temp2.size()-1);
        temp2.remove(temp2.size()-1);
        EmployeeRepository.persistenceData.setEmployees(temp2);
    }

    @Test
    void getBySSN() {

        //We use a calendar object,
        // due to the date constructor which is deprecated
        calendar.set(Calendar.YEAR, 1986);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        //Then we get the date object values from the calendar object
        Date birthDateEmployee = calendar.getTime();

        Employee test = new Employee("John", "Doe", 990909999, "password", "john.doe@gmail.com", "07123456789", birthDateEmployee);

        employeeRepository.createProfile(test);

        assertEquals(test.toString(), employeeRepository.getBySSN(990909999).toString());

        ArrayList<Employee> temp = employeeRepository.getAll();

        //Remove the last added employee
        //used for the test
        temp.remove(temp.size()-1);
        EmployeeRepository.persistenceData.setEmployees(temp);
    }

    @Test
    void getByAccessToken() {

        //We use a calendar object,
        // due to the date constructor which is deprecated
        calendar.set(Calendar.YEAR, 1986);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        //Then we get the date object values from the calendar object
        Date birthDateEmployee = calendar.getTime();

        Employee test = new Employee("John", "Doe", 990909999, "password", "john.doe@gmail.com", "07123456789", birthDateEmployee);
        test.setAccessToken("123456789");

        employeeRepository.createProfile(test);

        assertEquals(test.toString(), employeeRepository.getByAccessToken("123456789").toString());

        ArrayList<Employee> temp = employeeRepository.getAll();

        //Remove the last added employee
        //used for the test
        temp.remove(temp.size()-1);
        EmployeeRepository.persistenceData.setEmployees(temp);
    }

    @Test
    void getByEmail() {

        //We use a calendar object,
        // due to the date constructor which is deprecated
        calendar.set(Calendar.YEAR, 1986);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        //Then we get the date object values from the calendar object
        Date birthDateEmployee = calendar.getTime();

        Employee test = new Employee("John", "Doe", 990909999, "password", "john.doe@gmail.com", "07123456789", birthDateEmployee);

        employeeRepository.createProfile(test);

        assertEquals(test.toString(), employeeRepository.getByEmail("john.doe@gmail.com").toString());

        ArrayList<Employee> temp = employeeRepository.getAll();

        //Remove the last added employee
        //used for the test
        temp.remove(temp.size()-1);
        EmployeeRepository.persistenceData.setEmployees(temp);
    }

    @Test
    void createProfile() {

        //We use a calendar object,
        // due to the date constructor which is deprecated
        calendar.set(Calendar.YEAR, 1986);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        //Then we get the date object values from the calendar object
        Date birthDateEmployee = calendar.getTime();

        Employee test = new Employee("John", "Doe", 990909999, "password", "john.doe@gmail.com", "07123456789", birthDateEmployee);

        employeeRepository.createProfile(test);

        assertEquals(test.toString(), employeeRepository.getBySSN(990909999).toString());

        ArrayList<Employee> temp = employeeRepository.getAll();

        //Remove the last added employee
        //used for the test
        temp.remove(temp.size()-1);
        EmployeeRepository.persistenceData.setEmployees(temp);
    }

    @Test
    void updateProfile() {

        //We use a calendar object,
        // due to the date constructor which is deprecated
        calendar.set(Calendar.YEAR, 1986);
        calendar.set(Calendar.MONTH, Calendar.APRIL);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        //Then we get the date object values from the calendar object
        Date birthDateEmployee = calendar.getTime();

        Employee test = new Employee("John", "Doe", 990909999, "password", "john.doe@gmail.com", "07123456789", birthDateEmployee);

        employeeRepository.createProfile(test);

        Employee test2 = new Employee("Jane", "Doe", 990909999, "strong password", "jane.doe@gmail.com", "07987654321", birthDateEmployee);

        employeeRepository.updateProfile(test2);

        assertEquals(test2.toString(), employeeRepository.getBySSN(990909999).toString());

        ArrayList<Employee> temp = employeeRepository.getAll();

        //Remove the last added employee
        //used for the test
        temp.remove(temp.size()-1);
        EmployeeRepository.persistenceData.setEmployees(temp);
    }
}