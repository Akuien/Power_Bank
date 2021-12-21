package repositories;


import data.PersistenceData;
import domain.entities.Company;
import domain.entities.Stock;

import java.util.ArrayList;

public class CompanyRepository extends AbstractRepository { // Detailed information about this class is provided in "Explanation".

    public Company getCompanyByName(String companyName) {
        ArrayList<Company> companies = CompanyRepository.persistenceData.getCompanies();
        for (Company currentCompany : companies){
           if (currentCompany.getName().equals(companyName)){
               return currentCompany;
           }
        }
        return null;
    }

    public ArrayList<Company> getAll(){
        return CompanyRepository.persistenceData.getCompanies();
    }


}
