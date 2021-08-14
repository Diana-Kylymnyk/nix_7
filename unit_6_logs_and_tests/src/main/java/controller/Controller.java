package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your option");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position);
                position = reader.readLine();
                if (position.equals("0")) {
                    System.exit(0);
                }
                crud(position);
            }
        } catch (IOException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private void runNavigation() {
        System.out.println();
        System.out.println("CRUD_STUDENT - 1");
        System.out.println("CRUD_COURSE - 2");
        System.out.println("EXIT - 0");
        System.out.println();
    }

    private void crud(String position) {
        switch (position) {
            case "1":
                new StudentController().start();
                break;
            case "2":
                new CourseController().start();
                break;
            case "0":
                System.exit(0);
        }
        runNavigation();
    }
}
