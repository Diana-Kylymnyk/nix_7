package pieces;

import chessboard.Chessboard;
import chessboard.ChessboardSquare;

public class King extends Piece {

    public King(String colorIn) {
        super(colorIn, "king");

        if (this.color.equals("white")) {
            symbol = "wKi";
        } else {
            symbol = "bKi";
        }
    }

    @Override
    public boolean checkMove(int[] moveFrom, int[] moveTo, String plyColor, boolean testKing) {
        int moveFromX = moveFrom[0];
        int moveFromY = moveFrom[1];
        int moveToX = moveTo[0];
        int moveToY = moveTo[1];

        ChessboardSquare toSquare = Chessboard.board[moveToY][moveToX];

        for (int moveAwayX = -1; moveAwayX <= 1; moveAwayX++) {
            for (int moveAwayY = -1; moveAwayY <= 1; moveAwayY++) {
                if (moveToX == moveFromX + moveAwayX && moveToY == moveFromY + moveAwayY) {
                    if ((!toSquare.getType().equals("blank")) && (!toSquare.getColor().equals(plyColor))) {
                        return true;
                    } else if (toSquare.getType().equals("blank")) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
