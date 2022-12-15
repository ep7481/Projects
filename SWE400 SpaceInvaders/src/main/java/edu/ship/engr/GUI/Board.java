//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package edu.ship.engr.GUI;

import edu.ship.engr.GUI.entities.Bullet;
import edu.ship.engr.GUI.entities.Player;
import edu.ship.engr.messages.KillMessage;
import edu.ship.engr.messages.Message;
import edu.ship.engr.messages.MovementMessage;
import edu.ship.engr.messages.ShootMessage;
import edu.ship.engr.peertopeer.Runner;

import java.util.ArrayList;

public class Board {
    public static int LEFT = -1;
    public static int RIGHT = 1;
    private static final String blankLine = "\n                     ";
    private static final String enemyLine1 = "\n    # # # # # # #    ";
    private static final String enemyLine2 = "\n   # # # # # # # #   ";

    State state = State.getState();

    Player player;
    Player opponent;

    char[] playerLine = new char[25];
    StringBuffer boardString;
    StringBuffer cleanBoardString; // Board with no bullets, but hit aliens will be removed from here
    ArrayList<Bullet> bullets = new ArrayList<>();

    private static Board board;

    synchronized public static Board getBoard() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    private Board() {
        String cleanBoard = enemyLine1 + enemyLine2 + enemyLine1;
        for (int i = 0; i < 8; i++) {
            cleanBoard += blankLine;
        }
        cleanBoard += "\n";
        cleanBoardString = new StringBuffer(cleanBoard);
        boardString = new StringBuffer(cleanBoard);
    }

    public void setPlayers(int playerPosition, int opponentPosition) {
        player = new Player(playerPosition);
        opponent = new Player(opponentPosition);
        render();
    }

    public void addToScore(int points) {
        player.addScore(points);
    }

    public void addToOpponentScore(int points) {
        opponent.addScore(points);
    }

    private boolean isValidMove(int direction) {
        int newPos = player.getPosition() + direction;
        return (newPos >= 2 && newPos <= (playerLine.length - 7));
    }

    public void move(int direction) {
        if (isValidMove(direction)) {
            player.addPosition(direction);
            MovementMessage msg = new MovementMessage(player.getPosition());
            Runner.messageAccumulator.queueMessage(new Message<>(msg));
            render();
        }
    }

    public void moveOtherPlayer(int newPosition) {
        opponent.setPosition(newPosition);
        render();
    }

    private void render() {
        for (int i = 0; i < 25; i++) {
            playerLine[i] = ' ';
        }
        playerLine[player.getPosition()] = '^';
        playerLine[opponent.getPosition()] = '^';
    }

    public void shoot() {
        Bullet bullet = new Bullet(221 + player.getPosition());

        ShootMessage msg = new ShootMessage(bullet.getPosition(), bullet.getSpeed());
        Runner.messageAccumulator.queueMessage(new Message<>(msg));
        bullets.add(bullet);
    }

    public void addBullet(Bullet b) {
        bullets.add(b);
    }

    synchronized public void movePlayerBullets() {
        ArrayList<Bullet> removable = new ArrayList<>();
        boardString.setLength(0);
        boardString.append(cleanBoardString);
        for (Bullet i : bullets) {
            if (i.isReady()) {
                i.move();
                if (i.getPosition() < 22) {
                    removable.add(i);
                } else {
                    i.resetTick();
                }
            } else {
                i.tick();
            }
            if (!i.isEnemyBullet() && cleanBoardString.charAt(i.getPosition()) == '#') {
                alienShot(i.getPosition());
            }
            boardString.setCharAt(i.getPosition(), i.getIcon());
        }
        bullets.removeAll(removable);
    }

    public void alienShot(int position) {
        state.advanceTimestamp();
        state.addKill(position);
        KillMessage msg = new KillMessage(position, state.getTimestamp());
        Runner.messageAccumulator.queueMessage(new Message<>(msg));
        cleanBoardString.setCharAt(position, ' ');
    }

    synchronized public void removeEnemy(int position) {
        cleanBoardString.setCharAt(position, ' ');
    }

    public int getMyScore() { //Can only be used when game is running because a player can only set the other players score and not its own.
        return player.getScore();
    }

    public int getOpponentScore() {
        return opponent.getScore();
    }

    private boolean isGameOver() {
        return (player.getScore() + opponent.getScore()) >= 220;
    }

    public String toString() {
        if (isGameOver()) {
            if (player.getScore() > opponent.getScore()) {
                return "YOU WIN!\nNICE JOB!";
            } else if (player.getScore() < opponent.getScore()) {
                return "YOU LOST!\nBETTER LUCK NEXT TIME!";
            }
            return "IT'S A DRAW!\nBETTER LUCK NEXT TIME!";
        }
        return "Score:" + player.getScore() + " - Opp:" + opponent.getScore() + boardString + new String(playerLine);
    }
}