package repositories;

import data.PersistenceData;
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
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        //Then we get the date object values from the calendar object
        Date birthDateEmployee1 = calendar.getTime();

        calendar.set(Calendar.YEAR, 1973);
        calendar.set(Calendar.MONTH, Calendar.MARCH);
        calendar.set(Calendar.DAY_OF_MONTH, 9);

        Date birthDateEmployee2 = calendar.getTime();

        Employee employee1 = new Employee("Jeff", "Bloom", 860409123, "boom", "jeff.bloom@gmail.com", "07459734015", birthDateEmployee1);
        Employee employee2 = new Employee("Jack", "Black", 730309321, "black", "jack.black@gmail.com", "07470923784", birthDateEmployee2);

        ArrayList<Employee> temp = new ArrayList<>();

        temp.add(employee1);
        temp.add(employee2);

        assertEquals(temp.size(), employees.size());

        assertEquals(temp.get(0).toString(), employees.get(0).toString());

        assertEquals(temp.toString(), employees.toString());
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
    }
}