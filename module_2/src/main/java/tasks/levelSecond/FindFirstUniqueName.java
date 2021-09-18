package tasks.levelSecond;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class FindFirstUniqueName {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NAMES_TXT = "files/levelSecond/names.txt";

    public static void realization() {
        System.out.println("\nFind first unique name from list");
        boolean flag = true;
        while (flag) {
            System.out.print("1-Enter names\n" +
                    "2-Take names from file\n" +
                    "0-Exit\n" +
                    "Your choice>>");
            switch (SCANNER.nextLine()) {
                case "1":
                    inputNames();
                    break;
                case "2":
                    readNames();
                    break;
                case "0":
                    flag = false;
            }
        }
    }

    private static void inputNames() {
        ArrayList<String> names = new ArrayList<>();
        System.out.println("Please, enter count of names");
        String count = SCANNER.nextLine();
        System.out.println("Enter names");
        for (int i = 0; i < Integer.parseInt(count); i++) {
            names.add(SCANNER.nextLine());
        }
        ArrayList<String> list = new ArrayList<>(names);
        System.out.println("Find unique name from list: ");
        System.out.println(findFirstUniqueNameInList(list));
        System.out.println();
    }

    public static void readNames() {
        try {
            Path path = Paths.get(NAMES_TXT);
            ArrayList<String> names = (ArrayList<String>) Files.readAllLines(path);
            System.out.println("The list of names from file");
            names.forEach(n -> System.out.print(n + "\n"));
            System.out.println("\nFirst unique name from list: ");
            System.out.println(findFirstUniqueNameInList(names));
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String findFirstUniqueNameInList(List<String> names) {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        for (String name : names) {
            map.compute(name, (key, value) -> (value != null) ? value + 1 : 1);
        }
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            if (e.getValue() == 1) {
                return e.getKey();
            }
        }
        return "There aren't unique names!";
    }
}
