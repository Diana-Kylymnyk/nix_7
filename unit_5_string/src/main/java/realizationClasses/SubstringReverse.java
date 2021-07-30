package realizationClasses;

import methods.StringReverse;

import java.util.Scanner;

public class SubstringReverse {

    public static void realization(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your string:");
        String string = sc.nextLine();
        System.out.println("Enter substring:");
        String substring = sc.nextLine();
        string = StringReverse.reverse(string,substring);
        System.out.println(string);
    }
}
