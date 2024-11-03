/**
 *  CleverPlayer is kind of a player, he is cleverer than whatever player
 *  the strategy is to pass on the board from the coordinate (0,0), row after row and put
 *  mark if the cell empty in each turn
 */
public class CleverPlayer implements Player {

    /**
     * A CleverPlayer constructor
     */
    public CleverPlayer(){}

    /**
     * The player put a mark in his turn
     * @param board
     * @param mark
     */
    public void playTurn(Board board, Mark mark){
        int row = 0;
        int col = 0;

        /**
         * passing on the board until the player get to an empty cell and mark it
         */
        while (!board.putMark(mark, row, col) && row < board.getSize()) {
            row++;
            if (row == board.getSize()){
                col++;
                row = 0;
            }
        }
    }
}
