package usecases;

public class ValidatePassword {

    public boolean execute(String password) { // Password need to contain Uppercase, lowercase, number and be longer then 8
        int count = 0;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))){
                count++;
            }
        }
        for (int n = 0; n < password.length(); n++) {
            if (password.length() <= 7){
                count++;
            }
        }
        for (int p = 0; p < password.length(); p++) {
            if (Character.isLowerCase(password.charAt(p))){

            }
        }
        for (int x = 0; x < password.length(); x++) {
            if (Character.isDigit(password.charAt(x))) {
            }
        }
        return true;
    }
}
