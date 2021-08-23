package controller;

import entity.MyDate;
import service.CheckFormat;
import service.ConvertDateToString;
import service.ConvertStringToDate;
import util.Sorter;

import java.util.ArrayList;
import java.util.Scanner;

public class SortingListOfDates {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ConvertStringToDate CONVERT = new ConvertStringToDate();
    private static final ConvertDateToString PRINT_DATE = new ConvertDateToString();

    public static void sorting() throws NumberFormatException {
        ArrayList<MyDate> dates = new ArrayList<>();

        String format;
        do {
            System.out.println("Choose date input format\n" +
                    "1. dd/mm/yyyy 00:00:00:000 (or dd-mm-yyyy)\n" +
                    "2. m/d/yyyy 00:00:00:000 (or m-d-yyyy)\n" +
                    "3. mmm-d-yy 00:00:00:000 (or Month d yyyy)\n" +
                    "4. dd-mmm-yyyy 00:00:00:000 (or dd Month yyyy)");
            format = SCANNER.nextLine();
        } while (Integer.parseInt(format) > 4 || Integer.parseInt(format) <= 0);

        try {
            System.out.print("Enter count of dates:");
            String count = SCANNER.nextLine();
            for (int i = 0; i < Integer.parseInt(count); i++) {
                System.out.print("Enter the date:");
                String date;

                boolean isValid = true;
                do {
                    if (!isValid) {
                        System.out.println("Incorrect input, try again");
                    }
                    date = SCANNER.nextLine();
                    isValid = false;
                } while (CheckFormat.check(date, format) || CheckFormat.checkFormatOfTime(date));

                MyDate myDate = null;
                try {
                    myDate = CONVERT.stringToDate(date, format);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dates.add(myDate);
            }

            System.out.println("1. Descending sort\n" + "2. Ascending sort");
            String choiceSort = SCANNER.nextLine();
            ArrayList<MyDate> sortedDates = Sorter.sortDates(dates, choiceSort);

            System.out.println("Choose format of date\n" +
                    "1. dd/mm/yy 00:00:00:000\n" +
                    "2. m/d/yy 00:00:00:000\n" +
                    "3. Month d yyyy 00:00:00:000\n" +
                    "4. dd Month yy 00:00:00:000");
            String choiceFormat = SCANNER.nextLine();
            System.out.println("The dates:");
            for (MyDate d : sortedDates) {
                System.out.print(PRINT_DATE.dateToString(d, choiceFormat));
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage() + " Incorrect!");
        }
    }
}
