/**
 * Checking if the game ended, who is the winner and if the game
 * ended with draw
 */
public class Game {
    private final Player playerX;//player 1
    private final Player playerO;//player 2
    private Renderer renderer;//renderer word
    private Board board;
    private int winStreak;//the number of the streak of winning
    private final int WIN_STREAK = 2;//the minimum streak
    private int draw_counter = 0; //checking var for draw
    private static int counter = 0;//counter the games rounds
    private Mark checking = Mark.BLANK;//keeping the winner value


    /**
     * A constructor with a given 2 players and renderer
     * @param playerX one player
     * @param playerO second player
     * @param renderer by this value we renderer the board
     */
    public Game(Player playerX, Player playerO, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
    }

    /**
     * A constructor with a given players
     * @param playerX one player
     * @param playerO second player
     * @param size the size of the board
     * @param winStreak the win streak
     * @param renderer by this value we renderer the board
     */
    public Game(Player playerX, Player playerO, int size, int winStreak, Renderer renderer) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.renderer = renderer;
        board = new Board(size);
        if (winStreak > size || winStreak < WIN_STREAK) {
            this.winStreak = size;
        } else {
            this.winStreak = winStreak;
        }
    }

    /**
     * @return the WinStreak
     */
    public int getWinStreak() {
        return winStreak;
    }


    /**
     * @return the size of the board
     */
    public int getBoardSize() {
        return board.getSize();
    }


    /**
     * Checking if the game ended, if there is a winning streak for the given mark or
     * there is a draw
     * @param mark
     * @return true if the game ended by winning or draw
     */
    private boolean gameEnded(Mark mark){
        for (int i = 0; i < getBoardSize(); i++) {
            for (int j = 0; j < getBoardSize(); j++) {
                if (gameEndedHelper1(i, j, mark)){
                    return true;
                }
            }
        }

        return false;
    }


    /**
     * A helper method for the gameEnded function, checking for each given coordinate's directions
     * if there is a winning streak
     * @param x a coordinate
     * @param y a coordinate
     * @param mark
     * @return true if there is a winning streak or draw
     */
    private boolean gameEndedHelper1(int x, int y, Mark mark){
        /**
         * Checking all the directions for the given coordinates if there is
         * a winning streak
         */
        if (gameEndedHelper(x, y, 1, 0, mark) >= getWinStreak() || //checking down vertical
                gameEndedHelper(x, y, -1, 0, mark) >= getWinStreak() || //checking up vertical
                gameEndedHelper(x, y, 0, 1, mark) >= getWinStreak()  || //checking right horizontal
                gameEndedHelper(x, y, 0, -1, mark) >= getWinStreak() || //checking left horizontal
                gameEndedHelper(x, y, 1, 1, mark) >= getWinStreak()  || //checking slant down right
                gameEndedHelper(x, y, -1, -1, mark) >= getWinStreak() || //checking slant up left
                gameEndedHelper(x, y, -1, 1, mark) >= getWinStreak() || //checking slant up right
                gameEndedHelper(x, y, 1, -1, mark) >= getWinStreak()) {//checking slant down left
            checking = mark;
            return true;
        } else if (draw_counter >= (board.getSize()*board.getSize())){
            return true;
        }

        return false;
    }


    /**
     * helper method for ending game, counting the contiguous cells of a given mark
     * @param x
     * @param y
     * @param x_delta
     * @param y_delta
     * @param mark
     * @return counter of the contiguous cells of a given mark
     */
    private int gameEndedHelper(int x, int y, int x_delta, int y_delta, Mark mark){
        int count = 0;
        while (x >= 0 && x < getBoardSize() && y >= 0 && y < getBoardSize() && board.getMark(x, y) == mark){
            count++;
            x+=x_delta;
            y+=y_delta;
        }
        return count;
    }




    /**
     * Checking who is the winner
     * @param counter the game round number
     * @return the winner or if there is a draw
     */
    public Mark getWinner(int counter){
        if (checking == Mark.BLANK){
            return checking;
        }
        else if (counter%2 == 0 && checking == Mark.X || counter%2 == 1 && checking == Mark.O){
            return Mark.X;
        }
        return Mark.O;
    }





    /**
     * Rolling the game and checking who is the winner
     * @return the winner after rolling the game
     */
    public Mark run() {
        while (!gameEnded(Mark.X) && !gameEnded(Mark.O)){
            /**
             * for even game round, the player 1 get the X mark
             */
            if (counter % 2 == 0) {
                playerX.playTurn(board, Mark.X);
                draw_counter+=1;
                if (gameEnded(Mark.X)){
                    renderer.renderBoard(board);
                    break;
                }
                playerO.playTurn(board, Mark.O);
            } else {
                /**
                 * for odd game round the player 1 get the O mark
                 */
                playerX.playTurn(board, Mark.O);
                draw_counter+=1;
                if (gameEnded(Mark.O)){
                    renderer.renderBoard(board);
                    break;
                }
                playerO.playTurn(board, Mark.X);
            }
            renderer.renderBoard(board);
            draw_counter+=1;
        }

        Mark winner = getWinner(counter);
        counter++; // counter the game's rounds
        return winner;
    }

}