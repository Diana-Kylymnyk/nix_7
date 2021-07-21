package tasks;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Task_2 {
    public static void countOfCharacters(String string) {
        Map<Character, Integer> map = new HashMap<>();
        int otherCharacters = 0;
        for (int i = 0; i < string.length(); i++) {
            if (Character.isLetter(string.charAt(i))) {
                Integer n = map.get(string.charAt(i));
                if (n == null) {
                    map.put(string.charAt(i), 1);
                } else {
                    map.put(string.charAt(i), ++n);
                }
            } else {
                otherCharacters++;
            }
        }
        if (otherCharacters == string.length()) {
            System.out.println("There are no letters!\n");
        } else {
            print(map);
        }
    }

    public static void print(Map<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue().toString());
        }
        System.out.print("\n");
    }

    public static void realization() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your string: ");
        String string = sc.nextLine();
        countOfCharacters(string);
    }
}
