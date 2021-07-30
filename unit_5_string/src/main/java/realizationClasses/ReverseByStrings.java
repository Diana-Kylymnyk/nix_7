package realizationClasses;

import methods.StringReverse;

import java.util.Scanner;

public class ReverseByStrings {

    public static void realization() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your string:");
        String string = sc.nextLine();
        System.out.print("Enter first word: ");
        String first = sc.nextLine();
        System.out.print("Enter last word: ");
        String last = sc.nextLine();
        string = StringReverse.reverse(string, first, last);
        System.out.println(string);
    }
}
