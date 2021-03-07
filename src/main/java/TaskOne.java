import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TaskOne {

    /**
     * This method check (based on date of birth) - is user Adult or not
     * @param dateOfBirth: date format - dd.MM.yyyy
     * @return
     * @throws Exception
     **/
    public static boolean checkUserAge(String dateOfBirth) throws Exception {
        boolean isAdult = false;

        if (! (dateOfBirth.matches("^\\d{2}\\.\\d{2}\\.\\d{4}")) ) {
            throw new IllegalArgumentException(String.format("Invalid Date of Birth. Expected format 'dd.MM.yyyy', actual '%s'", dateOfBirth));
        }
        Date birthDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateOfBirth);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        Date todayAdultDate = calendar.getTime();

        if (todayAdultDate.compareTo(birthDate) > 0){
            isAdult = true;
        }

        return isAdult;

    }

}
