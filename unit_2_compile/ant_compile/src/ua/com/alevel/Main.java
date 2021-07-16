package ua.com.alevel;

import ua.com.alevel.time.CurrentTime;
import ua.com.alevel.greeting.Hello;

public class Main {
    public static void main(String[] args) {
        new Hello().print();
        new CurrentTime().now();
    }
}