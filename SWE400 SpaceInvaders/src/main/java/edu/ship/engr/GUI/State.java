package edu.ship.engr.GUI;

import java.util.ArrayList;

public class State {

    private int timestamp;
    ArrayList<Integer> recentKills = new ArrayList<>();

    private static State state = null;

    private State() {
        timestamp = 0;
    }

    synchronized public static State getState() {
        if (state == null) {
            state = new State();
        }
        return state;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public void advanceTimestamp(){
        timestamp = timestamp + 1;
    }

    public void addKill(int kill) {
        recentKills.add(kill);
    }

    public void clearKills() {
        recentKills.clear();
    }

    public boolean isInRecentKills(int position) {
        return recentKills.contains(position);
    }

}
