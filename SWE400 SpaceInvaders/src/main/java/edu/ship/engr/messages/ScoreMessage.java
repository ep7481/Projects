package edu.ship.engr.messages;

import java.util.LinkedHashMap;

public class ScoreMessage {
    int score;

    public ScoreMessage(int score) {
        this.score = score;
    }

    public ScoreMessage(LinkedHashMap<String, Object> p) {
        score = (Integer) p.get("score");
    }

    public int getScore () {return score;}

    public String toString() {
        return "ScoreMessage{score = " + score + "}";
    }
}
