package data;

import domain.entities.*;

import javax.sound.sampled.Port;
import java.util.ArrayList;
import java.util.Arrays;

public class PersistenceData {

    private ArrayList<Employee> employees;
    private ArrayList<Customer> customers;
    private ArrayList<Shareholder> shareholders;
    private ArrayList<Manager> managers;
    private ArrayList<BankAccount> bankAccounts;
    private ArrayList<Transaction> transactions;
    private ArrayList<Portfolio> portfolios;
    private ArrayList<Company> companies;
    private ArrayList<Mortgage> mortgages;


    public PersistenceData(){

        employees = new ArrayList<>();
        customers = new ArrayList<>();
        shareholders = new ArrayList<>();
        managers = new ArrayList<>();
        bankAccounts = new ArrayList<>();
        transactions = new ArrayList<>();
        portfolios = new ArrayList<>();
        companies = new ArrayList<>();
        mortgages = new ArrayList<>();

        //Infinite amount of shares
    }

    //We need to specify the type of object we are going to read or write for each method, for this reason the CustomJsonIo class needs to receive the "Type" parameter.
    public ArrayList<Employee> getEmployees() {
        CustomJsonIo<Employee> file = new CustomJsonIo<>();
        Employee[] array = new Employee[0];
        employees = file.readArrayFromJson("employees.json", array);
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
        Customer[] array = new Customer[0];
        customers = file.readArrayFromJson("customers.json", array);
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        CustomJsonIo<Customer> file = new CustomJsonIo<>();
        file.clearFile("customers.json");
        file.writeArrayToJson("customers.json", customers);
        this.customers = customers;
    }

    public ArrayList<Shareholder> getShareHolders() {
        CustomJsonIo<Shareholder> file = new CustomJsonIo<>();
        Shareholder[] array = new Shareholder[0];
        shareholders = file.readArrayFromJson("shareholders.json", array);
        return shareholders;
    }

    public void setShareHolders(ArrayList<Shareholder> shareholders) {
        CustomJsonIo<Shareholder> file = new CustomJsonIo<>();
        file.clearFile("shareholders.json");
        file.writeArrayToJson("shareholders.json", shareholders);
        this.shareholders = shareholders;
    }

    public ArrayList<Manager> getManagers() {
        CustomJsonIo<Manager> file = new CustomJsonIo<>();
        Manager[] array = new Manager[0];
        managers = file.readArrayFromJson("managers.json", array);
        return managers;
    }

    public void setManagers(ArrayList<Manager> managers) {
        CustomJsonIo<Manager> file = new CustomJsonIo<>();
        file.clearFile("managers.json");
        file.writeArrayToJson("managers.json", managers);
        this.managers = managers;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        CustomJsonIo<BankAccount> file = new CustomJsonIo<>();
        BankAccount[] array = new BankAccount[0];
        bankAccounts = file.readArrayFromJson("bankAccounts.json", array);
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
        Transaction[] array = new Transaction[0];
        transactions = file.readArrayFromJson("transactions.json", array);
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
        Portfolio[] array = new Portfolio[0];
        portfolios = file.readArrayFromJson("portfolios.json", array);
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
        Company[] array = new Company[0];
        companies = file.readArrayFromJson("companies.json", array);
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
        Mortgage[] array = new Mortgage[0];
        mortgages = file.readArrayFromJson("mortgages.json", array);
        return mortgages;
    }

    public void setMortgages(ArrayList<Mortgage> mortgages) {
        CustomJsonIo<Mortgage> file = new CustomJsonIo<>();
        file.clearFile("mortgages.json");
        file.writeArrayToJson("mortgages.json", mortgages);
        this.mortgages = mortgages;
    }
}
