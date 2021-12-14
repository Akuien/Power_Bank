package usecases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateDate {
    public boolean execute(String birthDate) {

        String dateRegex = "([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/(19[0-9][0-9]||20[0-2][0-2])$";
        Pattern datePattern = Pattern.compile(dateRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = datePattern.matcher(birthDate);
        return matcher.find();
    }
}
