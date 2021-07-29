package move;

import chessboard.Chessboard;

import java.util.Scanner;

public class PlayerMoveWithInputCheck {

    private final String color;
    private final Scanner scanner = new Scanner(System.in);

    public PlayerMoveWithInputCheck(String colorIn) {
        color = colorIn;
    }

    public int[][] getPlayerMoveAndCheckInputData() {

        int[][] move = new int[2][2];

        for (int runNum = 1; runNum <= 2; runNum++) {
            while (true) {
                if (runNum == 1) {
                    System.out.print(color.toUpperCase() + " MOVE" + ": input location to move from. (Ex: D4) >> ");
                } else {
                    System.out.print(color.toUpperCase() + " MOVE" + ": input location to move to. (Ex: D4) >> ");
                }
                String moveIn = scanner.nextLine().trim();

                if (moveIn.equals("exit")){
                    System.out.println("Exit!");
                    return null;
                }

                if (moveIn.length() == 2 && !(moveIn.contains(" ") || moveIn.contains("\t"))) {

                    if (!Character.isDigit(moveIn.charAt(0)) && Character.isDigit(moveIn.charAt(1))) {
                        int x, y;

                        if ((x = ConvertOperationsForInputData.convertCharToNum(Character.toUpperCase(moveIn.charAt(0)))) != -1) {
                            if ((y = ConvertOperationsForInputData.convertCharNumToNum(moveIn.charAt(1))) != -1) {
                                y = 8 - y;
                                int[] tempArray = {x, y};
                                if (runNum == 1) {
                                    if (Chessboard.board[y][x].getType().equals("blank") || !Chessboard.board[y][x].getColor().equals(color)) {
                                        tempArray[0] = -1;
                                        tempArray[1] = -1;
                                        return new int[][]{tempArray, tempArray};
                                    }
                                }

                                move[runNum - 1] = tempArray;
                                break;
                            }
                        }
                    }
                }
                System.out.println("Invalid location. Try again.");
            }
        }
        return move;
    }
}
