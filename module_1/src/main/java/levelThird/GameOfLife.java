package levelThird;

import java.util.Random;

public class GameOfLife {

    public static GameOfLife currentGame;
    int height;
    int width;
    int[][] board;

    public GameOfLife(int height, int width) {
        this.board = new int[height][width];
        this.height = height;
        this.width = width;
    }

    public GameOfLife(int[][] boardPreset) {
        this.board = boardPreset;
        this.height = boardPreset.length;
        this.width = boardPreset[0].length;
    }

    public void fillRandom() {
        Random rand = new Random();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.board[y][x] = rand.nextInt(2);
            }
        }
    }

    public int checkStatus(int x, int y) {
        if (x >= width || x < 0) {
            return 0;
        }
        if (y >= height || y < 0) {
            return 0;
        }
        return this.board[y][x];
    }

    public int countAliveNeighbors(int x, int y) {
        int num = 0;

        num += checkStatus(x - 1, y - 1);
        num += checkStatus(x, y - 1);
        num += checkStatus(x + 1, y - 1);

        num += checkStatus(x - 1, y);
        num += checkStatus(x + 1, y);

        num += checkStatus(x - 1, y + 1);
        num += checkStatus(x, y + 1);
        num += checkStatus(x + 1, y + 1);

        return num;
    }

    public void nextState() {
        int[][] tempBoard = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int numAliveNeighbors = countAliveNeighbors(x, y);
                if (this.board[y][x] == 1) {
                    if (numAliveNeighbors < 2) {
                        tempBoard[y][x] = 0;
                    } else if (numAliveNeighbors == 2 || numAliveNeighbors == 3) {
                        tempBoard[y][x] = 1;
                    } else {
                        tempBoard[y][x] = 0;
                    }
                } else {
                    if (numAliveNeighbors == 3) {
                        tempBoard[y][x] = 1;
                    }
                }
            }
        }
        this.board = tempBoard;
    }

    public void printBoard() {
        System.out.print(" ");
        for (int y = 0; y < width; y++) {
            System.out.print("---");
        }
        System.out.println();

        for (int y = 0; y < height; y++) {
            System.out.print("|");
            for (int x = 0; x < width; x++) {
                System.out.print(" " + this.board[y][x] + " ");
            }
            System.out.println("|");
        }
        System.out.print(" ");

        for (int y = 0; y < width; y++) {
            System.out.print("---");
        }
        System.out.println();
    }
}