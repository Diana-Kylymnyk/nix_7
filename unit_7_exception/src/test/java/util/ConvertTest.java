package util;

import entity.MyDate;
import exceptions.DateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConvertTest {

    private final static MyDate myDate = new MyDate();

    @Test
    public void convertDate() throws DateException {
        myDate.setDateAndTime(2013, 6, 20, 12, 2, 30, 123);
        System.out.println(myDate);
        long res = ConvertDateToMilliseconds.dateIntoMilliseconds(myDate);
        System.out.println(res);
        System.out.println(ConvertMillisecondsToDate.millisecondsToDate(res));
        Assertions.assertEquals(myDate, ConvertMillisecondsToDate.millisecondsToDate(res));
    }

    @Test
    public void convertDateWithLeapYear() throws DateException {
        myDate.setDateAndTime(2016, 6, 20, 12, 2, 30, 123);
        System.out.println(myDate);
        long res = ConvertDateToMilliseconds.dateIntoMilliseconds(myDate);
        System.out.println(res);
        System.out.println(ConvertMillisecondsToDate.millisecondsToDate(res));
        Assertions.assertEquals(myDate, ConvertMillisecondsToDate.millisecondsToDate(res));
    }
}
