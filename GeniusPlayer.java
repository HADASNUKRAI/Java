/**
 * GeniusPlayer is kind of a player, he is smarter than clever player
 * the strategy is to pass on the board from the coordinate (0,1), row after row and put
 * mark if the cell empty in each turn, like that the GeniusPlayer is block the
 * other player
 */

public class GeniusPlayer implements Player {
    public void playTurn(Board board, Mark mark) {
        int row = 1;
        int col = 0;

        /**
         * if the coordinate cell in the board is not empty so the player
         * forward to the next coordinate
         */
        while (!board.putMark(mark, row, col) && row < board.getSize()) {
            col++;
            if (col == board.getSize()) {
                col = 0;
                row++;
            }
        }
    }
}
