package edu.ship.engr.communication.handlers;

import edu.ship.engr.GUI.Board;
import edu.ship.engr.messages.Message;
import edu.ship.engr.messages.ScoreMessage;

import java.util.LinkedHashMap;

public class ScoreMessageHandler implements Handler{
    @Override
    public void processMessage(Message<?> msgFromJSON) {
        ScoreMessage msg = new ScoreMessage((LinkedHashMap<String, Object>) msgFromJSON.getObject());
        Board.getBoard().addToScore(msg.getScore());
    }
}
