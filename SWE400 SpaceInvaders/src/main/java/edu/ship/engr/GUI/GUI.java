//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package edu.ship.engr.GUI;

import edu.ship.engr.GUI.entities.Player;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.text.JTextComponent;

public class GUI extends JFrame implements ActionListener {
    private static final String TITLE_BAR_START = "Space Invaders";
    private static final long serialVersionUID = 1L;
    private Timer timer;
    private JTextComponent boardBox;
    private Board board;
    private Player player;

    public GUI(boolean isHost) {
        if (isHost) {
            this.setTitle("HOST " + TITLE_BAR_START);
            this.board = Board.getBoard();
            board.setPlayers(5, 15);
        } else {
            this.setTitle(TITLE_BAR_START);
            this.board = Board.getBoard();
            board.setPlayers(15, 5);
        }
        this.setSize(469, 630);
        this.setLocationRelativeTo((Component) null);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setVisible(true);
        this.boardBox = new JTextArea();
        this.boardBox.setText("Starting");
        this.boardBox.setFont(new Font("Monospaced", Font.PLAIN, 35));
        this.add(this.boardBox);
        this.boardBox.addKeyListener(new KeyProcessor());

        this.startTheTimer();
    }

    private void startTheTimer() {
        this.timer = new Timer(5, this);
        //this.timer.setInitialDelay(150);
        this.timer.start();
    }

    public void actionPerformed(ActionEvent e) {
        this.board.movePlayerBullets();
        this.boardBox.setText(this.board.toString());
        this.invalidate();
        this.repaint();
    }

    private class KeyProcessor extends KeyAdapter {
        private KeyProcessor() {
        }

        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == 65) {
                board.move(Board.LEFT);
            }

            if (keyCode == 68) {
                board.move(Board.RIGHT);
            }

            if (keyCode == 32) {
                board.shoot();
            }

        }
    }
}
