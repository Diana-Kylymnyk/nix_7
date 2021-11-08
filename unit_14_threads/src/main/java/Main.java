import tasks.task1.PrintHelloFromThreads;
import tasks.task2.CountPrimesInThreads;

public class Main {

    public static void main(String[] args) {
        System.out.println("First task");
        PrintHelloFromThreads printHello = new PrintHelloFromThreads();
        printHello.realization();

        System.out.println("\nSecond task");
        CountPrimesInThreads countPrimes = new CountPrimesInThreads();
        countPrimes.countInTwoThreads();
    }
}