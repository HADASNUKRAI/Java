/**
 * Responsible to map the string to the player object
 */

public class PlayerFactory{
    public Player buildPlayer(String type) {
        if (type.equals("Human") || type.equals("HUMAN") || type.equals("human")){
            return new HumanPlayer();
        }
        else if (type.equals("Clever") || type.equals("Clever") || type.equals("clever")){
            return new CleverPlayer();
        }
        else if (type.equals("Genius") || type.equals("GENIUS") || type.equals("genius")){
            return new GeniusPlayer();
        }

        return new WhateverPlayer();
    }
}
