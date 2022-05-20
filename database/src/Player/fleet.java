package Player;

import java.util.ArrayList;
import java.util.List;

public class fleet {
	List<ship> ships = new ArrayList<ship>();
	int fleetID;
	
	public fleet(int fleetID) {
		this.fleetID = fleetID;
	
	}
	
	public void addShip(ship s) {
		ships.add(s);
	}
	
	public int getFleetID() {
		return fleetID;
	}
	
	public void removeShip() {
		ships.remove(0);
	}
	
	public void removeShip(ship s) {
		ships.remove(s);
	}
	
	public List<ship> getShips() {
		return ships;
	}
	
	public String toString() {
		return "Fleet ID: " + fleetID;
	}

}
