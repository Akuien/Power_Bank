package repositories;

import data.PersistenceData;
import domain.entities.BankAccount;
import domain.entities.Company;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CompanyRepositoryTest {

    private ArrayList<Company> companies;
    private PersistenceData data;
    private CompanyRepository companyRepository;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {

        //Create 2 companies
        //which will be stored in the companies.json file

        Company company1 = new Company("Tesla", 30.45);
        Company company2 = new Company("Google", 20.87);
        companies = new ArrayList<>();

        companies.add(company1);
        companies.add(company2);

        data = new PersistenceData();
        data.setCompanies(companies);
        companyRepository = new CompanyRepository();
    }

    @org.junit.jupiter.api.Test
    void getCompanyByName() {

        assertEquals(companies.get(0).toString(), companyRepository.getCompanyByName("Tesla").toString());

        assertEquals(companies.get(1).toString(), companyRepository.getCompanyByName("Google").toString());
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        assertEquals(companies.toString(), companyRepository.getAll().toString());
    }
}