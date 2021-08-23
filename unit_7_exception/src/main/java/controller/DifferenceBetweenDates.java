package controller;

import entity.MyDate;
import service.CheckFormat;
import service.ConvertStringToDate;
import util.Calculator;
import util.ConvertMillisecondsToDate;

import java.util.Scanner;

public class DifferenceBetweenDates {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ConvertStringToDate CONVERT = new ConvertStringToDate();

    public static void difference() {
        String format;
        do {
            System.out.println("Choose date input format\n" +
                    "1. dd/mm/yyyy 00:00:00:000 (or dd-mm-yyyy)\n" +
                    "2. m/d/yyyy 00:00:00:000 (or m-d-yyyy)\n" +
                    "3. Month d yyyy 00:00:00:000\n" +
                    "4. dd Month yyyy 00:00:00:000");
            format = SCANNER.nextLine();
        } while (Integer.parseInt(format) > 4 || Integer.parseInt(format) <= 0);

        System.out.print("Enter the first date:");
        String firstDate;

        boolean isValid = true;
        do {
            if (!isValid) {
                System.out.println("Incorrect input, try again");
            }
            firstDate = SCANNER.nextLine();
            isValid = false;
        } while (CheckFormat.check(firstDate, format) || CheckFormat.checkFormatOfTime(firstDate));

        MyDate myFirstDate = null;
        try {
            myFirstDate = CONVERT.stringToDate(firstDate, format);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.print("Enter the second date:");
        String secondDate;

        boolean isValidValue = true;
        do {
            if (!isValidValue) {
                System.out.println("Incorrect input, try again");
            }
            secondDate = SCANNER.nextLine();
            isValidValue = false;
        } while (CheckFormat.check(secondDate, format) || CheckFormat.checkFormatOfTime(secondDate));

        MyDate mySecondDate = null;
        try {
            mySecondDate = CONVERT.stringToDate(secondDate, format);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long result = Calculator.differenceBetweenDates(myFirstDate, mySecondDate);
        System.out.println("Result");
        System.out.println("Years: " + (int) ConvertMillisecondsToDate.millisecondsToYears(result));
        System.out.println("Days: " + (int) ConvertMillisecondsToDate.millisecondsToDays(result));
        System.out.println("Hours:" + (int) ConvertMillisecondsToDate.millisecondsToHours(result));
        System.out.println("Minutes:" + (int) ConvertMillisecondsToDate.millisecondsToMinutes(result));
        System.out.println("Seconds:" + (int) ConvertMillisecondsToDate.millisecondsToSeconds(result));
        System.out.println();
    }
}
