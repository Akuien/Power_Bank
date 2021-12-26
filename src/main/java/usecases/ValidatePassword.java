package usecases;

public class ValidatePassword {

    public boolean execute(String password) { // Password need to contain Uppercase, lowercase, number and be longer then 8
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))){
                count ++;
            }
        }
        if (password.length() >= 8){
            count ++;
        }
        for (int p = 0; p < password.length(); p++) {
            if (Character.isLowerCase(password.charAt(p))) {
                count ++;
            }
        }
        for (int x = 0; x < password.length(); x++) {
            if (Character.isDigit(password.charAt(x))) {
                count ++;
            }
        }
        return count >= 4;
    }
}
