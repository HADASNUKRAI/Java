/**
 * Board class initialize the board and put the mark
 */

public class Board {
    private static final int SIZE = 4; //size of the board
    private Mark board[][];

    /**
     * A Board constructor
     */
    public Board() {
        board = new Mark[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = Mark.BLANK;
            }
        }
    }

    /**
     *  A constructor with a given size
     * @param size given size of the board
     */
    public Board(int size) {
        board = new Mark[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Mark.BLANK;
            }
        }
    }


    /**
     * @return the size of the board
     */
    public int getSize() {
        return board.length;
    }


    /**
     * @param mark
     * @param row  coordinate
     * @param col  coordinate
     * @return true if the board marked in the given coordinates otherwise false
     */
    public boolean putMark(Mark mark, int row, int col) {
        /**
         * Checking if the coordinates out of bounds
         */
        if (row >= getSize() || row < 0 || col >= getSize() || col < 0) {
            System.out.println("Invalid mark position, please choose a different position.");
            return false;
        }

        if (board[row][col] != Mark.BLANK) {
            System.out.println("Mark position is already occupied.");
            return false;
        }
        board[row][col] = mark;
        return true;
    }


    /**
     * @param x coordinate
     * @param y coordinate
     * @return the mark in the given coordinates
     */
    public Mark getMark(int x, int y) {
        if (x >= getSize() || x < 0 || y >= getSize() || y < 0) {
            return Mark.BLANK;
        }
        return board[x][y];
    }
}






