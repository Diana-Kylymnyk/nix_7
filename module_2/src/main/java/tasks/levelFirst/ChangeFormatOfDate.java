package tasks.levelFirst;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeFormatOfDate {

    private static final int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public static String[] checkFormatAndReturnArrayOfDatesWithoutDelimiters(List<String> dates) {
        List<String> datesWithoutDelimiters = new ArrayList<>();

        List<String> formats = List.of(
                "(?<day>\\d{2})/(?<month>\\d{2})/(?<year>\\d{4})",
                "(?<year>\\d{4})/(?<month>\\d{2})/(?<day>\\d{2})",
                "(?<month>\\d{2})-(?<day>\\d{2})-(?<year>\\d{4})");

        for (String date : dates) {
            for (String format : formats) {
                Matcher m = Pattern.compile(format).matcher(date);
                if (m.matches()) {
                    datesWithoutDelimiters.add(returnDateWithoutDelimiters(m.group("year"), m.group("month"), m.group("day")));
                    break;
                }
            }
        }
        return datesWithoutDelimiters.toArray(new String[0]);
    }

    private static String returnDateWithoutDelimiters(String year, String month, String day) {
        if (isValidDate(year, month, day)) {
            return year + month + day;
        } else {
            return "";
        }
    }

    public static boolean isLeapYear(int year) {
        return (year % 400 == 0 || year % 4 == 0 && year % 100 != 0);
    }

    public static boolean isValidDate(String year, String month, String day) {
        boolean isValid = true;
        int m = Integer.parseInt(month) - 1;

        if (m < 0 || m >= 12) {
            isValid = false;
        }

        try {
            int maxDate = daysInMonth[m];
            if (m == 1 && isLeapYear(Integer.parseInt(year))) {
                maxDate = 29;
            }

            if (Integer.parseInt(day) < 1 || Integer.parseInt(day) > maxDate) {
                isValid = false;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Problem:" + e.getMessage());
        }
        return isValid;
    }
}
