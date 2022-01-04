package repositories;

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
        Mortgage mortgage1 = new Mortgage(880702813, 970213742, 1000000, 10.30, 10, 400, 30);
        Mortgage mortgage2 = new Mortgage(970213742, 880702813, 1000001, 13.43, 60, 800.58, 40);

        mortgageRepository.createMortgage(mortgage1);
        mortgageRepository.createMortgage(mortgage2);

        assertEquals(mortgage1.toString(), mortgageRepository.getMortgagesBySSN(880702813).toString());

        assertEquals(mortgage2.toString(), mortgageRepository.getMortgagesBySSN(970213742).toString());
    }

    @org.junit.jupiter.api.Test
    void getMortgagesByStatus() {
        //Create 2 mortgages for each customer and add them to the arraylist
        //which will later be stored in the mortgages.json file

        //We create 2 mortgages,
        // and then add them to the mortgages arraylist
        Mortgage mortgage1 = new Mortgage(880702813, 970213742, 1000000, 10.30, 10, 400, 30);
        Mortgage mortgage2 = new Mortgage(970213742, 880702813, 1000001, 13.43, 60, 800.58, 40);
        Mortgage mortgage3 = new Mortgage(970213742, 880702813, 1000002, 15.26, 75, 950.35, 67);

        mortgage1.setStatus("approved");
        mortgage2.setStatus("pending");
        mortgage3.setStatus("rejected");


        mortgageRepository.createMortgage(mortgage1);
        mortgageRepository.createMortgage(mortgage2);
        mortgageRepository.createMortgage(mortgage3);

        assertEquals(mortgage1.toString(), mortgageRepository.getMortgagesByStatus("accepted").get(0).toString());

        assertEquals(mortgage2.toString(), mortgageRepository.getMortgagesByStatus("pending").get(0).toString());

        assertEquals(mortgage3.toString(), mortgageRepository.getMortgagesByStatus("rejected").get(0).toString());
    }

    @org.junit.jupiter.api.Test
    void getMortgageByLoanID() {
        //Create 2 mortgages for each customer and add them to the arraylist
        //which will later be stored in the mortgages.json file

        //We create 2 mortgages,
        // and then add them to the mortgages arraylist
        Mortgage mortgage1 = new Mortgage(880702813, 970213742, 1000000, 10.30, 10, 400, 30);
        Mortgage mortgage2 = new Mortgage(970213742, 880702813, 1000001, 13.43, 60, 800.58, 40);

        mortgageRepository.createMortgage(mortgage1);
        mortgageRepository.createMortgage(mortgage2);

        assertEquals(mortgage1.toString(), mortgageRepository.getMortgageByLoanID(970213742).toString());

        assertEquals(mortgage2.toString(), mortgageRepository.getMortgageByLoanID(880702813).toString());
    }

    @org.junit.jupiter.api.Test
    void getAll() {
        //Create 2 mortgages for each customer and add them to the arraylist
        //which will later be stored in the mortgages.json file

        //We create 2 mortgages,
        // and then add them to the mortgages arraylist
        Mortgage mortgage1 = new Mortgage(880702813, 970213742, 1000000, 10.30, 10, 400, 30);
        Mortgage mortgage2 = new Mortgage(970213742, 880702813, 1000001, 13.43, 60, 800.58, 40);

        ArrayList<Mortgage> temp = new ArrayList<>();
        temp.add(mortgage1);
        temp.add(mortgage2);

        mortgageRepository.createMortgage(mortgage1);
        mortgageRepository.createMortgage(mortgage2);

        assertEquals(temp.size(), mortgageRepository.getAll().size());

        assertEquals(temp.get(0).toString(), mortgageRepository.getAll().get(0).toString());

        assertEquals(temp.toString(), mortgageRepository.getAll().toString());
    }

    @org.junit.jupiter.api.Test
    void createMortgage() {
        Mortgage mortgage1 = new Mortgage(880702813, 970213742, 1000000, 10.30, 10, 400, 30);

        mortgageRepository.createMortgage(mortgage1);

        assertEquals(mortgage1.toString(), mortgageRepository.getMortgageByLoanID(970213742).toString());
    }

    @org.junit.jupiter.api.Test
    void updateMortgage() {
        Mortgage test1 = new Mortgage(880702813, 970213742, 1000000, 10.30, 10, 400, 30);

        mortgageRepository.createMortgage(test1);

        Mortgage test2 = new Mortgage(880702813, 970213742, 1000000, 14.30, 10, 400, 50);

        mortgageRepository.updateMortgage(test2);

        assertEquals(test2.toString(), mortgageRepository.getMortgageByLoanID(970213742).toString());
    }
}