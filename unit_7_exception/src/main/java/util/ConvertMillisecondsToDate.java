package util;

import entity.MyDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConvertMillisecondsToDate {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");

    private static final double MILLISECOND = 1;
    private static final double SECOND = MILLISECOND / 1000;
    private static final double MINUTE = SECOND / 60;
    private static final double HOUR = MINUTE / 60;
    private static final double DAY = HOUR / 24;

    private static final long YEAR = 31536000000L;
    private static final long LEAP_YEAR = 31622400000L;

    private static final int[] MONTH_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static double millisecondsToSeconds(long milliseconds) {
        LOGGER_INFO.info("Converting milliseconds to seconds");
        return (milliseconds * SECOND);
    }

    public static double millisecondsToMinutes(long milliseconds) {
        LOGGER_INFO.info("Converting milliseconds to minutes");
        return (milliseconds * MINUTE);
    }

    public static double millisecondsToHours(long milliseconds) {
        LOGGER_INFO.info("Converting milliseconds to hours");
        return (milliseconds * HOUR);
    }

    public static double millisecondsToDays(long milliseconds) {
        LOGGER_INFO.info("Converting milliseconds to days");
        return (milliseconds * DAY);
    }

    public static double millisecondsToYears(long milliseconds) {
        LOGGER_INFO.info("Converting milliseconds to years");
        double year = 0;
        int i = 1;
        while (true) {
            if (milliseconds == 0) return year;
            if (isLeapYear(i)) {
                if (milliseconds >= LEAP_YEAR) {
                    milliseconds -= LEAP_YEAR;
                    year++;
                } else {
                    year += millisecondsToDays(milliseconds) / 366;
                    milliseconds = 0;
                }
            } else {
                if (milliseconds >= YEAR) {
                    milliseconds -= YEAR;
                    year++;
                } else {
                    year += millisecondsToDays(milliseconds) / 365;
                    milliseconds = 0;
                }
            }
            i++;
        }
    }

    public static MyDate millisecondsToDate(long milliseconds) {
        LOGGER_INFO.info("Converting milliseconds to date");
        MyDate myDate = new MyDate();

        long seconds = milliseconds / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        int month = 0;
        int currentMonth = 0;
        int year = 0;

        long tempDays = days;
        while (tempDays > MONTH_DAYS[currentMonth]) {
            tempDays -= MONTH_DAYS[currentMonth];
            if (currentMonth == 1) {
                if (isLeapYear(year)) {
                    tempDays -= 1;
                }
            }
            currentMonth++;
            if (currentMonth > 11) {
                year++;
                currentMonth = 0;
            }
            month++;
        }

        myDate.setMilliseconds((int) (milliseconds % 1000));
        myDate.setSeconds((int) (seconds % 60));
        myDate.setMinutes((int) (minutes % 60));
        myDate.setHours((int) (hours % 24));
        if (isLeapYear(year)) {
            myDate.setDay((int) ((tempDays) % MONTH_DAYS[currentMonth]));
        } else {
            myDate.setDay((int) ((tempDays) % MONTH_DAYS[currentMonth]) + 1);
        }
        myDate.setMonth(((month) % 12) + 1);
        myDate.setYear(year);
        return myDate;
    }

    private static boolean isLeapYear(int year) {
        return (year % 400 == 0 || year % 4 == 0 && year % 100 != 0);
    }
}
