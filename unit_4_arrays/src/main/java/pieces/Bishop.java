package pieces;

import chessboard.Chessboard;
import chessboard.ChessboardSquare;

import java.util.Objects;

public class Bishop extends Piece {

    public Bishop(String colorIn) {
        super(colorIn, "bishop");

        if (Objects.equals(color, "white")) {
            symbol = "wBi";
        } else {
            symbol = "bBi";
        }
    }

    @Override
    public boolean checkMove(int[] moveFrom, int[] moveTo, String plyColor, boolean testKing) {
        int moveFromX = moveFrom[0];
        int moveFromY = moveFrom[1];
        int moveToX = moveTo[0];
        int moveToY = moveTo[1];

        ChessboardSquare toSquare = Chessboard.board[moveToY][moveToX];

        int moveDistance = Math.abs(moveToX - moveFromX);

        if (!testKing) {
            if (Objects.equals(toSquare.getType(), "king")) {
                return false;
            }
        }

        String direction;

        if (moveToX > moveFromX) {
            if (moveToY < moveFromY) {
                direction = "topRite";
            } else {
                direction = "botRite";
            }
        } else {
            if (moveToY < moveFromY) {
                direction = "topLeft";
            } else {
                direction = "botLeft";
            }
        }

        ChessboardSquare testSquare;
        for (int moveAway = 1; moveAway <= moveDistance; moveAway++) {

            switch (direction) {
                case "topRite":
                    testSquare = Chessboard.board[moveFromY - moveAway][moveFromX + moveAway];
                    break;
                case "botRite":
                    testSquare = Chessboard.board[moveFromY + moveAway][moveFromX + moveAway];
                    break;
                case "topLeft":
                    testSquare = Chessboard.board[moveFromY - moveAway][moveFromX - moveAway];
                    break;
                default:
                    testSquare = Chessboard.board[moveFromY + moveAway][moveFromX - moveAway];
                    break;
            }

            if ((!Objects.equals(testSquare.getType(), "blank")) && (moveAway != moveDistance)) {
                return false;
            } else if ((moveAway == moveDistance) && ((!Objects.equals(testSquare.getColor(), plyColor)) || (Objects.equals(testSquare.getType(), "blank")))) {
                return true;
            }
        }
        return false;
    }
}