package data;

import domain.entities.*;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.Arrays;

public class PersistenceData {

    private ArrayList<Employee> employees;
    private ArrayList<Customer> customers;
    private ArrayList<BankAccount> bankAccounts;
    private ArrayList<Transaction> transactions;
    private ArrayList<Portfolio> portfolios;
    private ArrayList<Company> companies;
    private ArrayList<Mortgage> mortgages;


    public PersistenceData(){

        employees = new ArrayList<>();
        customers = new ArrayList<>();
        bankAccounts = new ArrayList<>();
        transactions = new ArrayList<>();
        portfolios = new ArrayList<>();
        companies = new ArrayList<>(Arrays.asList(new Company("Apple", 194), new Company("Microsoft", 100), new Company("Amazon", 3000)));
        mortgages = new ArrayList<>();

        //Infinite amount of shares
    }

    //We need to specify the type of object we are going to read or write for each method, for this reason the CustomJsonIo class needs to receive the "Type" parameter.
    public ArrayList<Employee> getEmployees() {
        CustomJsonIo<Employee> file = new CustomJsonIo<>();
        employees = file.readArrayFromJson("employees.json", Employee.class);
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        CustomJsonIo<Employee> file = new CustomJsonIo<>();
        file.clearFile("employees.json");
        file.writeArrayToJson("employees.json", employees);
        this.employees = employees;
    }

    public ArrayList<Customer> getCustomers() {
        CustomJsonIo<Customer> file = new CustomJsonIo<>();
        customers = file.readArrayFromJson("customers.json", Customer.class);
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        CustomJsonIo<Customer> file = new CustomJsonIo<>();
        file.clearFile("customers.json");
        file.writeArrayToJson("customers.json", customers);
        this.customers = customers;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        CustomJsonIo<BankAccount> file = new CustomJsonIo<>();
        bankAccounts = file.readArrayFromJson("bankAccounts.json", BankAccount.class);
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        CustomJsonIo<BankAccount> file = new CustomJsonIo<>();
        file.clearFile("bankAccounts.json");
        file.writeArrayToJson("bankAccounts.json", bankAccounts);
        this.bankAccounts = bankAccounts;
    }

    public ArrayList<Transaction> getTransactions() {
        CustomJsonIo<Transaction> file = new CustomJsonIo<>();
        transactions = file.readArrayFromJson("transactions.json", Transaction.class);
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        CustomJsonIo<Transaction> file = new CustomJsonIo<>();
        file.clearFile("transactions.json");
        file.writeArrayToJson("transactions.json", transactions);
        this.transactions = transactions;
    }

    public ArrayList<Portfolio> getPortfolios() {
        CustomJsonIo<Portfolio> file = new CustomJsonIo<>();
        portfolios = file.readArrayFromJson("portfolios.json", Portfolio.class);
        return portfolios;
    }

    public void setPortfolios(ArrayList<Portfolio> portfolios) {
        CustomJsonIo<Portfolio> file = new CustomJsonIo<>();
        file.clearFile("portfolios.json");
        file.writeArrayToJson("portfolios.json", portfolios);
        this.portfolios = portfolios;
    }

    public ArrayList<Company> getCompanies() {
        CustomJsonIo<Company> file = new CustomJsonIo<>();
        companies = file.readArrayFromJson("companies.json", Company.class);
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        CustomJsonIo<Company> file = new CustomJsonIo<>();
        file.clearFile("companies.json");
        file.writeArrayToJson("companies.json", companies);
        this.companies = companies;
    }

    public ArrayList<Mortgage> getMortgages() {
        CustomJsonIo<Mortgage> file = new CustomJsonIo<>();
        mortgages = file.readArrayFromJson("mortgages.json", Mortgage.class);
        return mortgages;
    }

    public void setMortgages(ArrayList<Mortgage> mortgages) {
        CustomJsonIo<Mortgage> file = new CustomJsonIo<>();
        file.clearFile("mortgages.json");
        file.writeArrayToJson("mortgages.json", mortgages);
        this.mortgages = mortgages;
    }
}
