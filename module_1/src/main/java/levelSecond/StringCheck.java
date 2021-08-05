package levelSecond;

import java.util.Scanner;
import java.util.Stack;

public class StringCheck {

    private static boolean isInArray(char symbol, char[] arr) {
        if (arr == null)
            throw new IllegalArgumentException();

        for (char c : arr) {
            if (c == symbol)
                return true;
        }
        return false;
    }

    private static int indexOf(char symbol, char[] arr) {
        if (arr == null)
            throw new IllegalArgumentException();

        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == symbol)
                index = i;
        }
        return index;
    }

    public static boolean checkBrackets(String string) {
        if (string == null)
            throw new IllegalArgumentException();

        Stack<Character> stack = new Stack<>();
        char[] openBrackets = new char[]{'{', '[', '('};
        char[] closeBrackets = new char[]{'}', ']', ')'};

        for (int i = 0; i < string.length(); i++) {
            char symbol = string.charAt(i);
            if (isInArray(symbol, openBrackets)) {
                stack.push(symbol);
            } else {
                int index = indexOf(symbol, closeBrackets);
                if (index != -1) {
                    if (stack.empty()) {
                        return false;
                    } else if (stack.pop() != openBrackets[index]) {
                        return false;
                    }
                }
            }
        }
        return stack.empty();
    }

    public static void userInputAndRealization() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter string: ");
        String string = sc.nextLine();
        if (checkBrackets(string)) {
            System.out.println("This string is valid");
        } else {
            System.out.println("This string is invalid");
        }
    }
}
