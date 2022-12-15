package edu.ship.engr.communication.handlers;

import edu.ship.engr.GUI.Board;
import edu.ship.engr.GUI.State;
import edu.ship.engr.messages.KillMessage;
import edu.ship.engr.messages.Message;
import edu.ship.engr.messages.ScoreMessage;
import edu.ship.engr.peertopeer.Runner;

import java.util.LinkedHashMap;

import static edu.ship.engr.peertopeer.Runner.gui;

public class KillMessageHandler implements Handler {

    State state = State.getState();
    Board board = Board.getBoard();

    @Override
    public void processMessage(Message<?> msgFromJSON) {
        KillMessage msg = new KillMessage((LinkedHashMap<String, Object>) msgFromJSON.getObject());

        if ((state.getTimestamp() >= msg.getTimestamp()) && (state.isInRecentKills(msg.getEnemyPosition()))) {
            ScoreMessage score = new ScoreMessage(5);
            board.addToOpponentScore(5);
            Runner.messageAccumulator.queueMessage(new Message<>(score));
            state.clearKills();
            System.out.println("COLLISION");
            return;
        }

        state.setTimestamp(msg.getTimestamp());
        Board.getBoard().removeEnemy(msg.getEnemyPosition());
        state.clearKills();
        ScoreMessage score = new ScoreMessage(10);
        board.addToOpponentScore(10);
        Runner.messageAccumulator.queueMessage(new Message<>(score));
        System.out.println("ALL GOOD");
    }
}