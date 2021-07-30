package methods;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringReverse {

    public static String reverse(String src) {
        StringBuilder reversed = new StringBuilder();
        String[] words = src.split(" ");

        for (String word : words) {
            StringBuilder reverseWord = new StringBuilder();
            for (int i = word.length() - 1; i >= 0; i--) {
                reverseWord.append(word.charAt(i));
            }
            reversed.append(reverseWord).append(' ');
        }
        reversed.setLength(reversed.length() - 1);
        return reversed.toString();
    }

    public static String reverse(String src, String dest) {
        Pattern p = Pattern.compile(dest);
        Matcher m = p.matcher(src);
        String word = reverse(dest);
        return m.replaceAll(word);
    }

    public static String reverse(String src, int firstIndex, int lastIndex) {
        String substring = src.substring(firstIndex, lastIndex + 1);
        substring = reverse(substring);
        StringBuilder reversed = new StringBuilder(src);
        reversed.replace(firstIndex, lastIndex + 1, substring);
        return reversed.toString();
    }

    public static String reverse(String src, char firstSymbol, char lastSymbol) {
        int firstIndex = src.indexOf(firstSymbol);
        int lastIndex = src.indexOf(lastSymbol, firstIndex);
        String reversed = src;
        if (lastIndex != -1) {
            reversed = reverse(src, firstIndex, lastIndex);
        }
        return reversed;
    }

    public static String reverse(String src, String firstString, String lastString) {
        int firstIndex = src.indexOf(firstString);
        int lastIndex = src.indexOf(lastString, firstIndex) + lastString.length() - 1;
        return reverse(src, firstIndex, lastIndex);
    }
}
