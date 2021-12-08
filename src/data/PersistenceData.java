package data;

import domain.entities.*;

import java.util.ArrayList;
import java.util.Arrays;

public class PersistenceData {
    private ArrayList<Employee> employees;
    private ArrayList<Customer> customers;
    private ArrayList<BankAccount> bankAccounts;
    private ArrayList<Transaction> transactions;
    private ArrayList<Portfolio> portfolios;
    private ArrayList<Company> companies;


    public PersistenceData(){
        employees = new ArrayList<>();
        customers = new ArrayList<>();
        bankAccounts = new ArrayList<>();
        transactions = new ArrayList<>();
        portfolios = new ArrayList<>();
        companies = new ArrayList<>(Arrays.asList(new Company("Apple", 194), new Company("Microsoft", 100), new Company("Amazon", 3000)));

        //Infinite amount of shares
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(ArrayList<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public ArrayList<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(ArrayList<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public ArrayList<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(ArrayList<Company> companies) {
        this.companies = companies;
    }
}
