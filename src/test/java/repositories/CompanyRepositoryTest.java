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

        Company company1 = new Company("Microsoft", 100.0);
        Company company2 = new Company("Apple", 194.0);
        Company company3 = new Company("Amazon", 3000);
        Company company4 = new Company("Google", 3000.0);

        companies = new ArrayList<>();

        companies.add(company1);
        companies.add(company2);
        companies.add(company3);
        companies.add(company4);

        companyRepository = new CompanyRepository();
    }

    @org.junit.jupiter.api.Test
    void getCompanyByName() {

        assertEquals(companies.get(1).toString(), companyRepository.getCompanyByName("Apple").toString());

        assertEquals(companies.get(3).toString(), companyRepository.getCompanyByName("Google").toString());
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        assertEquals(companies.toString(), companyRepository.getAll().toString());
    }
}