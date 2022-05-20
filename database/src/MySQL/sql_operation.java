package MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Player.Admin;
import Player.cartel;
import Player.fleet;
import Player.planet;
import Player.player;
import Player.ship;
import Player.planet;

public class sql_operation {
	
	public static final String DB_LOCATION = "jdbc:mysql://db.engr.ship.edu:3306/csc471_05?useTimezone=true&serverTimezone=UTC";
    public static final String LOGIN_NAME = "csc471_05";
    public static final String PASSWORD = "Password_05";
    protected Connection m_dbConn = null;
	
	public sql_operation() {
		
	}
	
	/**
	 * ensures JDBC is functional
	 * @return - whether JDBC is functional or not
	 * @throws SQLException
	 */
	public boolean activateJDBC() throws SQLException
    {
        try
        {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        }
        catch (SQLException sqle)
        {
            sqle.printStackTrace();
        }
        
        m_dbConn = DriverManager.getConnection(DB_LOCATION, LOGIN_NAME, PASSWORD);
        return true;
    }
	
	
	/**
	 * deletes a fleet from the Database
	 * @param fID - fleetID
	 * @throws SQLException
	 */
	public void deleteFleet(int fID)throws SQLException {
		Statement stmt = m_dbConn.createStatement();
		String deleteFleet = "DELETE FROM FLEET WHERE FleetID = " + fID + ";";
		stmt.execute(deleteFleet);
	}
	
	/**
	 * Deletes a ship from the database
	 * @param sID - Ship's ID
	 * @throws SQLException
	 */
	public void deleteShip(int sID) throws SQLException{
		Statement stmt = m_dbConn.createStatement();
		String deleteShip = "DELETE FROM SHIP WHERE ShipID = " + sID + ";";
		stmt.execute(deleteShip);
	}
	
	/**
	 * Adds a new ship to the database
	 * @param fID - Fleet's ID
	 * @param pID - Player's ID
	 * @param sID - Ship's ID
	 * @param speed - Speed of the ship
	 * @throws SQLException
	 */
	public void addShip(int fID, int pID, int sID, int speed) throws SQLException{
		Statement stmt = m_dbConn.createStatement();
		String addShip = "INSERT INTO SHIP (FID, PlayerID, ShipID, Speed) VALUES (" +
				fID + "," + pID + "," + sID + "," + speed + " );";
		stmt.execute(addShip);
	}
	
	/**
	 * Adds a new fleet to the database
	 * @param fID - Fleet's ID
	 * @param pID - Player's ID
	 * @throws SQLException
	 */
	public void addFleetToDatabase(int fID, int pID) throws SQLException {
		Statement stmt = m_dbConn.createStatement();
		String addFleet = "insert into FLEET (FleetID, PlayerID) values ("+ fID + "," + pID+ ");";
		stmt.execute(addFleet);
	}
	
	public void createTable(String tableName) throws SQLException {
		Statement stmt = m_dbConn.createStatement();
		String createTestStatement = "CREATE TABLE " + tableName + "(pID INT)";
		stmt.execute(createTestStatement);
	}
	
	/**
	 * gets all players from Database and returns them as a list
	 * @return - List of players
	 * @throws SQLException
	 */
	public player[] getPlayers() throws SQLException {
		player p[] = new player[10];
		Statement stmt = m_dbConn.createStatement();
		ResultSet rs = stmt.executeQuery("CALL getPlayers();");
		int i = 0;
		while(rs.next()) {
			p[i] = new player(rs.getInt("Money"),  rs.getInt("PlayerID"));
			i++;
		}	
		
		return p;
	}
	public Admin[] getAdmin(int AdminID) throws SQLException{
	Admin a[] = new Admin[10];
	Statement stmt = m_dbConn.createStatement();
	String select = "SELECT * FROM ADMININSTRATER WHERE ADMINISTRATER.AdminID =" + Integer.toString(AdminID) + ";";
	ResultSet rs = stmt.executeQuery(select);
	int i =0;
	while(rs.next()) {
		a[i] = new Admin(rs.getInt("AdminID"));
		i++;
	}
	return a;
	}
	
	/**
	 * Gets all fleets from the Database based on PlayerID
	 * @param pID - PlayerID
	 * @return - List of fleets
	 * @throws SQLException
	 */
	public fleet[] getFleet(int pID) throws SQLException{
		fleet f[] = new fleet[10];
		Statement stmt = m_dbConn.createStatement();
		String select = "SELECT * FROM FLEET WHERE FLEET.PlayerID = " + Integer.toString(pID) + ";";
		ResultSet rs = stmt.executeQuery(select);
		int i = 0;
		while(rs.next()) {
			f[i] = new fleet(rs.getInt("FleetID"));
			i++;
		}
		
		return f;
	}
	
	/**
	 * Gets all ships from the Database based on FleetID
	 * @param fleetID - FleetID
	 * @return List of Ships
	 * @throws SQLException
	 */
	public ship[] getShips(int fleetID) throws SQLException {
		ship s[] = new ship[10];
		Statement stmt = m_dbConn.createStatement();
		String select = "SELECT * FROM SHIP WHERE SHIP.FID = " + Integer.toString(fleetID) + ";";
		ResultSet rs = stmt.executeQuery(select);
		
		int i = 0;
		while(rs.next()) {
			s[i] = new ship(rs.getInt("ShipID"));
			i++;
		}
		
		return s;
	}
	
	public planet[] getPlanets(int planetNumber) throws SQLException {
		planet p[] = new planet[10];
		Statement stmt = m_dbConn.createStatement();
		String select = "SELECT * FROM SHIP WHERE PLANET.planetNumber = " + Integer.toString(planetNumber) + ";";
		ResultSet rs = stmt.executeQuery(select);
		
		int i = 0;
		while(rs.next()) {
			p[i] = new planet(rs.getInt("planetNumber"));
			i++;
		}
		
		return p;
	}
	
	public void addFleet(int fID, String FNAME) throws SQLException{
		Statement stmt = m_dbConn.createStatement();
		String add = "INSERT INTO FLEET (PID, FNAME) VALUES (" + Integer.toString(fID) + "," + FNAME + ");";
		stmt.execute(add);
		
	}
	

	public cartel[] getCartel() throws SQLException {
		cartel c[] = new cartel[10];
		Statement stmt = m_dbConn.createStatement();
		String select = "SELECT * FROM CARTEL;";
		ResultSet rs = stmt.executeQuery(select);
		
		int i = 0;
		while(rs.next()) {
			c[i] = new cartel(rs.getString("CartelName"));
			i++;
		}
		
		
		return c;
	}
	

	public player[] getCplayers(String cartelname) throws SQLException {
		player p[] = new player[10];
		Statement stmt = m_dbConn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER WHERE PLAYER.Cname = " + '"' +cartelname + '"' + ";");
		int i = 0;
		while(rs.next()) {
			p[i] = new player(rs.getInt("Money"),  rs.getInt("PlayerID"));
			i++;
		}	
		
		return p;
	}
	
	public void removeCplayer(int ID) throws SQLException {
		Statement stmt = m_dbConn.createStatement();
		stmt.executeUpdate("UPDATE PLAYER SET Cname = 'NoCartel' WHERE PlayerID = " + ID +";");
		}
	
	public void addCplayer(String cartelname, int ID) throws SQLException {
		Statement stmt = m_dbConn.createStatement();
		stmt.executeUpdate("UPDATE PLAYER SET Cname = " + '"' + cartelname +'"' + "WHERE PlayerID = " + ID +";");
	}
	
	
	
	/*public planet[] getPlanet(String planetname) throws SQLException {
		planet p[] = new planet[10];
		Statement stmt = m_dbConn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM PLANET WHERE PLANET.PlanetName = " + planetname + ";");
		int i = 0;
		while(rs.next()) {
			p[i] = new planet(rs.getInt("numberOfBuildngs"), rs.getInt("planetNumber"));
			i++;
		}
		return p;
		
	}
	*/
	
	
//	public ship[] getShips(int fID) throws SQLException {
//		
//	}

}
