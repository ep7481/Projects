package Player;

public class ship {
	int id;
	
	public ship(int id) {
		this.id = id;
	}
	
	public int getShipID() {
		return id;
	}
	
	public String toString() {
		return "Ship ID: " + Integer.toString(id);
	}
}
