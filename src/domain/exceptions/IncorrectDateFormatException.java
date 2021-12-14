package domain.exceptions;

public class IncorrectDateFormatException extends Exception{
    public IncorrectDateFormatException(String birthDate){
        super("The date " + birthDate + " is not formatted correctly, please use the following format: dd/mm/yyyy.");
    }
}
