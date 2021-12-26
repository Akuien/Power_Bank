package data;

import domain.entities.Employee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

//Initial running test on Writing and Reading to/from json files

public class JsonTest {
    public static void main(String[] args) {

        //Testing for Employees
        PersistenceData data = new PersistenceData();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1988);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date dateRepresentation = cal.getTime();
        Employee employee1 = new Employee("Jeff", "Bloom", 990207123, "boom", "jeff.bloom@gmail.com", "07459734015", dateRepresentation);
        Employee employee2 = new Employee("Jack", "Black", 730309321, "black", "jack.black@gmail.com", "07470923784", dateRepresentation);
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        data.setEmployees(employees);

        employees = (ArrayList<Employee>) data.getEmployees();

        Employee test = new Employee("Jack", "Black", 730309321, "black", "jack.black@gmail.com", "07470923784", dateRepresentation);

        for (Employee employee : employees){
            System.out.println(employee.getAge());
        }

        System.out.println(employees);

        System.out.println(test);

        //Testing for Customers
    }
}