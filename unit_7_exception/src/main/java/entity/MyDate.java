package entity;

import exceptions.DateException;

public class MyDate {

    private int year;
    private int month;
    private int day;
    private int hours;
    private int minutes;
    private int seconds;
    private int milliseconds;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    private static final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public MyDate() {
    }

    public void setDateAndTime(int year, int month, int day, int hour, int minute, int second, int mil)
            throws DateException {
        if (isValidDate(year, month, day)) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.hours = hour;
            this.minutes = minute;
            this.seconds = second;
            this.milliseconds = mil;
        } else {
            throw new DateException();
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 400 == 0 || year % 4 == 0 && year % 100 != 0);
    }

    public static boolean isValidDate(int year, int month, int day) {
        boolean isValid = true;
        int m = month - 1;

        if (m < 0 || m >= 12) {
            isValid = false;
            System.out.println("date is not valid");
        }

        int maxDate = daysInMonth[m];
        if (m == 1 && isLeapYear(year)) {
            maxDate = 29;
        }

        if (day < 1 || day > maxDate) {
            isValid = false;
            System.out.println("date is not valid, max date in this month is " + maxDate);
        }
        return isValid;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year + " " +
                hours + ":" + minutes + ":" + seconds + ":" + milliseconds;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MyDate)) {
            return false;
        }
        MyDate date = (MyDate) obj;
        return (this.year == date.year) && (this.month == date.month) && (this.day == date.day);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + year;
        result = 31 * result + month;
        result = 31 * result + day;

        return result;
    }
}
