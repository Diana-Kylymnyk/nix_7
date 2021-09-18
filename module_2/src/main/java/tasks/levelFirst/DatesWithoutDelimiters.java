package tasks.levelFirst;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DatesWithoutDelimiters {

    private static final String DATES_TXT = "files/levelFirst/dates.txt";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void realization() {
        System.out.println("\nReturn correct dates without delimiters");
        boolean flag = true;
        while (flag) {
            System.out.print("1-Enter dates\n" +
                    "2-Take dates from file\n" +
                    "0-Exit\n" +
                    "Your choice>>");

            String choose = SCANNER.nextLine();
            switch (choose) {
                case "1":
                    try {
                        ArrayList<String> dateWithDelimiters = new ArrayList<>();
                        System.out.println("Please, enter count of dates");
                        String count = SCANNER.nextLine();
                        System.out.println("Enter dates");
                        for (int i = 0; i < Integer.parseInt(count); i++) {
                            dateWithDelimiters.add(SCANNER.nextLine());
                        }
                        System.out.println("The list of formatted dates:");
                        String[] datesWithoutDelimiters = ChangeFormatOfDate.checkFormatAndReturnArrayOfDatesWithoutDelimiters(dateWithDelimiters);
                        for (String dateWithoutDelimiters : datesWithoutDelimiters) {
                            if (!dateWithoutDelimiters.equals("")) {
                                System.out.println(dateWithoutDelimiters);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "2":
                    Path path = Paths.get(DATES_TXT);
                    try {
                        List<String> dates = Files.readAllLines(path);
                        dates.forEach(System.out::println);
                        System.out.println("The list of formatted dates:");
                        String[] datesWithoutDelimiters = ChangeFormatOfDate.checkFormatAndReturnArrayOfDatesWithoutDelimiters(dates);
                        for (String dateWithoutDelimiters : datesWithoutDelimiters) {
                            if (!dateWithoutDelimiters.equals("")) {
                                System.out.println(dateWithoutDelimiters);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "0":
                    flag = false;
            }
        }
    }
}
