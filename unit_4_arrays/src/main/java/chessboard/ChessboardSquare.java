package chessboard;

public abstract class ChessboardSquare {

    protected String symbol;
    public String color;
    public String type;

    public ChessboardSquare(String typeIn){
        type = typeIn;
    }

    String getSymbol(){
        return symbol;
    }
    public String getColor(){
        return color;
    }
    public String getType(){
        return type;
    }

    public abstract boolean checkMove(int[] moveFromReq, int[] moveToReq, String plyColor, boolean testKing);
}
