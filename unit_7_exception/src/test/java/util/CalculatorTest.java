package util;

import entity.MyDate;
import exceptions.DateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalculatorTest {

    private final static MyDate myDate1 = new MyDate();
    private final static MyDate myDate2 = new MyDate();

    @Test
    public void differenceTest() throws DateException, ParseException {
        myDate1.setDateAndTime(2013, 5, 15, 0, 0, 0, 0);
        myDate2.setDateAndTime(2018, 5, 17, 0, 0, 0, 0);
        System.out.println(myDate1);
        System.out.println(myDate2);
        long differenceBetweenDates = Calculator.differenceBetweenDates(myDate1, myDate2);
        System.out.println("differenceBetweenDates = " + differenceBetweenDates);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date1 = dateFormat.parse("15.05.2013");
        Date date2 = dateFormat.parse("17.05.2018");

        long milliseconds = date2.getTime() - date1.getTime();
        Assertions.assertEquals(differenceBetweenDates, milliseconds);
    }

    @Test
    public void additionAndSubtractionTest() throws DateException {
        myDate1.setDateAndTime(2019, 10, 30, 12, 2, 30, 123);
        System.out.println(myDate1);

        long mil = ConvertDateToMilliseconds.secondsToMilliseconds(34);
        MyDate newDate = Calculator.addMilliseconds(myDate1, mil);
        System.out.println(Calculator.addMilliseconds(myDate1, mil));
        Assertions.assertEquals(myDate1, Calculator.subtractMilliseconds(newDate, mil));
    }
}
