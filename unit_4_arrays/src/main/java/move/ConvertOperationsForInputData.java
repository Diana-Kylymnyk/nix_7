package move;

import chessboard.Chessboard;

public class ConvertOperationsForInputData {

    public static int convertCharToNum(char charIn) {
        int numOut = -1;

        for (int i = 0; i < Chessboard.SIDE_LETTERS.length; i++) {
            if (Chessboard.SIDE_LETTERS[i] == charIn) {
                numOut = i;
            }
        }
        return numOut;
    }

    public static int convertCharNumToNum(char charIn) {
        int numOut = -1;
        int convertedNum = Character.getNumericValue(charIn);

        for (int i : Chessboard.SIDE_NUMBERS) {
            if (i == convertedNum) {
                numOut = convertedNum;
                break;
            }
        }
        return numOut;
    }
}
