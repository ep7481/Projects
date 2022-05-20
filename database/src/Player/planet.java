package Player;

import java.util.List;

public class planet {
	int playerID;
	int planetNumber;
	int numberOfBuildings;
	String planetName;
	
	public planet(String planetName) {
		this.planetName = planetName;
		this.planetNumber = planetNumber ;
	}

	
	public planet(int numberOfBuildings, int planetNumber, int playerID, String planetName ) {
		this.playerID = playerID;
		this.numberOfBuildings = numberOfBuildings;
		this.planetNumber = planetNumber;
		this.planetName = planetName;
	}
	public planet(int int1) {
		// TODO Auto-generated constructor stub
	}


	public int getPlayerID() {
		return playerID;
	}
	
	public int getnumberOfBuildings() {
		return numberOfBuildings;
	}
	
	public int getplanetNumber() {
		return planetNumber;
	}
	
	public String toString() {
		return "PlayerID: " + playerID + "\n";
	}
	
	public String getplanetName() {
		return planetName;
	}
	


}
