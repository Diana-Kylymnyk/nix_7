package levelFirst;

import java.awt.*;
import java.util.Scanner;

public class AreaOfTriangle {

    private final Point a;
    private final Point b;
    private final Point c;

    public AreaOfTriangle(Point[] arr) {
        this.a = arr[0];
        this.b = arr[1];
        this.c = arr[2];
    }

    double distanceBetweenTwoPoint(Point a, Point b) {
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    double getArea() {
        double ab = distanceBetweenTwoPoint(a, b);
        double bc = distanceBetweenTwoPoint(c, b);
        double ca = distanceBetweenTwoPoint(a, c);
        double s = (ab + bc + ca) / 2;
        return Math.sqrt(s * (s - ab) * (s - bc) * (s - ca));
    }

    public static void checkInput(Scanner sc){
        if (!sc.hasNextInt()) {
            System.out.print("This is not a number! Try again >> ");
            sc.next();
        }
    }

    public static void userInputAndRealization() {
        Scanner sc = new Scanner(System.in);
        Point[] points = new Point[3];

        for (int i = 0; i < 3; i++) {
            System.out.println("Point " + (i + 1));
            System.out.print("Enter x: ");
            checkInput(sc);
            int x = sc.nextInt();
            System.out.print("Enter y: ");
            checkInput(sc);
            int y = sc.nextInt();
            Point point = new Point(x, y);
            points[i] = point;
        }

        AreaOfTriangle triangle = new AreaOfTriangle(points);
        String formattedDouble = String.format("%.1f", triangle.getArea());
        System.out.println("Area of triangle = " + formattedDouble);
    }
}
