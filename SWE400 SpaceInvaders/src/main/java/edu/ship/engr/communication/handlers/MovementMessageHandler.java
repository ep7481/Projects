package edu.ship.engr.communication.handlers;

import edu.ship.engr.GUI.Board;
import edu.ship.engr.messages.Message;
import edu.ship.engr.messages.MovementMessage;

import java.util.LinkedHashMap;

public class MovementMessageHandler implements Handler{
    @Override
    public void processMessage(Message<?> msgFromJSON) {
        MovementMessage msg = new MovementMessage((LinkedHashMap<String, Object>) msgFromJSON.getObject());
        Board.getBoard().moveOtherPlayer(msg.getPlayerPosition());
    }
}
