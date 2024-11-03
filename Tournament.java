/**
 * Running a turner of number of games, of the game Tic Tac Toe
 * @author Hadas Nukrai
 */
public class Tournament {
    private final int rounds; //the number of the rounds
    private final Renderer renderer; //the render word
    private final Player player_1; //player 1
    private final Player player_2; //player 2

    private static final String WINNING_PLAYER_1 = "Player 1, %s won: %d rounds";
    private static final String WINNING_PLAYER_2 = "Player 2, %s won: %d rounds";

    private int counter_player_1 = 0;//count the winnings of the player 1
    private int counter_player_2 = 0;//count the winnings of the player 2
    private int counter_draw = 0;//count the number of draw

    /**
     * A constructor that init the games objects
     * @param rounds number game's rounds
     * @param renderer value for renderer the game
     * @param player_1 one player
     * @param player_2 second player
     */
    public Tournament(int rounds, Renderer renderer, Player player_1, Player player_2){
        this.rounds = rounds;
        this.renderer = renderer;
        this.player_1 = player_1;
        this.player_2 = player_2;
    }

    /**
     * Representing a round in the game
     * @param size the size of the board
     * @param winStreak number of the winning streak condition
     * @param players_1 one player
     * @param players_2 second player
     */
    public void playTournament(int size, int winStreak, String players_1, String players_2){
        Game game = new Game(this.player_1, this.player_2, size, winStreak, renderer);
        Mark result = game.run();
        if (result == Mark.X){
            System.out.println("The winner is player 1: " + players_1);
            counter_player_1++;

        }
        else if (result == Mark.O){
            System.out.println("The winner is player 2: " + players_2);
            counter_player_2++;
        }
        else{
            counter_draw++;
            System.out.println("The game finish with draw");
        }
    }

    /**
     * Checking if the given players names is correct
     * @param name a given player's name
     * @param capital_letters a given string of capital letters of the name
     * @param small_letters a given string of small letters of the name
     * @return true if the given players names/renderer word is correct otherwise false
     */
    public static boolean checkingWord (String name, String capital_letters, String small_letters){
        if (name.length() != capital_letters.length()){
            return false;
        }

        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != capital_letters.charAt(i) && name.charAt(i) != small_letters.charAt(i)){
                return false;
            }
        }

        return true;
    }


    public static void main (String[] args){
        String[] rendererWord_capital = {"CONSOLE", "NONE"};
        String[] rendererWord_small = {"console", "none"};
        String[] players_capital_letter = {"HUMAN", "CLEVER", "WHATEVER", "GENIUS"};
        String[] players_small_letter = {"human", "clever", "whatever", "genius"};
        int count = -1;

        /**
         * Checking if the players names is correct
         */
        for (int i = 0; i < players_capital_letter.length; i++) {
            String name_1 = players_capital_letter[i];
            String name_2 = players_small_letter[i];
            count++;

            if (!checkingWord(args[4], name_1, name_2) && count == 4 ||
                    !checkingWord(args[5], name_1, name_2) && count == 4){
                System.out.println(Constants.UNKNOWN_PLAYER_NAME);
                return;
            }
        }

        int counter = 0;
        int rounds = Integer.parseInt(args[0]);
        int size = Integer.parseInt(args[1]);
        int winStreak = Integer.parseInt(args[2]);
        String target = args[3];
        count = 0;

        /**
         * Checking if the renderer word is correct
         */
        for (int i = 0; i < rendererWord_capital.length; i++) {
            String word_1 = rendererWord_capital[i];
            String word_2 = rendererWord_small[i];
            count++;

            if(!checkingWord(target, word_1, word_2) && count == 2){
                System.out.println(Constants.UNKNOWN_RENDERER_NAME);
                return;
            }
        }


        RendererFactory rendererFactory = new RendererFactory();
        Renderer renderer = rendererFactory.buildRenderer(target, size);
        PlayerFactory playerFactory = new PlayerFactory();
        Player player_1 = playerFactory.buildPlayer(args[4]);
        Player player_2 = playerFactory.buildPlayer(args[5]);
        Tournament tournament = new Tournament(rounds, renderer, player_1, player_2);

        /**
         * Running the game's rounds
         */
        while (counter < tournament.rounds){
            tournament.playTournament(size, winStreak, args[4], args[5]);
            counter++;
        }

        System.out.println("#########Results#########");
        System.out.println(String.format(WINNING_PLAYER_1, args[4], tournament.counter_player_1));
        System.out.println(String.format(WINNING_PLAYER_2, args[5], tournament.counter_player_2));
        System.out.println("Ties: " + tournament.counter_draw);
    }
}
