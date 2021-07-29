package pieces;

import chessboard.Chessboard;
import chessboard.ChessboardSquare;

public class Knight extends Piece {

    public Knight(String colorIn) {
        super(colorIn, "knight");

        if (color.equals("white")) {
            symbol = "wKn";
        } else {
            symbol = "bKn";
        }
    }

    @Override
    public boolean checkMove(int[] moveFrom, int[] moveTo, String plyColor, boolean testKing) {
        int moveFromX = moveFrom[0];
        int moveFromY = moveFrom[1];
        int moveToX = moveTo[0];
        int moveToY = moveTo[1];

        ChessboardSquare toSquare = Chessboard.board[moveToY][moveToX];

        if (!testKing) {
            if (toSquare.getType().equals("king")) {
                return false;
            }
        }

        boolean locationPass = false;

        for (int displaceX = -2; displaceX <= 2; displaceX++) {

            if (displaceX != 0) {
                if (moveToX == moveFromX + displaceX) {

                    if (Math.abs(displaceX) == 1) {
                        for (int displaceY = -2; displaceY <= 2; displaceY += 4) {
                            if (moveToY == moveFromY + displaceY) {
                                locationPass = true;
                                break;
                            }
                        }
                    } else {
                        for (int displaceY = -1; displaceY <= 1; displaceY += 2) {
                            if (moveToY == moveFromY + displaceY) {
                                locationPass = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        if (locationPass) {
            return (toSquare.getType().equals("blank")) || (!toSquare.getColor().equals(plyColor));
        }
        return false;
    }
}
