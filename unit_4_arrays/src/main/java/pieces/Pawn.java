package pieces;

import chessboard.Chessboard;
import chessboard.ChessboardSquare;

public class Pawn extends Piece {

    public Pawn(String colorIn) {
        super(colorIn, "pawn");

        if (color.equals("white")) {
            symbol = "wPa";
        } else {
            symbol = "bPa";
        }
    }

    @Override
    public boolean checkMove(int[] moveFrom, int[] moveTo, String plyColor, boolean testKing) {
        int moveFromX = moveFrom[0];
        int moveFromY = moveFrom[1];
        int moveToX = moveTo[0];
        int moveToY = moveTo[1];

        int moveForwardTwo;
        int moveForwardOne;
        int pawnRowOnPlySide;

        ChessboardSquare toSquare = Chessboard.board[moveToY][moveToX];

        if (!testKing) {
            if (toSquare.getType().equals("king")) {
                return false;
            }
        }

        if (plyColor.equals("white")) {
            moveForwardTwo = -2;
            moveForwardOne = -1;
            pawnRowOnPlySide = 6;
        } else {
            moveForwardTwo = 2;
            moveForwardOne = 1;
            pawnRowOnPlySide = 1;
        }

        if (moveToY == moveFromY + moveForwardOne) {
            if ((moveToX == moveFromX - 1) || (moveToX == moveFromX + 1)) {
                return (!toSquare.getType().equals("blank")) && (!toSquare.getColor().equals(plyColor));
            } else return (moveToX == moveFromX) && (toSquare.getType().equals("blank"));
        } else if ((moveToY == moveFromY + moveForwardTwo) && (moveToX == moveFromX)
                && (toSquare.getType().equals("blank"))) {
            return moveFromY == pawnRowOnPlySide;
        }

        return false;
    }
}
