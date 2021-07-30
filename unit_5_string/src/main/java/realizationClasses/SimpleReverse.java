package realizationClasses;

import methods.StringReverse;

import java.util.Scanner;

public class SimpleReverse {

    public static void realization(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your string:");
        String string = sc.nextLine();
        string = StringReverse.reverse(string);
        System.out.println(string);
    }
}
