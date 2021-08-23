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
        Assertions.assertTrue(CheckFormat.fourthFormat("02 April 2012"));
    }
}
