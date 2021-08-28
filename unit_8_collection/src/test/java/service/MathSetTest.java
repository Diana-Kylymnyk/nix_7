package service;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class MathSetTest {

    private static MathSet<Integer> m;

    @Test
    public void additionTest() {
        m = new MathSet<>(new Integer[]{10, 4, 2}, new Integer[]{5, 7, 2});
        MathSet<Integer> m1 = new MathSet<>(m);
        m1.add(9);
        m1.add(78);
        m1.add(10);
        System.out.println(m1);

        MathSet<Integer> m2 = new MathSet<>(m, m1);
        System.out.println(m2);
        Assertions.assertEquals(m1.size(), m2.size());
    }

    @Test
    public void sorting() {
        m = new MathSet<>(new Integer[]{10, 4, 2}, new Integer[]{5, 7, 2});
        System.out.println(m);
        m.sortDesc();
        System.out.println(m);
        Assertions.assertEquals(10, m.get(0));
        m.sortAsc();
        System.out.println(m);
        Assertions.assertEquals(2, m.get(0));
        m.sortDesc(5);
        System.out.println(m);
        Assertions.assertEquals(5, m.get(m.size() - 1));
    }

    @Test
    public void joinTest() {
        m = new MathSet<>(new Integer[]{10, 4, 2}, new Integer[]{5, 7, 2});
        MathSet<Integer> m1 = new MathSet<>(new Integer[]{10, 3, 9});
        MathSet<Integer> m2 = new MathSet<>(new Integer[]{111, 22, 33}, new Integer[]{122, 22, 33});
        m.join(m1, m2);
        System.out.println(m);
        Assertions.assertEquals(11, m.size());
    }

    @Test
    public void intersectionTest() {
        m = new MathSet<>(new Integer[]{10, 4, 2}, new Integer[]{5, 7, 2});
        MathSet<Integer> m1 = new MathSet<>(new Integer[]{10, 3, 9});
        MathSet<Integer> m2 = new MathSet<>(new Integer[]{111, 10, 3}, new Integer[]{122, 13, 33});
        m.intersection(m1, m2);
        System.out.println(m);
        Assertions.assertEquals(1, m.size());
        Assertions.assertEquals(10, m.get(0));
    }

    @Test
    public void averageAndMedianTest() {
        m = new MathSet<>(new Integer[]{10, 4, 2}, new Integer[]{5, 7, 2});
        System.out.println(m.getMedian());
        Assertions.assertEquals(5, m.getMedian());
        System.out.println(m.getAverage());
        Assertions.assertEquals(5.6, m.getAverage());
    }

    @Test
    public void getTest() {
        m = new MathSet<>(new Integer[]{10, 4, 2}, new Integer[]{5, 7, 2});
        Assertions.assertEquals(10, m.getMax());
        Assertions.assertEquals(2, m.getMin());
        Assertions.assertEquals(5, m.get(3));
    }

    @Test
    public void cutAndClearTest() {
        m = new MathSet<>(new Integer[]{10, 4, 2}, new Integer[]{5, 7, 2});
        System.out.println(m.cut(0, 2));
        Assertions.assertEquals(3, m.cut(0, 2).size());
        m.toArray();
        Assertions.assertEquals(5, m.size());
        m.clear(new Integer[]{2, 4});
        System.out.println(m);
        Assertions.assertEquals(3, m.size());
        m.clear();
        Assertions.assertEquals(0, m.size());
    }
}
