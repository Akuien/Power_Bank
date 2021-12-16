package domain.exceptions;

public class DoesNotComplyConditionsForMortgage extends Exception{

    public DoesNotComplyConditionsForMortgage(){
        super("Sorry the mortgage suggested is not available.");
    }
}
