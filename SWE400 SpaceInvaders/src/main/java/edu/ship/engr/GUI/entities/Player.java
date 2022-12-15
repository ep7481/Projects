
package edu.ship.engr.GUI.entities;

public class Player {
    private int position;
    private int score;

    public Player(int position) {
        this.position = position;
        this.score = 0;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void addPosition(int change) {
        position += change;
    }

    public void addScore(int points) {
        score += points;
    }

    public int getScore(){
        return score;
    }
}
