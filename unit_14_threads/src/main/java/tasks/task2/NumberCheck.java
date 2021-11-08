package tasks.task2;

import java.util.List;

public class NumberCheck extends Thread {

    private final List<Integer> list;
    private int count = 0;

    public NumberCheck(List<Integer> numbers) {
        this.list = numbers;
    }

    @Override
    public void run() {
        for (int number : list) {
            if (isPrimeNumber(number)) {
                count++;
            }
        }
    }

    public int countOfPrimes() {
        return count;
    }

    private boolean isPrimeNumber(Integer number) {
        if (number < 2)
            return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0)
                return false;
        }
        return true;
    }
}
