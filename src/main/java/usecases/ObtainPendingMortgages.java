package usecases;

import domain.constants.MortgageStatus;
import domain.entities.Mortgage;

import repositories.MortgageRepository;

import java.util.ArrayList;

public class ObtainPendingMortgages {
    // Creating a MortgageRepository component.
    private MortgageRepository mortgageRepository;
    // Creating a constructor.
    public ObtainPendingMortgages(){
        this.mortgageRepository = new MortgageRepository();
    }
    // Creating a method that returns a list of mortgages of type "pending".
    public ArrayList<Mortgage> execute(){
        return mortgageRepository.getMortgagesByStatus(MortgageStatus.pending);
    }
}