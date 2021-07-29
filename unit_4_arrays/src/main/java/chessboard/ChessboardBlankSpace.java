package chessboard;

public class ChessboardBlankSpace extends ChessboardSquare {

    public ChessboardBlankSpace() {
        super("blank");
        symbol = "   ";
        color = null;
    }

    public boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing) {
        return false;
    }
}
