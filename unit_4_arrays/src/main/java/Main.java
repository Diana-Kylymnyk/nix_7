import chessboard.Chessboard;
import chessboard.ChessboardSquare;
import move.PlayerMoveWithInputCheck;

import java.util.Objects;

public class Main extends Chessboard {
    public static void main(String[] args) {

        System.out.println("\t\t   ====== CHESS GAME ======");
        System.out.println(" (If you want to finish the game enter 'exit')");

        PlayerMoveWithInputCheck whitePly = new PlayerMoveWithInputCheck("white");
        PlayerMoveWithInputCheck blackPly = new PlayerMoveWithInputCheck("black");

        setupBoard();

        while (true) {

            for (int runNum = 1; runNum <= 2; runNum++) {
                drawBoard();

                int[][] move;

                while (true) {

                    if (runNum == 1) {
                        move = whitePly.getPlayerMoveAndCheckInputData();
                    } else {
                        move = blackPly.getPlayerMoveAndCheckInputData();
                    }

                    if (move == null) {
                        return;
                    } else if (move[0][0] == -1) {
                        System.out.println("Invalid location. Try again.");
                        continue;
                    }

                    int[] moveFrom = move[0];
                    int[] moveTo = move[1];
                    ChessboardSquare fromSquare = board[moveFrom[1]][moveFrom[0]];

                    boolean checkValue;
                    if (runNum == 1) {
                        checkValue = fromSquare.checkMove(moveFrom, moveTo, "white", false);
                    } else {
                        checkValue = fromSquare.checkMove(moveFrom, moveTo, "black", false);
                    }

                    if (checkValue) {
                        updateBoard(moveFrom, moveTo);

                        if (runNum == 1) {
                            if (Objects.equals(checkForCheckOrMate("white"), "check")) {
                                System.out.println("Check!");
                            }
                        } else {
                            if (Objects.equals(checkForCheckOrMate("black"), "check")) {
                                System.out.println("Check!");
                            }
                        }
                        break;
                    }
                    System.out.println("Invalid move. Try again.");
                }
            }
        }
    }
}
