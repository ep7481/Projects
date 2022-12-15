package edu.ship.engr.GUI.entities;

import edu.ship.engr.GUI.Board;

import java.util.Random;

public class Bullet {
    private int speed;
    private int ticker;
    private int position;
    private char icon;
    private boolean isEnemyBullet;

    // enemy "fake" bullet constructor
    public Bullet(int position, int speed) {
        this.speed = speed;
        this.position = position;
        ticker = 0;
        icon = '+';

        isEnemyBullet = true;
    }

    // player's REAL bullets
    public Bullet(int position) {
        speed = randomSpeed();
        ticker = 0;
        this.position = position;
        icon = '*';
        isEnemyBullet = false;
    }

    private int randomSpeed() {
        return 20 - (new Random().nextInt(5));
    }

    public void tick() {
        ticker++;
    }

    public void resetTick() {
        ticker = 0;
    }

    public boolean isReady() {
        return ticker == speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void move() {
        position -= 22;
    }

    public int getPosition() {
        return position;
    }

    public char getIcon() {
        return icon;
    }

    public boolean isEnemyBullet() {
        return isEnemyBullet;
    }
}
