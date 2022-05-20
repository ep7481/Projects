package GameUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import MySQL.sql_operation;
import Player.cartel;
import Player.player;

public class cartelEditor {
	Dimension d;
	sql_operation sql;
	JScrollPane playerPane;
	JList PlayerList;
	JList CartelChat;

	player pyList[] = new player[100];
	cartel cList[] = new cartel[100];

	int pIndex;

	JButton button2 = new JButton();
	JButton button = new JButton();
	JFrame frame;
	JList CartelList;

	@SuppressWarnings("deprecation")
	public void CartelEditor(int i, int j, sql_operation s) throws SQLException {
		this.sql = s;
		frame = new JFrame("CARTEL LIST"); // frame for the ui of the cartel list
		frame.setMinimumSize(d);
		PlayerList = new JList(pyList);
		PlayerList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		PlayerList.setLayoutOrientation(JList.VERTICAL);
		PlayerList.setVisibleRowCount(-1);
		PlayerList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
			}
		});
		/**
		 * panel that add cartel and player list to the iu
		 */
		JPanel right = new JPanel();
		CartelList = new JList(cList);
		CartelList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		CartelList.setLayoutOrientation(JList.VERTICAL);
		CartelList.setVisibleRowCount(-1);
		CartelList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
				int index = list.getSelectedIndex();
				String nameclicked = cList[index].toString();
				try {
					fillPlayerList(nameclicked);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		GridBagConstraints gbc = new GridBagConstraints(); // helps the layout of the jpanel
		/**
		 * holds the cartels player list and scroll panel
		 */
		playerPane = new JScrollPane(CartelList);
		playerPane.setPreferredSize(new Dimension(250, 300));
		TitledBorder border = new TitledBorder("Cartel List"); // adds title to the panel
		border.setTitleJustification(TitledBorder.CENTER);
		playerPane.setBorder(border);

		/**
		 * contains the cartel panel inside of the UI
		 */
		JScrollPane playerlist = new JScrollPane(PlayerList);
		TitledBorder border2 = new TitledBorder("Player List");// adds title to the panel
		border2.setTitleJustification(TitledBorder.CENTER);
		playerlist.setBorder(border2);

		gbc.gridx = 0;
		gbc.gridy = 1;

		playerlist.setPreferredSize(new Dimension(250, 300));
		gbc.gridx = 0;
		gbc.gridy = 2;

		right.add(playerlist, gbc);

		JScrollPane chat = new JScrollPane(CartelChat);
		TitledBorder border3 = new TitledBorder("Chat List"); // adds title to the panel
		border3.setTitleJustification(TitledBorder.CENTER);
		chat.setBorder(border3);
		chat.setPreferredSize(new Dimension(250, 300));
		gbc.gridx = 0;
		gbc.gridy = 3;

		right.add(chat);

		JPanel bottomButtons = new JPanel(); // Jpanel that stores the bottons

		button.setLabel("Add Player"); // adding a button to add players to the jlist
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String title = JOptionPane.showInputDialog(null, "Enter Player Name");
				int playerid = Integer.parseInt(title);
				int index = CartelList.getSelectedIndex();
				String cname = cList[index].toString();
				try {
					sql.addCplayer(cname, playerid);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				updateLists();
			}
		});

		button2.setLabel("Remove Player"); // Remove player button for removing from cartel
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String data = "";
				if (PlayerList.getSelectedIndex() != 1) {
					int index = PlayerList.getSelectedIndex();
					int playerid = pyList[index].getPlayerID();
					try {
						sql.removeCplayer(playerid);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				updateLists();
			}

		});

		gbc.gridx = 5;
		bottomButtons.add(button, gbc);
		gbc.gridx = 6;
		bottomButtons.add(button2, gbc);

		frame.add(playerPane, "West");
		frame.add(right, "East");
		frame.add(bottomButtons, "South");

		frame.pack();
		fillList();

	}

	/**
	 * 
	 * @param args runs the GUI
	 * @throws SQLException
	 */
	public static void main(String args[]) throws SQLException {
		sql_operation sql = new sql_operation();
		sql.activateJDBC();
		cartelEditor cartel = new cartelEditor();
		cartel.CartelEditor(200, 200, sql);

	}

	/**
	 * method that updates the cartellist and the playerList
	 */
	public void updateLists() {
		PlayerList.updateUI();
		CartelList.updateUI();

	}

	/**
	 * updates the players inside of the UI
	 * 
	 * @throws SQLException
	 */
	public void updatePlayers(cartel cList) throws SQLException {
		player tList[] = sql.getCplayers(cList.getCartelName());
		int i = 0;
		while (tList[i] != null) {
			pyList[i] = tList[i];
			i++;
		}
		updateLists();
	}

	/**
	 * frame that allows the cartel name to be added to the GUI
	 * 
	 * @param test
	 */
	public static void testListener(String test) {
		JFrame f = new JFrame("Add Cartel");
		JPanel p = new JPanel();
		JButton b = new JButton(test);

		p.add(b);
		p.setMinimumSize(new Dimension(100, 100));
		f.add(p);
		f.pack();
		f.setVisible(true);

	}

	/*
	 * Fills the cartel list on the gui with all cartels
	 */
	public void fillList() throws SQLException {
		cartel c[] = sql.getCartel();
		for (int i = 0; i < c.length; i++) {
			if (c[i] != null && c[i].getCartelName() != "NoCartel")
				cList[i] = c[i];
		}
		updateLists();
	}

	/*
	 * Fills the gui with the players in each cartel
	 */
	public void fillPlayerList(String cname) throws SQLException {
		player p[] = sql.getCplayers(cname);
		for (int i = 0; i < pyList.length; i++) {
			pyList[i] = null;
		}
		for (int i = 0; i < p.length; i++) {
			pyList[i] = p[i];
		}
		updateLists();
	}

	/**
	 * opens up to the general UI
	 */
	public void open() {
		frame.setVisible(true);
	}

	public void close() {
		frame.setVisible(false);
	}
}
