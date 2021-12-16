package domain.exceptions;

public class CompanyDoesNotExistException extends Exception{

    public CompanyDoesNotExistException(String companyName){
        super("Company " + companyName + " does not exist.");
    }
}
