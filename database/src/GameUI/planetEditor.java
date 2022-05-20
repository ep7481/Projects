package GameUI;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class planetEditor implements ActionListener {
  private JFrame mainFrame;
  private JLabel headerLabel;
  private JLabel statusLabel;
  private JPanel controlPanel;

  public planetEditor() {
    mainFrame = new JFrame("Planet Display");
    mainFrame.setSize(500,600);
    mainFrame.setLayout(new GridLayout(3, 1));

    mainFrame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent windowEvent) {
        System.exit(0);
      }
    });
    
    headerLabel = new JLabel("", JLabel.CENTER);
    statusLabel = new JLabel("", JLabel.CENTER);
    statusLabel.setSize(350, 100);

    controlPanel = new JPanel();
    controlPanel.setLayout(new FlowLayout());

    mainFrame.add(headerLabel);
    mainFrame.add(controlPanel);
    mainFrame.add(statusLabel);
    runDemo();
  }

  public static void main(String args[]) {
	planetEditor database = new planetEditor();
    database.runDemo();
  }

  private void runDemo() {
    headerLabel.setText("Planets");
    final DefaultListModel<String> planetName = new DefaultListModel<String>();

    planetName.addElement("Planet 1");
    planetName.addElement("Planet 2");
    planetName.addElement("Planet 3");
    planetName.addElement("Planet 4");
    planetName.addElement("Planet 5");
    planetName.addElement("Planet 6");
    planetName.addElement("Planet 7");

    final JList<String> planetList = new JList<String>(planetName);
    planetList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    planetList.setSelectedIndex(1);
    planetList.setVisibleRowCount(6);
    planetList.setPreferredSize(new Dimension(400,300));

    JScrollPane ListScrollPane = new JScrollPane(planetList);
       
    
    JButton editButton = new JButton("Edit");

    editButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String data = "";
        if (planetList.getSelectedIndex() != -1) {
          data = "Planet Selected: " + planetList.getSelectedValue();
          statusLabel.setText(data);
          
          JFrame sideFrame = new JFrame(planetList.getSelectedValue());
          sideFrame.setSize(400, 400);
          sideFrame.setLayout(new GridLayout(3, 1));
          
          sideFrame.setVisible(true);
        }
      }
    });

    JButton addButton = new JButton("add");

    addButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String title = JOptionPane.showInputDialog(null, "Enter planet name:");
        planetName.addElement(title);
      }
    });

    JButton deleteButton = new JButton("delete");

    deleteButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String data = "";
        if (planetList.getSelectedIndex() != -1) {
          int index = planetList.getSelectedIndex();
          data = "Planet Deleted: " + planetList.getSelectedValue();
          planetName.removeElementAt(index);
          statusLabel.setText(data);
        }

        statusLabel.setText(data);
      }
    });
    
    controlPanel.add(ListScrollPane);
    controlPanel.add(editButton);
    controlPanel.add(addButton);
    controlPanel.add(deleteButton);
  
  }
  
  
  public void open() {
	  mainFrame.setVisible(true);
  }
  
  public void close() {
	  mainFrame.setVisible(false);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub

  }
}