package usecases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateEmail {
    public boolean execute(String email) { // Validates email // https://www.youtube.com/watch?v=OOdO785p3Qo

        String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
        Pattern emailPattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.find();
    }
}
