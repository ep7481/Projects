package Player;

import java.util.List;

public class player {
	int playerID;
	int money;
	int resources;
	
	
	public player(int moneyint, int playerID) {
		this.playerID = playerID;
		this.money = moneyint;
	}

	
	public player(int money, int resources, int playerID) {
		this.playerID = playerID;
		this.money = money;
		this.resources = resources;
	}
	public int getPlayerID() {
		return playerID;
	}
	
	public int getMoney() {
		return money;
	}
	
	public int getResources() {
		return resources;
	}
	
	public String toString() {
		return "PlayerID: " + playerID + "\n";
	}
	public void loadFleet(fleet[] f) {
		
	}
	
}
