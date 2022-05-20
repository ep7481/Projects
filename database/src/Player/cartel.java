package Player;


import java.util.ArrayList;
import java.util.List;

public class cartel {
	
	List<player> cartel = new ArrayList<player>();
			
	String CartelName;
	
	public cartel(String CartelName) {
		this.CartelName = CartelName;
	}
	
	public void addPlayerCartel(player p) {
		cartel.add(p);
	}
	public String getCartelName() {
		return CartelName;
	}
	
	public void removeplayer(player p) {
		cartel.remove(p);
	}
	
	public String toString() {
		return CartelName;
	}
	
}



