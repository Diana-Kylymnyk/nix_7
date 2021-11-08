package tasks.task2;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountPrimesInThreads {

    public void countInTwoThreads() {
        List<Integer> listOfNumbers = Stream
                .of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
                .collect(Collectors.toList());

        NumberCheck firstPart = new NumberCheck(listOfNumbers.subList(0, listOfNumbers.size() / 2));
        NumberCheck secondPartOfList = new NumberCheck(listOfNumbers.subList(listOfNumbers.size() / 2, listOfNumbers.size()));

        Thread firstThread = new Thread(firstPart);
        Thread secondThread = new Thread(secondPartOfList);

        firstThread.start();
        secondThread.start();
        try {
            firstThread.join();
            secondThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("First thread: " + firstPart.countOfPrimes());
        System.out.println("Second thread: " + secondPartOfList.countOfPrimes());
        int res = firstPart.countOfPrimes() + secondPartOfList.countOfPrimes();
        System.out.println("Res: " + res);
    }
}
