package usecases;

public class ValidatePhoneNumber {

    public boolean execute(String phoneNumber) {
        return phoneNumber.charAt(0) == '0' && phoneNumber.charAt(1) == '7' && phoneNumber.length() == 11 && phoneNumber.matches("[0-9]+");
        // first char is 0 and second char is 7. and then the length of code is 11. And then we check that its in
        //between 0-9. since a phone number can have any number between 0-9 in it.
    }
}
