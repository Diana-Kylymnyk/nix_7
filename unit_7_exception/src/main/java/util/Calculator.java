package util;

import entity.MyDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");

    public static long differenceBetweenDates(MyDate firstDate, MyDate secondDate) {
        LOGGER_INFO.info("Difference between dates");
        long firstDateIntoMilliseconds = ConvertDateToMilliseconds.dateIntoMilliseconds(firstDate);
        long secondDateIntoMilliseconds = ConvertDateToMilliseconds.dateIntoMilliseconds(secondDate);
        return Math.abs(firstDateIntoMilliseconds - secondDateIntoMilliseconds);
    }

    public static MyDate addMilliseconds(MyDate date, long milliseconds) {
        LOGGER_INFO.info("Add milliseconds to date");
        long dateIntoMilliseconds = ConvertDateToMilliseconds.dateIntoMilliseconds(date);
        return ConvertMillisecondsToDate.millisecondsToDate(dateIntoMilliseconds + milliseconds);
    }

    public static MyDate subtractMilliseconds(MyDate date, long milliseconds) {
        LOGGER_INFO.info("Subtract milliseconds from date");
        long dateIntoMilliseconds = ConvertDateToMilliseconds.dateIntoMilliseconds(date);
        return ConvertMillisecondsToDate.millisecondsToDate(dateIntoMilliseconds - milliseconds);
    }
}
