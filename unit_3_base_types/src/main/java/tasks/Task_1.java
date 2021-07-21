package tasks;

import java.util.Scanner;

public class Task_1 {
    public static void sumFromString(String string) {
        int sum = 0, otherCharacters = 0;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                sum += Character.getNumericValue(string.charAt(i));
            } else {
                otherCharacters++;
            }
        }
        if (otherCharacters == string.length()) {
            System.out.println("There are no numbers!\n");
        } else {
            System.out.println("SumOfNumbers = " + sum + "\n");
        }
    }

    public static void realization() {
        Scanner sc = new Scanner(System.in);
        String string;
        System.out.print("Enter your string with numbers: ");
        string = sc.nextLine();
        sumFromString(string);
    }
}
