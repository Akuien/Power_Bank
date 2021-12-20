package data;

import domain.entities.*;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.Arrays;

public class PersistenceData {
    private CustomJsonIo file;

    private ArrayList<Employee> employees;
    private ArrayList<Customer> customers;
    private ArrayList<BankAccount> bankAccounts;
    private ArrayList<Transaction> transactions;
    private ArrayList<Portfolio> portfolios;
    private ArrayList<Company> companies;
    private ArrayList<Mortgage> mortgages;


    public PersistenceData(){
        file = new CustomJsonIo();

        employees = new ArrayList<>();
        customers = new ArrayList<>();
        bankAccounts = new ArrayList<>();
        transactions = new ArrayList<>();
        portfolios = new ArrayList<>();
        companies = new ArrayList<>(Arrays.asList(new Company("Apple", 194), new Company("Microsoft", 100), new Company("Amazon", 3000)));
        mortgages = new ArrayList<>();

        //Infinite amount of shares
    }

    public ArrayList<Employee> getEmployees() {
        Employee[] arr = new Employee[0];
        employees = (ArrayList<Employee>)(Object) file.readArrayFromJson("employees.json", arr);
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        ArrayList<Object> objects = (ArrayList<Object>)(Object) employees;
        file.clearFile("employees.json");
        file.writeArrayToJson("employees.json", objects);
        this.employees = employees;
    }

    public ArrayList<Customer> getCustomers() {
        Customer[] arr = new Customer[0];
        customers = (ArrayList<Customer>)(Object) file.readArrayFromJson("customers.json", arr);
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        ArrayList<Object> objects = (ArrayList<Object>)(Object) customers;
        file.clearFile("customer.json");
        file.writeArrayToJson("customers.json", objects);
        this.customers = customers;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        BankAccount[] arr = new BankAccount[0];
        bankAccounts = (ArrayList<BankAccount>)(Object) file.readArrayFromJson("bankAccounts.json", arr);
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        ArrayList<Object> objects = (ArrayList<Object>)(Object) bankAccounts;
        file.clearFile("bankAccounts.json");
        file.writeArrayToJson("bankAccounts.json", objects);
        this.bankAccounts = bankAccounts;
    }

    public ArrayList<Transaction> getTransactions() {
        Transaction[] arr = new Transaction[0];
        transactions = (ArrayList<Transaction>)(Object) file.readArrayFromJson("transactions.json", arr);
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        ArrayList<Object> objects = (ArrayList<Object>)(Object) transactions;
        file.clearFile("transactions.json");
        file.writeArrayToJson("transactions.json", objects);
        this.transactions = transactions;
    }

    public ArrayList<Portfolio> getPortfolios() {
        Portfolio[] arr = new Portfolio[0];
        portfolios = (ArrayList<Portfolio>)(Object) file.readArrayFromJson("portfolios.json", arr);
        return portfolios;
    }

    public void setPortfolios(ArrayList<Portfolio> portfolios) {
        ArrayList<Object> objects = (ArrayList<Object>)(Object) portfolios;
        file.clearFile("portfolios.json");
        file.writeArrayToJson("portfolios.json", objects);
        this.portfolios = portfolios;
    }

    public ArrayList<Company> getCompanies() {
        Company[] arr = new Company[0];
        companies = (ArrayList<Company>)(Object) file.readArrayFromJson("companies.json", arr);
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        ArrayList<Object> objects = (ArrayList<Object>)(Object) companies;
        file.clearFile("companies.json");
        file.writeArrayToJson("companies.json", objects);
        this.companies = companies;
    }

    public ArrayList<Mortgage> getMortgages() {
        Mortgage[] arr = new Mortgage[0];
        mortgages = (ArrayList<Mortgage>)(Object) file.readArrayFromJson("mortgages.json", arr);
        return mortgages;
    }

    public void setMortgages(ArrayList<Mortgage> mortgages) {
        ArrayList<Object> objects = (ArrayList<Object>)(Object) mortgages;
        file.clearFile("mortgages.json");
        file.writeArrayToJson("mortgages.json", objects);
        this.mortgages = mortgages;
    }
}
