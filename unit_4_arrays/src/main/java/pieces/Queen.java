package pieces;

import chessboard.Chessboard;
import chessboard.ChessboardSquare;

import java.util.Objects;

public class Queen extends Piece {

    public Queen(String colorIn) {
        super(colorIn, "queen");

        if (Objects.equals(color, "white")) {
            symbol = "wQu";
        } else {
            symbol = "bQu";
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
        String type;

        if (!testKing) {
            if (Objects.equals(toSquare.getType(), "king")) {
                return false;
            }
        }

        if (moveToY == moveFromY) {
            if (moveToX > moveFromX) {
                direction = "rite";
            } else {
                direction = "left";
            }
            type = "straight";
        } else if (moveToX == moveFromX) {
            if (moveToY > moveFromY) {
                direction = "bot";
            } else {
                direction = "top";
            }
            type = "straight";
        } else if (moveToX > moveFromX) {
            if (moveToY < moveFromY) {
                direction = "topRite";
            } else {
                direction = "botRite";
            }
            type = "diagonal";
        } else {
            if (moveToY < moveFromY) {
                direction = "topLeft";
            } else {
                direction = "botLeft";
            }
            type = "diagonal";
        }

        ChessboardSquare testSquare;

        if (Objects.equals(type, "diagonal")) {
            int moveDistance = Math.abs(moveToX - moveFromX);

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
        } else {
            if ((Objects.equals(direction, "rite")) || (Objects.equals(direction, "left"))) {
                int displaceMax = Math.abs(moveToX - moveFromX);

                for (int displace = 1; displace <= displaceMax; displace++) {
                    if (Objects.equals(direction, "rite")) {
                        testSquare = Chessboard.board[moveFromY][moveFromX + displace];

                    } else {
                        testSquare = Chessboard.board[moveFromY][moveFromX - displace];

                    }
                    if ((!Objects.equals(testSquare.getType(), "blank")) && (displace != displaceMax)) {
                        return false;
                    } else if ((displace == displaceMax) && ((Objects.equals(testSquare.getType(), "blank")) || (!Objects.equals(testSquare.getColor(), plyColor)))) {
                        return true;
                    }
                }
            } else {
                int displaceMax = Math.abs(moveToY - moveFromY);
                for (int displace = 1; displace <= displaceMax; displace++) {

                    if (Objects.equals(direction, "top")) {
                        testSquare = Chessboard.board[moveFromY - displace][moveFromX];

                    } else {
                        testSquare = Chessboard.board[moveFromY + displace][moveFromX];

                    }
                    if ((!Objects.equals(testSquare.getType(), "blank")) && (displace != displaceMax)) {
                        return false;
                    } else if ((displace == displaceMax) && ((Objects.equals(testSquare.getType(), "blank")) || (!Objects.equals(testSquare.getColor(), plyColor)))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}


