package pieces;

import chessboard.ChessboardSquare;

public abstract class Piece extends ChessboardSquare {

    public Piece(String colorIn, String typeIn) {
        super(typeIn);
        color = colorIn;
    }
}
