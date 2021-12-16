package data;

import domain.entities.*;

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
        employees = (ArrayList<Employee>)(Object) file.readArrayFromJson("employees.json");
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        ArrayList<Object> objects = (ArrayList<Object>)(Object) employees;
        file.writeArrayToJson("employees.json", objects);
        this.employees = employees;
    }

    public ArrayList<Customer> getCustomers() {
        customers = (ArrayList<Customer>)(Object) file.readArrayFromJson("customers.json");
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        bankAccounts = (ArrayList<BankAccount>)(Object) file.readArrayFromJson("bankAccounts.json");
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public ArrayList<Transaction> getTransactions() {
        transactions = (ArrayList<Transaction>)(Object) file.readArrayFromJson("transactions.json");
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<Portfolio> getPortfolios() {
        portfolios = (ArrayList<Portfolio>)(Object) file.readArrayFromJson("portfolios.json");
        return portfolios;
    }

    public void setPortfolios(ArrayList<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public ArrayList<Company> getCompanies() {
        companies = (ArrayList<Company>)(Object) file.readArrayFromJson("companies.json");
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }

    public ArrayList<Mortgage> getMortgages() {
        mortgages = (ArrayList<Mortgage>)(Object) file.readArrayFromJson("mortgages.json");
        return mortgages;
    }

    public void setMortgages(ArrayList<Mortgage> mortgages) {
        this.mortgages = mortgages;
    }
}
