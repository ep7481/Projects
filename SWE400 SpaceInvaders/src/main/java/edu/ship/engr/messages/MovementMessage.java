package edu.ship.engr.messages;


import java.util.LinkedHashMap;

public class MovementMessage {
    int playerPosition;

    public MovementMessage(int position) {
        playerPosition = position;
    }

    public MovementMessage(LinkedHashMap<String, Object> p) {
        playerPosition = (Integer) p.get("playerPosition");
    }

    public int getPlayerPosition() {
        return playerPosition;
    }

    public String toString() {
        return "MovementMessage{enemyPosition = " + playerPosition + "}";
    }

}
