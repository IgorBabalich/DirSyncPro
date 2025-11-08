
package edu.wright.dirsyncpro.tools;

import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.*;

/**
 *
 * @author Joe Hendrix <hendrix.11@wright.edu>
 */
public class DatesTest {

    Date testDate = Dates.add(new Date(), Calendar.HOUR, -8);
    Date now = new Date();

    @Test
    public void testCmpDates() {
        System.out.println("cmpDates");
        int gran = 0;
        boolean idlsgran = false;
        assert Dates.cmpDates(testDate.getTime(), testDate.getTime(), gran, idlsgran) == 0;
    }

    @Test
    public void testGetNextCompleteHour() {
        System.out.println("getNextCompleteHour");
        Date result = Dates.getNextCompleteHour();
        assert result.after(now) : "result: " + result + "\tbefore: " + now;

        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assert cal.get(Calendar.MINUTE) == 0;
        assert cal.get(Calendar.SECOND) == 0;
    }

    @Test
    public void testGetNextDayAtThisTime() {
        System.out.println("getNextDayAtThisTime");
        Date result = Dates.getNextDayAtThisTime(testDate);

        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);

        Calendar testCal = Calendar.getInstance();
        testCal.setTime(testDate);

        assert resultCal.get(Calendar.HOUR) == testCal.get(Calendar.HOUR);
        assert resultCal.get(Calendar.MINUTE) == testCal.get(Calendar.MINUTE);
        assert resultCal.get(Calendar.SECOND) == testCal.get(Calendar.SECOND);
    }

    @Test
    public void testGetNextDateAtThisTimeAndNumber() {
        System.out.println("getNextDateAtThisTimeAndNumber");
        int day = 15;
        Date result = Dates.getNextDateAtThisTimeAndNumber(testDate, day, new Date());
        assert result.after(testDate);

        Calendar resultCal = Calendar.getInstance();
        resultCal.setTime(result);

        Calendar testCal = Calendar.getInstance();
        testCal.setTime(testDate);

        assert resultCal.get(Calendar.HOUR) == testCal.get(Calendar.HOUR);
        assert resultCal.get(Calendar.MINUTE) == testCal.get(Calendar.MINUTE);
        assert resultCal.get(Calendar.SECOND) == testCal.get(Calendar.SECOND);
        assert resultCal.get(Calendar.DAY_OF_MONTH) == day;

    }

    @Test
    public void testGetNextCompleteHour_Date() {
        System.out.println("getNextCompleteHour");
        Date result = Dates.getNextCompleteHour(testDate);
        assert result.after(testDate);

        Calendar cal = Calendar.getInstance();
        cal.setTime(result);
        assert cal.get(Calendar.MINUTE) == 0;
        assert cal.get(Calendar.SECOND) == 0;
    }

    @Test
    public void testAdd() {
        System.out.println("add");

        int field = Calendar.DATE;
        int amount = 5;
        Date result = Dates.add(testDate, field, amount);
        assert result.after(testDate) : "result: " + result + "\tbefore: " + testDate;
        result = Dates.add(result, field, -amount);
        assert result.equals(testDate) : "result: " + result + "\tbefore: " + testDate;
    }
}
