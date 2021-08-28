package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class MathSet<Num extends Number & Comparable<Num>> {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    private Num[] numbs;
    private int size;
    private int capacity;

    public MathSet() {
        capacity = 10;
        numbs = (Num[]) new Number[capacity];
        size = 0;
    }

    public MathSet(int capacity) {
        numbs = (Num[]) new Number[capacity];
        this.capacity = capacity;
        size = 0;
    }

    public MathSet(Num[] numbers) {
        capacity = 10;
        numbs = deleteDuplication(numbers);
        size = numbers.length;
    }

    public MathSet(Num[]... numbers) {
        capacity = 10;
        numbs = (Num[]) new Number[capacity];
        for (Num[] number : numbers) {
            add(number);
        }
    }

    public MathSet(MathSet numbers) {
        capacity = numbers.capacity;
        numbs = (Num[]) new Number[capacity];
        for (int i = 0; i < numbers.size; i++) {
            add((Num) numbers.numbs[i]);
        }
        size = numbers.size;
    }

    public MathSet(MathSet... numbers) {
        capacity = 10;
        numbs = (Num[]) new Number[capacity];
        for (MathSet<Num> number : numbers) {
            for (int i = 0; i < number.size; i++) {
                add(number.numbs[i]);
            }
        }
    }

    public int size() {
        return size;
    }

    public void add(Num n) {
        if (findElement(n) >= 0) {
            return;
        }
        if (size == capacity) {
            increaseCapacity();
        }
        numbs[size++] = n;
    }

    public int findElement(Num element) {
        LOGGER_INFO.info("Find element");
        if (numbs != null) {
            for (int i = 0; i < size; i++) {
                if (element.equals(numbs[i])) {
                    return i;
                }
            }
            return -1;
        }
        LOGGER_WARN.warn("numbs == null");
        return 0;
    }

    private void increaseCapacity() {
        LOGGER_INFO.info("Increase capacity");
        Num[] temp = (Num[]) new Number[capacity * 2];
        System.arraycopy(numbs, 0, temp, 0, capacity);
        capacity = capacity * 2;
        numbs = temp;
    }

    public void add(Num... n) {
        LOGGER_INFO.info("Add Num... n");
        for (Num number : n) {
            add(number);
        }
    }

    public Num[] deleteDuplication(Num[] numbers) {
        LOGGER_INFO.info("Delete duplication");
        int n = numbers.length;
        for (int i = 0, m = 0; i != n; i++, n = m) {
            for (int j = m = i + 1; j != n; j++) {
                if (numbers[j] != numbers[i]) {
                    if (m != j) numbers[m] = numbers[j];
                    m++;
                }
            }
        }
        if (n != numbers.length) {
            Num[] b = (Num[]) new Number[n];
            System.arraycopy(numbers, 0, b, 0, n);
            numbers = b;
        }
        return numbers;
    }

    public void join(MathSet ms) {
        LOGGER_INFO.info("Join MathSet ms");
        for (int i = 0; i < ms.size; i++) {
            add((Num) ms.numbs[i]);
        }
        sortAsc();
    }

    public void join(MathSet... ms) {
        LOGGER_INFO.info("Join MathSet... ms");
        for (MathSet number : ms) {
            for (int i = 0; i < number.size; i++) {
                add((Num) number.numbs[i]);
            }
        }
        sortAsc();
    }

    public void intersection(MathSet ms) {
        LOGGER_INFO.info("Intersection MathSet ms");
        MathSet<Num> res = new MathSet<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < ms.size; j++) {
                if (Objects.equals(this.numbs[i], ms.numbs[j])) {
                    res.add((Num) ms.numbs[j]);
                }
            }
        }
        clear();
        join(new MathSet(res));
    }

    public void intersection(MathSet... ms) {
        LOGGER_INFO.info("Intersection MathSet... ms");
        for (MathSet mathSet : ms) {
            intersection(mathSet);
        }
    }

    public void sortDesc() {
        LOGGER_INFO.info("Descending sorting the whole set");
        sortDesc(0, size);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        LOGGER_INFO.info("Descending sorting between two indexes");
        if ((lastIndex - firstIndex) > 0) {
            for (int i = firstIndex; i < lastIndex - 1; i++) {
                for (int k = i + 1; k < lastIndex; k++) {
                    if ((numbs[i].compareTo(numbs[k]) < 0)) {
                        Num temp = numbs[i];
                        numbs[i] = numbs[k];
                        numbs[k] = temp;
                    }
                }
            }
        }
    }

    int getIndex(Num value) {
        LOGGER_INFO.info("Getting index");
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (numbs[i].equals(value)) {
                index = i;
            }
        }
        return index;
    }

    public void sortDesc(Num value) {
        LOGGER_INFO.info("Descending sorting from element");
        if (getIndex(value) != -1) {
            sortDesc(getIndex(value), size);
        }
    }

    public void sortAsc() {
        LOGGER_INFO.info("Ascending sorting the whole set");
        sortAsc(0, size);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        LOGGER_INFO.info("Ascending sorting between two indexes");
        if ((lastIndex - firstIndex) > 0) {
            for (int i = firstIndex; i < lastIndex - 1; i++) {
                for (int k = i + 1; k < lastIndex; k++) {
                    if (numbs[i].compareTo(numbs[k]) > 0) {
                        Num temp = numbs[i];
                        numbs[i] = numbs[k];
                        numbs[k] = temp;
                    }
                }
            }
        }
    }

    public void sortAsc(Num value) {
        LOGGER_INFO.info("Ascending sorting from element");
        if (getIndex(value) != -1) {
            sortAsc(getIndex(value), size);
        }
    }

    public Num get(int index) {
        LOGGER_INFO.info("Getting element by index");
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Incorrect index");
        }
        return numbs[index];
    }

    public Num getMax() {
        LOGGER_INFO.info("Getting max value");
        Num max = numbs[0];
        for (int i = 0; i < size; i++) {
            if (numbs[i].compareTo(max) > 0) {
                max = numbs[i];
            }
        }
        return max;
    }

    public Num getMin() {
        LOGGER_INFO.info("Getting min value");
        Num min = numbs[0];
        for (int i = 0; i < size; i++) {
            if (numbs[i].compareTo(min) < 0) {
                min = numbs[i];
            }
        }
        return min;
    }

    public Number getAverage() {
        LOGGER_INFO.info("Getting average");
        double sum = 0d;
        for (int i = 0; i < size; i++) {
            sum += numbs[i].doubleValue();
        }
        return sum / size;
    }

    public Number getMedian() {
        LOGGER_INFO.info("Getting median");
        if (size != 0) {
            sortAsc();
            if (size % 2 != 0) {
                return numbs[size / 2];
            } else {
                Double firstValue = numbs[size / 2].doubleValue();
                Double secondValue = numbs[size / 2 - 1].doubleValue();
                return (firstValue + secondValue) / 2;
            }
        } else {
            LOGGER_WARN.warn("size == 0");
            return null;
        }
    }

    public Num[] toArray() {
        LOGGER_INFO.info("Method toArray()");
        return numbs;
    }

    public Num[] toArray(int firstIndex, int lastIndex) {
        LOGGER_INFO.info("Method toArray() between two indexes");
        Num[] newArray = (Num[]) new Number[(firstIndex - lastIndex) + 1];
        System.arraycopy(numbs, firstIndex, newArray, 0, firstIndex - lastIndex + 1);
        return newArray;
    }

    public MathSet cut(int firstIndex, int lastIndex) {
        LOGGER_INFO.info("Cut from set");
        MathSet<Num> mathSet = new MathSet<>();
        for (int i = 0; i < capacity; i++) {
            if (i >= firstIndex && i <= lastIndex) mathSet.add(numbs[i]);
        }
        return mathSet;
    }

    public void clear() {
        LOGGER_INFO.info("Clear full set");
        for (int i = 0; i < size; i++) {
            numbs[i] = null;
        }
        size = 0;
    }

    public void clear(Num[] numbers) {
        LOGGER_INFO.info("Clear numbers from set");
        for (Num number : numbers) {
            for (int i = 0; i < size; i++) {
                if (number.equals(numbs[i])) {
                    for (int k = i; k < size; k++) {
                        numbs[k] = numbs[k + 1];
                        numbs[k + 1] = null;
                    }
                    size--;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (numbs[i] != null)
                result.append(" ").append(numbs[i]);
        }
        return "MathSet:" +
                "\n" + "numbers =" + result +
                "\n" + "size = " + size;
    }
}
