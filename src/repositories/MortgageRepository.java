package repositories;

import domain.entities.BankAccount;
import domain.entities.Mortgage;

import java.util.ArrayList;

public class MortgageRepository extends AbstractRepository {

    public ArrayList<Mortgage> getMortgagesBySSN(long SSN) {
        ArrayList<Mortgage> mortgages = MortgageRepository.persistenceData.getMortgages();
        ArrayList<Mortgage> customerMortgages = new ArrayList<>();
        for (Mortgage currentMortgage : mortgages){
            if (currentMortgage.getCustomerSSN() == SSN){
                customerMortgages.add(currentMortgage);
            }
        }
        return customerMortgages;
    }

    public ArrayList<Mortgage> getMortgagesByStatus(String status) {
        ArrayList<Mortgage> mortgages = MortgageRepository.persistenceData.getMortgages();
        ArrayList<Mortgage> statusMortgages = new ArrayList<>();
        for (Mortgage currentMortgage : mortgages){
            if (currentMortgage.getStatus().equals(status)){
                statusMortgages.add(currentMortgage);
            }
        }
        return statusMortgages;
    }

    public ArrayList<Mortgage> getAll() {
        return MortgageRepository.persistenceData.getMortgages();
    }

    public void createMortgage(Mortgage mortgage) {
        ArrayList<Mortgage> mortgages = MortgageRepository.persistenceData.getMortgages();
        mortgages.add(mortgage);
        MortgageRepository.persistenceData.setMortgages(mortgages);
    }

}
