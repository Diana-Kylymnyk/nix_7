package service;

import entity.MyDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckFormat {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private static final String[] MONTHS = {"january", "february",
            "march", "april", "may", "june", "july",
            "august", "september", "october", "november", "december"};

    public static boolean check(String date, String choice) {
        boolean isValid = false;
        switch (choice) {
            case "1":
                isValid = firstFormat(date);
                break;
            case "2":
                isValid = secondFormat(date);
                break;
            case "3":
                isValid = thirdFormat(date);
                break;
            case "4":
                isValid = fourthFormat(date);
                break;
            default:
                System.out.println("Incorrect input");
        }
        return !isValid;
    }

    public static boolean firstFormat(String stringDate) {
        LOGGER_INFO.info("Check first format");
        try {
            if (stringDate.contains("/") || stringDate.contains("-")) {
                String delimiter;
                if (stringDate.contains("/")) {
                    delimiter = "/";
                } else {
                    delimiter = "-";
                }

                String[] splitArr = new String[4];

                switch (delimiter) {
                    case "/":
                        splitArr = stringDate.split("[/ ]", 4);
                        break;
                    case "-":
                        splitArr = stringDate.split("[- ]", 4);
                        break;
                }

                if (splitArr.length >= 3) {
                    int day;
                    if (splitArr[0].equals("")) {
                        day = 1;
                    } else {
                        day = Integer.parseInt(splitArr[0]);
                    }

                    int month;
                    if (splitArr[1].equals("")) {
                        month = 1;
                    } else {
                        month = Integer.parseInt(splitArr[1]);
                    }

                    int year;
                    if (splitArr[2].equals("")) {
                        year = 0;
                    } else {
                        year = Integer.parseInt(splitArr[2]);
                    }

                    if ((splitArr[0].length() == 2 || splitArr[0].matches(""))
                            && (splitArr[1].length() == 2 || splitArr[1].matches(""))) {
                        if (day > 0 && month <= 12 && month >= 1 && year >= 0) {
                            return MyDate.isValidDate(year, month, day);
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER_ERROR.error("Incorrect index");
            return false;
        }
    }

    public static boolean secondFormat(String stringDate) {
        LOGGER_INFO.info("Check second format");
        try {
            if (stringDate.contains("/") || stringDate.contains("-")) {
                String delimiter;
                if (stringDate.contains("/")) {
                    delimiter = "/";
                } else {
                    delimiter = "-";
                }

                String[] splitArr = new String[4];

                switch (delimiter) {
                    case "/":
                        splitArr = stringDate.split("[/ ]", 4);
                        break;
                    case "-":
                        splitArr = stringDate.split("[- ]", 4);
                        break;
                }

                if (splitArr.length >= 3) {
                    int day;
                    if (splitArr[1].equals("")) {
                        day = 1;
                    } else {
                        day = Integer.parseInt(splitArr[1]);
                    }
                    int month;
                    if (splitArr[0].equals("")) {
                        month = 1;
                    } else {
                        month = Integer.parseInt(splitArr[0]);
                    }

                    int year;
                    if (splitArr[2].equals("")) {
                        year = 0;
                    } else {
                        year = Integer.parseInt(splitArr[2]);
                    }

                    if (day > 0 && month <= 12 && month >= 1 && year >= 0) {
                        return MyDate.isValidDate(year, month, day);
                    }
                    return false;
                }
                return false;
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER_ERROR.error("Incorrect index");
            return false;
        }
    }

    public static boolean thirdFormat(String stringDate) {
        LOGGER_INFO.info("Check third format");
        try {
            int month;
            String[] splitArr = stringDate.split("[ ]");

            if (splitArr.length >= 3) {
                for (int i = 0; i < MONTHS.length; i++) {
                    if (splitArr[0].equalsIgnoreCase(MONTHS[i])) {
                        month = i + 1;
                        return MyDate.isValidDate(Integer.parseInt(splitArr[2]), month, Integer.parseInt(splitArr[1]));
                    }
                }
            }
            return false;
        } catch (NumberFormatException e) {
            LOGGER_ERROR.error("Incorrect number format");
            return false;
        }
    }

    public static boolean fourthFormat(String stringDate) {
        LOGGER_INFO.info("Check fourth format");
        try {
            int month;
            String[] splitArr = stringDate.split("[ ]");

            if (splitArr.length >= 3) {
                if (splitArr[0].length() == 2 || splitArr[0].matches("")) {
                    for (int i = 0; i < MONTHS.length; i++) {
                        if (splitArr[1].equalsIgnoreCase(MONTHS[i])) {
                            month = i + 1;
                            return MyDate.isValidDate(Integer.parseInt(splitArr[2]), month, Integer.parseInt(splitArr[0]));
                        }
                    }
                }
                return false;
            }
            return false;
        } catch (NumberFormatException e) {
            LOGGER_ERROR.error("Incorrect number format");
            return false;
        }
    }

    public static boolean checkFormatOfTime(String input) {
        boolean flag = true;
        try {
            if (input.contains("/") || input.contains("-")) {
                String delimiter;
                if (input.contains("/")) delimiter = "/";
                else delimiter = "-";
                String[] split = new String[4];

                switch (delimiter) {
                    case "/":
                        split = input.split("[/ ]");
                        break;
                    case "-":
                        split = input.split("[- ]");
                        break;
                }
                if (split.length > 3) {
                    String[] splitTime = split[3].split(":");
                    if (!splitTime[0].equals("")) {
                        if (Integer.parseInt(splitTime[0]) > 23 || Integer.parseInt(splitTime[0]) < 0) {
                            flag = false;
                        }
                    }
                    if (splitTime.length > 1) {
                        if (Integer.parseInt(splitTime[1]) > 59 || Integer.parseInt(splitTime[1]) < 0) {
                            flag = false;
                        }
                    }
                    if (splitTime.length > 2) {
                        if (Integer.parseInt(splitTime[2]) > 59 || Integer.parseInt(splitTime[2]) < 0) {
                            flag = false;
                        }
                    }
                    if (splitTime.length > 3) {
                        if (Integer.parseInt(splitTime[3]) > 999 || Integer.parseInt(splitTime[3]) < 0) {
                            flag = false;
                        }
                    }
                }
            }
        } catch (NumberFormatException e) {
            LOGGER_ERROR.error("Incorrect number format");
            return true;
        }
        return !flag;
    }
}

