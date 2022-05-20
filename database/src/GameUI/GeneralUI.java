package GameUI;

import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import MySQL.sql_operation;

public class GeneralUI {
	
	JFrame frame;
	JPanel up;
	JPanel down;
	JLabel p;
	JButton b1;
	JButton b2;
	JButton b3;
	JButton b4;
	
	int width;
	int height;
	sql_operation sql;
	
	public GeneralUI(int width, int height, sql_operation sql) throws SQLException {
		this.width = width;
		this.height = height;
		this.sql = sql;
		
		fleetEditor fleet = new fleetEditor();
	    fleet.FleetEditor(width, height, sql);
	    
	    cartelEditor cartel = new cartelEditor();
	    cartel.CartelEditor(width, height, sql);
	    
	    adminEditior admin = new adminEditior();
	    admin.AdminEditior(width, height, sql);
	    
	    planetEditor planet = new planetEditor();
	    planet.close();
		
		frame = new JFrame("Admin UI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		up = new JPanel();
		down = new JPanel();
		p = new JLabel();
		p.setText("Select an action panel");
		up.add(p);
		
		b1 = new JButton("Open Admin Editor");
	    b1.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){admin.open();}});

		b2 = new JButton("Open Cartel Editor");
	    b2.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){cartel.open();}});

		b3 = new JButton("Open Fleet Editor");
	    b3.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){fleet.open();}});

		b4 = new JButton("Open Planet Editor");
	    b4.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e){planet.open();}});

		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		down.add(b1, c);
		
		c.gridx = 1;
		down.add(b2, c);
		
		c.gridx = 2;
		down.add(b3, c);
		
		c.gridx = 4;
		down.add(b4, c);
		
		frame.add(up, "North");
		frame.add(down, "South");
		frame.pack();
		frame.setVisible(true);
		
		
	}
	
	
	public static void main(String args[]) throws SQLException {
		 sql_operation sql = new sql_operation();
	      sql.activateJDBC();
		GeneralUI ui = new GeneralUI(100, 100, sql);
	}
}
