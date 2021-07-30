package realizationClasses;

import methods.StringReverse;

import java.util.Scanner;

public class ReverseByIndexes {

    public static void realization() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your string:");
        String string = sc.nextLine();
        System.out.print("Enter first index: ");
        int first = sc.nextInt();
        System.out.print("Enter last index: ");
        int last = sc.nextInt();
        if (first >= last || last > string.length() || first < 0) {
            System.out.println("Incorrect values!");
            return;
        }
        string = StringReverse.reverse(string, first, last);
        System.out.println(string);
    }
}
