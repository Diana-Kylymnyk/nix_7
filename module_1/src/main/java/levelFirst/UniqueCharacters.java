package levelFirst;

import java.util.*;

public class UniqueCharacters {

    public static void createSetAndPrint(List<String> array){
        Set<String> set = new HashSet<>(array);
        System.out.println("Input array: " + array);
        System.out.println("Unique elements: " + set);
        System.out.println("Count of unique elements = " + set.size());
    }

    public static void checkInput(Scanner sc){
        if (!sc.hasNextInt()) {
            System.out.print("This is not a number! Try again >> ");
            sc.next();
        }
    }

    public static void userInputAndRealization() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements in array: ");
        checkInput(sc);
        int n = sc.nextInt();
        List<String> arr = new ArrayList<>();
        System.out.println("Enter elements: ");
        for (int i = 0; i < n; i++) {
            arr.add(sc.next());
        }
        createSetAndPrint(arr);
    }
}
