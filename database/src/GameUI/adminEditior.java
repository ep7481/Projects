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
import Player.Admin;
import Player.fleet;
import Player.player;
import Player.ship;

public class adminEditior {
	Dimension d;
	static sql_operation sql;
	JScrollPane playerPane;
	JList list;
	JList AttributeList;
	static JPanel playerList;

	player pyList[] = new player[100];
	Admin aList[] = new Admin[100];

	int pIndex;

	JButton button4 = new JButton();
	JButton button3 = new JButton();
	JButton button2 = new JButton();
	JButton button = new JButton();
	JFrame frame;

	static DefaultListModel<String> adminName = new DefaultListModel<String>();
	static JList<String> Adminlist = new JList<String>(adminName); //holds the adminNames 

	DefaultListModel<String> pList = new DefaultListModel<String>(); // arraylist that holds the players

	public void AdminEditior(int i, int j, sql_operation s) {
		JList<String> list = new JList<String>(pList);

		frame = new JFrame("ADMINISTRATER LIST"); // main frame for the admin UI
		frame.setMinimumSize(d); // sets the parameters of the Jframe

		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();
			}
		});
		/**
		 * panel that add admin, player panel , and attribute list
		 */
		JPanel right = new JPanel();

		Adminlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		Adminlist.setLayoutOrientation(JList.VERTICAL);
		Adminlist.setVisibleRowCount(-1);
		Adminlist.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				JList list = (JList) evt.getSource();

			}
		});

		GridBagConstraints gbc = new GridBagConstraints(); // helps the layout of the jpanel

		/**
		 * hlods the player list and scroll panel
		 */
		playerPane = new JScrollPane(list);
		playerPane.setPreferredSize(new Dimension(250, 300));
		TitledBorder border = new TitledBorder("Player List"); // adds title to the panel
		border.setTitleJustification(TitledBorder.CENTER);
		playerPane.setBorder(border);

		// right.add(playerPane, gbc);
		/**
		 * contains the admin pnael inside of the UI
		 */
		JScrollPane admin = new JScrollPane(Adminlist);
		TitledBorder border2 = new TitledBorder("admin List");// adds title to the panel
		border2.setTitleJustification(TitledBorder.CENTER);
		admin.setBorder(border2);

		gbc.gridx = 0;
		gbc.gridy = 1;

		admin.setPreferredSize(new Dimension(300, 200));
		gbc.gridx = 0;
		gbc.gridy = 2;

		right.add(admin, gbc);

		JScrollPane Attributes = new JScrollPane(AttributeList);
		TitledBorder border3 = new TitledBorder("Attribute List"); // adds title to the panel
		border3.setTitleJustification(TitledBorder.CENTER);
		Attributes.setBorder(border3);
		Attributes.setPreferredSize(new Dimension(300, 200));
		gbc.gridx = 0;
		gbc.gridy = 3;

		right.add(Attributes);

		JPanel bottomButtons = new JPanel(); // Jpanel that stores the bottons

		button.setLabel("Add Player"); // adding a button to add players to the jlist
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String title = JOptionPane.showInputDialog(null, "Enter Player Name");
				pList.addElement(title);
			}
		});

		button2.setLabel("Remove Player"); // button that removes the players
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLists();
				String data = "";
				if (list.getSelectedIndex() != 1) {
					int index = list.getSelectedIndex();
					data = "Player has been removed" + list.getSelectedValue();
					pList.removeElementAt(index);
					System.out.println(data);
				}
			}
		});

		button3.setLabel("Add Admin"); // button that adds the admin to the database
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String title = JOptionPane.showInputDialog(null, "Enter Player Name");
				adminName.addElement(title);
			}
		});

		button4.setLabel("Edit Attribute"); // button that edits the attributes of players
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateLists();
			}
		});

		gbc.gridx = 5;
		bottomButtons.add(button, gbc);
		gbc.gridx = 6;
		bottomButtons.add(button2, gbc);
		gbc.gridx = 7;
		bottomButtons.add(button3, gbc);
		gbc.gridx = 8;
		bottomButtons.add(button4, gbc);

		frame.add(playerPane, "West");
		frame.add(right, "East");
		frame.add(bottomButtons, "South");

		frame.pack();

	}
	
	/**
	 * 
	 * @param args runs the GUI
	 */
	public static void main(String args[]) {
		adminEditior admin = new adminEditior();
		admin.AdminEditior(200, 200, sql);

	}

	/**
	 * method that updates the admindlist and the playerList
	 */
	public static void updateLists() {
		playerList.updateUI();
		Adminlist.updateUI();

	}

	/**
	 * updates the players inside of the UI
	 * 
	 * @throws SQLException
	 */
	public void updatePlayers() throws SQLException {
		for (int i = 0; i < aList.length; i++) {
			aList[i] = null;
		}
		player tList[] = sql.getPlayers();
		int i = 0;
		while (tList[i] != null) {
			pyList[i] = tList[i];
			i++;
		}
		updateLists();
	}

	/**
	 * frame that allows the admin name to be added to the GUI
	 * 
	 * @param test
	 */
	public static void testListener(String test) {
		JFrame f = new JFrame("Add Admin");
		JPanel p = new JPanel();
		JButton b = new JButton(test);

		p.add(b);
		p.setMinimumSize(new Dimension(100, 100));
		f.add(p);
		f.pack();
		f.setVisible(true);

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
