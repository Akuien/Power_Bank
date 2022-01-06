package repositories;

import domain.entities.BankAccount;
import domain.entities.Mortgage;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MortgageRepositoryTest {

    private MortgageRepository mortgageRepository;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        mortgageRepository = new MortgageRepository();
    }


    @org.junit.jupiter.api.Test
    void getMortgagesBySSN() {
        //Create 2 mortgages for each customer and add them to the arraylist
        //which will later be stored in the mortgages.json file

        //We create 2 mortgages,
        // and then add them to the mortgages arraylist
        Mortgage mortgage1 = new Mortgage(991046478, 970215742, 9000000, 10.30, 10, 400, 30);
        Mortgage mortgage2 = new Mortgage(880375897, 880709813, 9000001, 13.43, 60, 800.58, 40);

        mortgageRepository.createMortgage(mortgage1);
        mortgageRepository.createMortgage(mortgage2);

        assertEquals(mortgage1.toString(), mortgageRepository.getMortgagesBySSN(970215742).get(0).toString());

        assertEquals(mortgage2.toString(), mortgageRepository.getMortgagesBySSN(880709813).get(0).toString());

        ArrayList<Mortgage> temp = mortgageRepository.getAll();

        //Remove the last 2 added mortgages
        //used for the test
        temp.remove(temp.size()-1);
        temp.remove(temp.size()-1);
        MortgageRepository.persistenceData.setMortgages(temp);
    }

    @org.junit.jupiter.api.Test
    void getMortgagesByStatus() {
        //Create 2 mortgages for each customer and add them to the arraylist
        //which will later be stored in the mortgages.json file

        //We create 2 mortgages,
        // and then add them to the mortgages arraylist
        Mortgage mortgage1 = new Mortgage(991046478, 970215742, 9000000, 10.30, 10, 400, 30);
        Mortgage mortgage2 = new Mortgage(880375897, 880709813, 9000001, 13.43, 60, 800.58, 40);
        Mortgage mortgage3 = new Mortgage(880375897, 880702813, 9000002, 15.26, 75, 950.35, 67);

        mortgage1.setStatus("approved");
        mortgage2.setStatus("pending");
        mortgage3.setStatus("rejected");


        mortgageRepository.createMortgage(mortgage1);
        mortgageRepository.createMortgage(mortgage2);
        mortgageRepository.createMortgage(mortgage3);

        ArrayList<Mortgage> approvedMortgages = mortgageRepository.getMortgagesByStatus("approved");
        ArrayList<Mortgage> pendingMortgages = mortgageRepository.getMortgagesByStatus("pending");
        ArrayList<Mortgage> rejectedMortgages = mortgageRepository.getMortgagesByStatus("rejected");

        assertEquals(mortgage1.toString(), approvedMortgages.get(approvedMortgages.size()-1).toString());

        assertEquals(mortgage2.toString(), pendingMortgages.get(pendingMortgages.size()-1).toString());

        assertEquals(mortgage3.toString(), rejectedMortgages.get(approvedMortgages.size()-1).toString());


        ArrayList<Mortgage> temp = mortgageRepository.getAll();

        //Remove the last 2 added mortgages
        //used for the test
        temp.remove(temp.size()-1);
        temp.remove(temp.size()-1);
        temp.remove(temp.size()-1);
        MortgageRepository.persistenceData.setMortgages(temp);
    }

    @org.junit.jupiter.api.Test
    void getMortgageByLoanID() {
        //Create 2 mortgages for each customer and add them to the arraylist
        //which will later be stored in the mortgages.json file

        //We create 2 mortgages,
        // and then add them to the mortgages arraylist
        Mortgage mortgage1 = new Mortgage(991046478, 970215742, 9000000, 10.30, 10, 400, 30);
        Mortgage mortgage2 = new Mortgage(880375897, 880709813, 9000001, 13.43, 60, 800.58, 40);

        mortgageRepository.createMortgage(mortgage1);
        mortgageRepository.createMortgage(mortgage2);

        assertEquals(mortgage1.toString(), mortgageRepository.getMortgageByLoanID(9000000).toString());

        assertEquals(mortgage2.toString(), mortgageRepository.getMortgageByLoanID(9000001).toString());

        ArrayList<Mortgage> temp = mortgageRepository.getAll();

        //Remove the last 2 added mortgages
        //used for the test
        temp.remove(temp.size()-1);
        temp.remove(temp.size()-1);
        MortgageRepository.persistenceData.setMortgages(temp);
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        //Create 2 mortgages for each customer and add them to the arraylist
        //which will later be stored in the mortgages.json file

        //We create 2 mortgages,
        // and then add them to the mortgages arraylist
        Mortgage mortgage1 = new Mortgage(880702813, 970213742, 9000000, 10.30, 10, 400, 30);
        Mortgage mortgage2 = new Mortgage(970213742, 880702813, 9000001, 13.43, 60, 800.58, 40);

        ArrayList<Mortgage> temp = new ArrayList<>();
        temp.add(mortgage1);
        temp.add(mortgage2);

        mortgageRepository.createMortgage(mortgage1);
        mortgageRepository.createMortgage(mortgage2);

        ArrayList<Mortgage> temp2 = mortgageRepository.getAll();

        assertEquals(temp.get(temp.size()-1).toString(), temp2.get(temp2.size()-1).toString());

        assertEquals(temp.get(temp.size()-2).toString(), temp2.get(temp2.size()-2).toString());

        //Remove the last 2 added mortgages
        //used for the test
        temp2.remove(temp2.size()-1);
        temp2.remove(temp2.size()-1);
        MortgageRepository.persistenceData.setMortgages(temp2);
    }

    @org.junit.jupiter.api.Test
    void createMortgage() {
        Mortgage mortgage1 = new Mortgage(880702813, 970213742, 9000000, 10.30, 10, 400, 30);

        mortgageRepository.createMortgage(mortgage1);

        assertEquals(mortgage1.toString(), mortgageRepository.getMortgageByLoanID(9000000).toString());

        ArrayList<Mortgage> temp = mortgageRepository.getAll();

        //Remove the last added mortgage
        //used for the test
        temp.remove(temp.size()-1);
        MortgageRepository.persistenceData.setMortgages(temp);
    }

    @org.junit.jupiter.api.Test
    void updateMortgage() {
        Mortgage test1 = new Mortgage(880702813, 970213742, 9000000, 10.30, 10, 400, 30);

        mortgageRepository.createMortgage(test1);

        Mortgage test2 = new Mortgage(880702813, 970213742, 9000000, 14.30, 10, 400, 50);

        mortgageRepository.updateMortgage(test2);

        assertEquals(test2.toString(), mortgageRepository.getMortgageByLoanID(9000000).toString());

        ArrayList<Mortgage> temp = mortgageRepository.getAll();

        //Remove the last added mortgage
        //used for the test
        temp.remove(temp.size()-1);
        MortgageRepository.persistenceData.setMortgages(temp);
    }
}