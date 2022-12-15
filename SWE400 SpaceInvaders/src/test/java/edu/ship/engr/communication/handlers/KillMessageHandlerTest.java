package edu.ship.engr.communication.handlers;

import edu.ship.engr.GUI.Board;
import edu.ship.engr.GUI.State;
import edu.ship.engr.messages.Message;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedHashMap;

public class KillMessageHandlerTest {
    @Test
    public void testScoreNoCollision() {
        State state = State.getState();
        Board board = Board.getBoard();
        board.setPlayers(15, 5);
        KillMessageHandler handler = new KillMessageHandler();

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("enemyPosition", 50);
        map.put("timestamp", 2);

        state.setTimestamp(2);
        board.alienShot(49);

        handler.processMessage(new Message<edu.ship.engr.messages.KillMessage>(map));

        assertEquals(board.getOpponentScore(), 10);

    }

    @Test
    public void testScoreCollision() {
        State state = State.getState();
        Board board = Board.getBoard();
        board.setPlayers(15, 5);
        KillMessageHandler handler = new KillMessageHandler();

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("enemyPosition", 50);
        map.put("timestamp", 2);

        state.setTimestamp(2);
        board.alienShot(50);

        handler.processMessage(new Message<edu.ship.engr.messages.KillMessage>(map));

        assertEquals(board.getOpponentScore(), 5);

    }

    @Test
    public void testCollisionEqual(){
        State state = State.getState();
        Board board = Board.getBoard();
        board.setPlayers(15, 5);
        board.alienShot(1);
        state.setTimestamp(2);
        KillMessageHandler km = new KillMessageHandler();
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("enemyPosition", 1);
        map.put("timestamp", 2);
        km.processMessage(new Message<edu.ship.engr.messages.KillMessage>(map));
        assertEquals(board.getOpponentScore(), 5);
    }
}
