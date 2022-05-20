package runUi;
import java.sql.SQLException;

import GameUI.cartelEditor;
import GameUI.fleetEditor;
import GameUI.planetEditor;
import MySQL.sql_operation;
import GameUI.planetEditor;
import Player.fleet;
import Player.player;
import Player.ship;

public class RunUI{

  public static void main(String[] args) throws SQLException {
	  sql_operation sql = new sql_operation();
      sql.activateJDBC();
      
      fleetEditor fleet = new fleetEditor();
      fleet.FleetEditor(100, 100, sql);
      
      //cartelEditor cartel = new cartelEditor();
      //cartel.CartelEditor(100, 100, sql);
      
  
  }

}
