package service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class CheckFormatTest {

    @Test
    public void checkFormatTest() {
        Assertions.assertTrue(CheckFormat.firstFormat("//12"));
        Assertions.assertTrue(CheckFormat.firstFormat("/05/47 00:24:00:000"));
        Assertions.assertTrue(CheckFormat.firstFormat("/02/"));
        Assertions.assertTrue(CheckFormat.firstFormat("01/08/1233 00:45"));
        Assertions.assertFalse(CheckFormat.firstFormat("12-3-12"));

        Assertions.assertTrue(CheckFormat.secondFormat("02/8/1233"));
        Assertions.assertFalse(CheckFormat.secondFormat("32/8/1233"));
        Assertions.assertTrue(CheckFormat.thirdFormat("April 2 2012"));
        Assertions.assertTrue(CheckFormat.thirdFormat("April-2- 12:"));
        Assertions.assertTrue(CheckFormat.thirdFormat("April-2-2012 12:12"));
        Assertions.assertTrue(CheckFormat.fourthFormat("02 April 2012"));
        Assertions.assertTrue(CheckFormat.thirdFormat("  2012 12:12"));
        Assertions.assertTrue(CheckFormat.fourthFormat("  2012 12:12"));

        System.out.println(ConvertStringToDate.thirdFormat("  1234 12:12"));
        System.out.println(ConvertStringToDate.fourthFormat("  1234 12:12"));
        System.out.println(ConvertStringToDate.thirdFormat("April 3 1234 12:12"));
        System.out.println(ConvertStringToDate.fourthFormat("03 April 1234 12:12"));
    }
}
