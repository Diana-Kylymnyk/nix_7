package util;

import entity.MyDate;
import exceptions.DateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SorterTest {

    private final static MyDate first = new MyDate();
    private final static MyDate second = new MyDate();
    private final static MyDate third = new MyDate();
    private final static ArrayList<MyDate> arrayList = new ArrayList<>();

    @Test
    public void sortDates() throws DateException {
        first.setDateAndTime(2016, 3, 5, 23, 2, 1, 0);
        second.setDateAndTime(1000, 1, 4, 22, 23, 4, 12);
        third.setDateAndTime(1040, 1, 4, 22, 23, 4, 12);
        arrayList.add(first);
        arrayList.add(second);
        arrayList.add(third);

        Sorter.sortDates(arrayList, String.valueOf(1));
        System.out.println(arrayList);
        Assertions.assertEquals(second.getYear(), arrayList.get(2).getYear());

        Sorter.sortDates(arrayList, String.valueOf(2));
        System.out.println(arrayList);
        Assertions.assertEquals(second.getYear(), arrayList.get(0).getYear());
    }
}
