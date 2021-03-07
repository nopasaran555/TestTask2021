import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class TaskOneTest {

    @Test
    public void testCheckUserAge_isAdult() throws Exception {
        String birthDate = "01.01.2000";
        boolean result = TaskOne.checkUserAge(birthDate);
        Assert.assertTrue(result);
    }

    @Test
    public void testCheckUserAge_isNotAdult() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date date = calendar.getTime();
        String birthDate = dateFormat.format(date);
        boolean result = TaskOne.checkUserAge(birthDate);
        Assert.assertFalse(result);
    }

    @Test
    public void testCheckUserAge_AdultToday() throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        String birthDate = dateFormat.format(date);
        boolean result = TaskOne.checkUserAge(birthDate);
        Assert.assertFalse(result);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testCheckUserAge_invalidDate() throws Exception {
        //invalid date format
        String birthDate = "333.13.2000";
        TaskOne.checkUserAge(birthDate);
    }

}
