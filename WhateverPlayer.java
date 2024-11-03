import java.util.Random;

/**
 * WhateverPlayer is kind of a player that choosing a random coordinates
 */
public class WhateverPlayer implements Player{

    /**
     * A WhateverPlayer constructor
     */
    public WhateverPlayer(){}

    /**
     * The player put a mark in his turn
     * @param board
     * @param mark
     */
    public void playTurn(Board board, Mark mark){
        Random random = new Random();

        int x = random.nextInt(board.getSize());
        int y = random.nextInt(board.getSize());

        while (!board.putMark(mark, x, y)){
            x = random.nextInt(board.getSize());
            y = random.nextInt(board.getSize());
        }
    }
}
