package usecases;

import domain.entities.Company;
import domain.entities.Portfolio;
import domain.exceptions.ShareholderDoesNotExistException;
import repositories.CompanyRepository;
import repositories.PortfolioRepository;

import java.util.ArrayList;

public class ObtainCompanies {

        private CompanyRepository companyRepository;
        // Calling companyRepository class as a component.
        public ObtainCompanies(){
            this.companyRepository = new CompanyRepository();
        }

        public ArrayList<Company> execute() throws Exception {
            return companyRepository.getAll();
            // Helps the customer to see the list of companies to choose from.
        }

    }

