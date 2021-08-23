package service;

import entity.MyDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConvertStringToDate {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};

    public static MyDate firstFormat(String stringDate) {
        LOGGER_INFO.info("Converting first format to string");
        MyDate myDate = new MyDate();

        String delimiter;
        if (stringDate.contains("/")) {
            delimiter = "/";
        } else {
            delimiter = "-";
        }

        String[] split = new String[4];
        switch (delimiter) {
            case "/":
                split = stringDate.split("[/ ]", 4);
                break;
            case "-":
                split = stringDate.split("[- ]", 4);
                break;
        }
        try {
            if (!split[0].equals("")) {
                myDate.setDay(Integer.parseInt(split[0]));
            } else {
                myDate.setDay(1);
            }
            if (!split[1].equals("")) {
                myDate.setMonth(Integer.parseInt(split[1]));
            } else {
                myDate.setMonth(1);
            }
            if (!split[2].equals("")) {
                myDate.setYear(Integer.parseInt(split[2]));
            } else {
                myDate.setYear(0);
            }
            if (split.length > 3) {
                setTime(myDate, split[3]);
            }
            return myDate;
        } catch (NumberFormatException e) {
            LOGGER_ERROR.error("Incorrect number format");
            throw new NumberFormatException();
        }
    }

    public static MyDate secondFormat(String stringDate) {
        LOGGER_INFO.info("Converting second format to string");
        MyDate myDate = new MyDate();

        String delimiter;
        if (stringDate.contains("/")) {
            delimiter = "/";
        } else {
            delimiter = "-";
        }

        String[] split = new String[4];
        switch (delimiter) {
            case "/":
                split = stringDate.split("[/ ]", 4);
                break;
            case "-":
                split = stringDate.split("[- ]", 4);
                break;
        }
        try {
            if (!split[0].equals("")) {
                myDate.setMonth(Integer.parseInt(split[0]));
            } else {
                myDate.setMonth(1);
            }
            if (!split[1].equals("")) {
                myDate.setDay(Integer.parseInt(split[1]));
            } else {
                myDate.setDay(1);
            }
            if (!split[2].equals("")) {
                myDate.setYear(Integer.parseInt(split[2]));
            } else {
                myDate.setYear(0);
            }
            if (split.length > 3) {
                setTime(myDate, split[3]);
            }
            return myDate;
        } catch (NumberFormatException e) {
            LOGGER_ERROR.error("Incorrect number format");
            throw new NumberFormatException();
        }
    }

    public static MyDate thirdFormat(String stringDate) {
        LOGGER_INFO.info("Converting third format to string");
        MyDate myDate = new MyDate();

        String[] split;
        if (stringDate.contains("-")) {
            split = stringDate.split("[- ]", 4);
        } else {
            split = stringDate.split("[ ]");
        }
        try {
            int month;
            for (int i = 0; i < MONTHS.length; i++) {
                if (split[0].equals(MONTHS[i])) {
                    month = i + 1;
                    myDate.setMonth(month);
                }
            }
            if (!split[1].equals("")) {
                myDate.setDay(Integer.parseInt(split[1]));
            } else {
                myDate.setDay(0);
            }
            if (!split[2].equals("")) {
                myDate.setYear(Integer.parseInt(split[2]));
            } else {
                myDate.setYear(0);
            }

            if (split.length > 3) {
                setTime(myDate, split[3]);
            }
            return myDate;
        } catch (NumberFormatException e) {
            LOGGER_ERROR.error("Incorrect number format");
            throw new NumberFormatException();
        }
    }

    public static MyDate fourthFormat(String stringDate) {
        LOGGER_INFO.info("Converting fourth format to string");
        MyDate myDate = new MyDate();

        String[] split;
        if (stringDate.contains("-")) {
            split = stringDate.split("[- ]", 4);
        } else {
            split = stringDate.split("[ ]");
        }
        try {
            if (!split[0].equals("")) {
                myDate.setDay(Integer.parseInt(split[0]));
            } else {
                myDate.setDay(0);
            }
            int month;
            for (int i = 0; i < MONTHS.length; i++) {
                if (split[1].equals(MONTHS[i])) {
                    month = i + 1;
                    myDate.setMonth(month);
                }
            }
            if (!split[2].equals("")) {
                myDate.setYear(Integer.parseInt(split[2]));
            } else {
                myDate.setYear(0);
            }

            if (split.length > 3) {
                setTime(myDate, split[3]);
            }
            return myDate;
        } catch (NumberFormatException e) {
            LOGGER_ERROR.error("Incorrect number format");
            throw new NumberFormatException();
        }
    }

    public static void setTime(MyDate date, String time) {
        String[] splitter = time.split(":");
        try {
            for (int i = 0; i < splitter.length; i++) {
                switch (i) {
                    case 0:
                        date.setHours(Integer.parseInt(splitter[0]));
                        break;
                    case 1:
                        date.setMinutes(Integer.parseInt(splitter[1]));
                        break;
                    case 2:
                        date.setSeconds(Integer.parseInt(splitter[2]));
                        break;
                    case 3:
                        date.setMilliseconds(Integer.parseInt(splitter[3]));
                        break;
                }
            }
        } catch (NumberFormatException e) {
            LOGGER_ERROR.error("Incorrect number format");
            throw new NumberFormatException();
        }
    }

    public MyDate stringToDate(String date, String choice) {
        MyDate myDate = new MyDate();
        switch (choice) {
            case "1":
                myDate = firstFormat(date);
                break;
            case "2":
                myDate = secondFormat(date);
                break;
            case "3":
                myDate = thirdFormat(date);
                break;
            case "4":
                myDate = fourthFormat(date);
                break;
            default:
                System.out.println("Incorrect input");
        }
        return myDate;
    }
}
