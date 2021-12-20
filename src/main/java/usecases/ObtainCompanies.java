package usecases;

import domain.entities.Company;
import domain.entities.Portfolio;
import domain.exceptions.ShareholderDoesNotExistException;
import repositories.CompanyRepository;
import repositories.PortfolioRepository;

import java.util.ArrayList;

public class ObtainCompanies {

        private CompanyRepository companyRepository;

        public ObtainCompanies(){
            this.companyRepository = new CompanyRepository();
        }

        public ArrayList<Company> execute() throws Exception {
            return companyRepository.getAll();
            // helps the customer to the list of companies to let them choose from.
        }

    }

