package Player;


import java.util.ArrayList;
import java.util.List;

public class Admin {
	
	List<player> admin = new ArrayList<player>();
			; 
	int adminID;
	
	public Admin(int adminID) {
		this.adminID = adminID;
	}
	
	public void addAdmin(player p) {
		admin.add(p);
	}
	public int getAdminID() {
		return adminID;
	}
	
	public void removeAdmin(player p) {
		admin.remove(p);
	}
	
}
