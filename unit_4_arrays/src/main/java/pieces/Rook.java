package pieces;

import chessboard.Chessboard;
import chessboard.ChessboardSquare;

public class Rook extends Piece {

    public Rook(String colorIn) {
        super(colorIn, "rook");

        if (color.equals("white")) {
            symbol = "wRo";
        } else {
            symbol = "bRo";
        }
    }

    @Override
    public boolean checkMove(int[] moveFrom, int[] moveTo, String plyColor, boolean testKing) {
        int moveFromX = moveFrom[0];
        int moveFromY = moveFrom[1];
        int moveToX = moveTo[0];
        int moveToY = moveTo[1];

        ChessboardSquare toSquare = Chessboard.board[moveToY][moveToX];

        String direction;

        if (!testKing) {
            if (toSquare.getType().equals("king")) {
                return false;
            }
        }

        if (moveToY == moveFromY) {
            if (moveToX > moveFromX) {
                direction = "rite";
            } else {
                direction = "left";
            }
        } else if (moveToX == moveFromX) {
            if (moveToY > moveFromY) {
                direction = "bot";
            } else {
                direction = "top";
            }
        } else {
            return false;
        }

        ChessboardSquare testSquare;

        if ((direction.equals("rite")) || (direction.equals("left"))) {
            int displaceMax = Math.abs(moveToX - moveFromX);

            for (int displace = 1; displace <= displaceMax; displace++) {
                if (direction.equals("rite")) {
                    testSquare = Chessboard.board[moveFromY][moveFromX + displace];
                } else {
                    testSquare = Chessboard.board[moveFromY][moveFromX - displace];
                }

                if ((!testSquare.getType().equals("blank")) && (displace != displaceMax)) {
                    return false;
                } else if ((displace == displaceMax) && ((testSquare.getType().equals("blank")) || (!testSquare.getColor().equals(plyColor)))) {
                    return true;
                }
            }
        } else {
            int displaceMax = Math.abs(moveToY - moveFromY);
            for (int displace = 1; displace <= displaceMax; displace++) {

                if (direction.equals("top")) {
                    testSquare = Chessboard.board[moveFromY - displace][moveFromX];
                } else {
                    testSquare = Chessboard.board[moveFromY + displace][moveFromX];
                }

                if ((!testSquare.getType().equals("blank")) && (displace != displaceMax)) {
                    return false;
                } else if ((displace == displaceMax) && ((testSquare.getType().equals("blank")) || (!testSquare.getColor().equals(plyColor)))) {
                    return true;
                }
            }
        }
        return false;
    }
}
