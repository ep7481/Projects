package edu.ship.engr.messages;


import edu.ship.engr.GUI.State;

import java.util.LinkedHashMap;

public class KillMessage {
    int enemyPosition;
    int timestamp;

    public KillMessage(int position, int timeStamp) {
        timestamp = timeStamp;
        enemyPosition = position;
    }

    public KillMessage(LinkedHashMap<String, Object> p) {
        enemyPosition = (Integer) p.get("enemyPosition");
        timestamp = (Integer) p.get("timestamp");
    }

    public int getEnemyPosition() {
        return enemyPosition;
    }

    public int getTimestamp(){
        return timestamp;
    }

    public String toString() {
        return "Alien Position: " + enemyPosition + ", timestamp: " + timestamp;
    }

}
