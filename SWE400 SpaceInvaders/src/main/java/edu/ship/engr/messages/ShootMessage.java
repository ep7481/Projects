package edu.ship.engr.messages;


import java.util.LinkedHashMap;

public class ShootMessage {
    int speed;
    int position;

    public ShootMessage(int position, int speed) {
        this.speed = speed;
        this.position = position;
    }

    public ShootMessage(LinkedHashMap<String, Object> p) {
        speed = (Integer) p.get("speed");
        position = (Integer) p.get("position");
    }

    public int getSpeed() {
        return speed;
    }

    public int getPosition() {
        return position;
    }

    public String toString() {
        return "ShootMessage{speed = " + speed + "}";
    }

}
