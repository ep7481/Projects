package GameUI;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import Player.fleet;
import Player.player;
import Player.ship;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import MySQL.sql_operation;

import javax.swing.JTextField;
//Consider figuring out Java list listener (Clickable list of buttons)
//https://docs.oracle.com/javase/tutorial/uiswing/components/list.html

public class fleetEditor {
  Dimension d;
  sql_operation sql;
  JScrollPane playerPane;
  JList list;
  JList shipList;
  JPanel playerList;
  JList fleetList;
  int pIndex;
  
  player pList[] = new player[100];
  
  fleet fList[] = new fleet[100];
  
  ship sList[] = new ship[100];
  
  JButton button4 = new JButton();
  JButton button3 = new JButton();
  JButton button2 = new JButton();
  JButton button = new JButton();
  JButton button5 = new JButton();
  JFrame frame;
  
  @SuppressWarnings({ "deprecation", "unchecked" })
  /**
   * Creates a new FleetEditor Object. Contains lists and listeners for a fully functional planet editor.
   * Must use open() and close() to open the window and close the window
   * @param y - width of the window
   * @param x - height of the window
   * @param s - Sql-operation that contains all necessary functions for the Database
   * @throws SQLException
   */
  public void FleetEditor(int x, int y, sql_operation s) throws SQLException {
	sql = s;
	
    d = new Dimension(200, 200);
    int cx = 0;
    int cy = 0;
    

    frame = new JFrame ("Fleet Editor");
    frame.setMinimumSize(d);
    
    list = new JList(pList);
    list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION );
    list.setLayoutOrientation(JList.VERTICAL);
    list.setVisibleRowCount(-1);
    list.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent evt) {
    		JList list = (JList)evt.getSource();
    		pIndex = list.getSelectedIndex();
    		try {
				updateFleets(pIndex);
			} catch (SQLException e) {
				e.printStackTrace();
			}
    		System.out.print(pIndex);
    	}
    });
    
    
    playerPane = new JScrollPane(list);
    playerList = new JPanel();
    playerList.add(playerPane);
    playerPane.setPreferredSize(new Dimension(200,200));
   
    
    JPanel right = new JPanel();
    
    fleetList = new JList(fList);
    fleetList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION );
    fleetList.setLayoutOrientation(JList.VERTICAL);
    fleetList.setVisibleRowCount(-1);
    fleetList.addMouseListener(new MouseAdapter() { public void mouseClicked(MouseEvent evt) {
		JList list = (JList)evt.getSource();
		int fIndex = list.getSelectedIndex();
		if(evt.getClickCount() == 2) {
			String newLoc = JOptionPane.showInputDialog("Enter new location for fleet:");
		}else {
			try {
				updateShips(fIndex);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.print(pIndex);
		}

		
	}
});

    
    JScrollPane fleet = new JScrollPane(fleetList);
    GridBagConstraints c = new GridBagConstraints();
    fleet.setPreferredSize(new Dimension(200,200));
    c.gridx = 2;
    c.gridy = 1;
    right.add(fleet, c);
    
    shipList = new JList(sList);
    shipList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION );
    shipList.setLayoutOrientation(JList.VERTICAL);
    shipList.setVisibleRowCount(-1);
    
    
    JScrollPane ships = new JScrollPane(shipList);
    ships.setPreferredSize(new Dimension(200,200));
    c.gridx = 1;
    c.gridy = 3;
    right.add(ships, c);
    
    JPanel bottomButtons = new JPanel();
    
    button.setLabel("Add Fleet");
    button.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){try {
		addFleet(list.getSelectedIndex());
	} catch (SQLException e1) {
		e1.printStackTrace();
	}}});
    
    button2.setLabel("Remove Fleet");
    button2.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){try {
		deleteFleet(fleetList.getSelectedIndex());
	} catch (SQLException e1) {
		e1.printStackTrace();
	}}});
    
    button3.setLabel("Add Ship");
    button3.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){try {
		addShip(fleetList.getSelectedIndex());
	} catch (SQLException e1) {
		e1.printStackTrace();
	}}});
    
    button4.setLabel("Delete Ship");
    button4.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){try {
		deleteShip(shipList.getSelectedIndex());
	} catch (SQLException e1) {
		e1.printStackTrace();
	}}});
    
    button5.setLabel("Close");
    button5.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){close();}});
    
    c.gridx = 0;
    bottomButtons.add(button, c);
    c.gridx = 1; 
    bottomButtons.add(button2, c);
    c.gridx = 2;
    bottomButtons.add(button3, c);
    c.gridx = 3;
    bottomButtons.add(button4, c);
    c.gridx = 4;
    bottomButtons.add(button5, c);
    
    
    

    frame.add(playerList, "West");
    frame.add(right, "East");
    frame.add(bottomButtons, "South");
    updatePlayers();

    frame.pack();
    
  }
  
  /*
   * Makes the main window visible
   */
  public void open() {
	  frame.setVisible(true);
  }
  
  
  /**
   * Makes the main window invisible
   */
  public void close() {
	  frame.setVisible(false);
  }
  
  /**
   * Deletes a fleet from the Database
   * @param fIndex - index of the fleet
   * @throws SQLException
   */
  public void deleteFleet(int fIndex) throws SQLException{
	  int fID = fList[fIndex].getFleetID();
	  int pIndex = list.getSelectedIndex();
	  sql.deleteFleet(fID);
	  updateFleets(pIndex);
  }
  
  /**
   * adds a new fleet to the Database
   * @param pIindex - player's index. Used for PlayerID
   * @throws SQLException
   */
  public void addFleet(int pIindex) throws SQLException {
	  JTextField fleetID = new JTextField(5);
	  
	  JPanel myPanel = new JPanel();
      myPanel.add(new JLabel("Fleet ID:"));
      myPanel.add(fleetID);
      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
      
      
      int result = JOptionPane.showConfirmDialog(null, myPanel, 
              "Create new fleet", JOptionPane.OK_CANCEL_OPTION);
     if (result == JOptionPane.OK_OPTION) {
       int fID = Integer.parseInt(fleetID.getText());
       int pID = pList[pIndex].getPlayerID();
       sql.addFleetToDatabase(fID, pID);
       updateFleets(pIndex);
     }
	  
  }
  
  /**
   * Adds a new ship to the database
   * @param fIndex - fleet's index. Used for PlayerID and FleetID
   * @throws SQLException
   */
  public void addShip(int fIndex) throws SQLException{
	  JTextField shipID = new JTextField(5);
	  JTextField speed = new JTextField(5);
	  JPanel addShipPanel = new JPanel();
	  addShipPanel.add(new JLabel("Ship ID:"));
	  addShipPanel.add(shipID);
	  addShipPanel.add(Box.createHorizontalStrut(15));
	  addShipPanel.add(new JLabel("Ship Speed: "));
	  addShipPanel.add(speed);
	  
	  int result = JOptionPane.showConfirmDialog(null, addShipPanel, "Create new ship", JOptionPane.OK_CANCEL_OPTION);
	  
	  if(result == JOptionPane.OK_OPTION) {
		  int fID = fList[fIndex].getFleetID();
		  int pID = pList[list.getSelectedIndex()].getPlayerID();  
		  sql.addShip(fID, pID, Integer.parseInt(shipID.getText()), Integer.parseInt(speed.getText()));
		  updateShips(fIndex);
	  }
  }
  
  /**
   * deletes a ship from the Database
   * @param sIndex - index of the ship. Used for ShipID
   * @throws SQLException
   */
  public void deleteShip(int sIndex) throws SQLException{
	  if(sList[sIndex] != null) {
		  int shipID = sList[sIndex].getShipID();
	  	  sql.deleteShip(shipID);
	  	  updateShips(fleetList.getSelectedIndex());
	  }
  }
 
  /**
   * Updates the JLists to reflect any changes
   */
  public void updateLists() {
	  playerList.updateUI();
	  fleetList.updateUI();
	  shipList.updateUI();
	  
  }
  
  /**
   * Fetches new players from the database and populates them on the list
   * @throws SQLException
   */
  public void updatePlayers() throws SQLException {
	  for(int i = 0; i < fList.length; i++) {
		  sList[i] = null;
		  fList[i] = null;
	  }
	  player tList[] = sql.getPlayers();
	  int i = 0;
	  while(tList[i] != null) {
		  pList[i] = tList[i];
		  i++;
	  }
	  updateLists();
  }
  
  /**
   * Fetches fleets from the Database and populates them based on clicked Player's ID
   * @param pIndexClicked - index of player. Used for PlayerID
   * @throws SQLException
   */
  public void updateFleets(int pIndexClicked) throws SQLException {
	  for(int i = 0; i < sList.length; i++) {
		  sList[i] = null;
	  }
	  
	  if(pList[pIndexClicked] != null) {
		  for(int i = 0; i < fList.length; i++) {
			  fList[i] = null;
		  }
		  player temp = pList[pIndexClicked];
		  int pID = temp.getPlayerID();
		  fleet tList[] = sql.getFleet(pID);
		  
		  int i = 0; 
		  while(tList[i] != null) {
			  fList[i] = tList[i];
			  i++;
		  }
		  updateLists();
	  }
  }
  
  /**
   * Fetches new ships from the database and populates them on third JList
   * @param fIndex - Index of the fleet
   * @throws SQLException
   */
  public void updateShips(int fIndex) throws SQLException {
	  if(fList[fIndex] != null) {
		  for(int i = 0; i < sList.length; i++) {
			  sList[i] = null;
		  }
		  
		  fleet temp = fList[fIndex];
		  int fID = temp.getFleetID();
		  ship tList[] = sql.getShips(fID);
		  
		  int i = 0; 
		  while(tList[i] != null) {
			  sList[i] = tList[i];
			  i++;
		  }
		  updateLists();
	  }else {
		  System.out.print("Error");
	  }
  }
}



