/**
 * HumanPlayer is kind of a player, representing human player
 * asking from the usr for coordinates
 */

class HumanPlayer implements Player {
    HumanPlayer(){}

    public void playTurn(Board board, Mark mark){
        System.out.println(Constants.playerRequestInputString(mark.toString()));
        int num = KeyboardInput.readInt();

        while (!board.putMark(mark, num/10, num%10)){
            System.out.println(Constants.INVALID_COORDINATE);
            num = KeyboardInput.readInt();
        }
    }
}
