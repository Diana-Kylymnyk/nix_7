package tasks.task1;

import java.util.ArrayList;
import java.util.List;

public class PrintHelloFromThreads implements Runnable {

    @Override
    public void run() {
        System.out.println("Hello from thread - " + Thread.currentThread().getName());
    }

    public void realization() {
        List<Thread> threads = new ArrayList<>();
        for (int i = 49; i >= 0; i--) {
            Thread t = new Thread(new PrintHelloFromThreads());
            t.setName(String.valueOf(i));
            threads.add(t);
        }

        for (Thread thread : threads) {
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}