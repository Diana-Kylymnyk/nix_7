package levelFirst;

import java.util.Scanner;

public class KnightMove {

    public static void checkKnightMove(int startX, int startY, int endX, int endY) {
        int absX = Math.abs(startX - endX);
        int absY = Math.abs(startY - endY);
        if (((absX == 1 && absY == 2)) || ((absX == 2) && (absY == 1))) {
            System.out.println("Can move to this cell");
        } else {
            System.out.println("Can NOT move to this cell");
        }
    }

    public static void checkInput(Scanner sc){
        if (!sc.hasNextInt()) {
            System.out.print("This is not a number! Try again >> ");
            sc.next();
        }
    }

    public static void userInputAndRealization() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter start position XY");
        System.out.print("X: ");
        checkInput(sc);
        int startX = sc.nextInt();
        System.out.print("Y: ");
        checkInput(sc);
        int startY = sc.nextInt();

        System.out.println("Enter end position XY");
        System.out.print("X: ");
        checkInput(sc);
        int endX = sc.nextInt();
        System.out.print("Y: ");
        checkInput(sc);
        int endY = sc.nextInt();

        checkKnightMove(startX,startY,endX,endY);
    }
}
