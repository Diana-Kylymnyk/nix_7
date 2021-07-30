package realizationClasses;

import methods.StringReverse;

import java.util.Scanner;

public class ReverseBySymbols {

    public static void realization() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your string:");
        String string = sc.nextLine();
        System.out.print("Enter first symbol: ");
        String first = sc.nextLine();
        System.out.print("Enter last symbol: ");
        String last = sc.next();
        string = StringReverse.reverse(string, first.charAt(0), last.charAt(0));
        System.out.println(string);
    }
}
