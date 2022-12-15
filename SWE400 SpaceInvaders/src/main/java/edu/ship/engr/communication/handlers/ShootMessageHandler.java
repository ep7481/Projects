package edu.ship.engr.communication.handlers;

import edu.ship.engr.GUI.Board;
import edu.ship.engr.GUI.entities.Bullet;
import edu.ship.engr.messages.Message;
import edu.ship.engr.messages.ShootMessage;

import java.util.LinkedHashMap;

public class ShootMessageHandler implements Handler{
    @Override
    public void processMessage(Message<?> msgFromJSON) {
        ShootMessage msg = new ShootMessage((LinkedHashMap<String, Object>) msgFromJSON.getObject());
        Board.getBoard().addBullet(new Bullet(msg.getPosition(), msg.getSpeed()));
    }
}
