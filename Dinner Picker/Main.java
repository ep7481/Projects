import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
A program to pick what to eat from dinner when I cannot decide.
 */
public class Main {
    public static void main(String[] args) {
        /*
            Creates a JFrame for the dinner picker button
         */
        JFrame picker = new JFrame();
        picker.setTitle("Dinner Picker");
        picker.setSize(165,80);
        picker.setLayout(null);
        picker.setLocationRelativeTo(null);                        //sets the window to open in the middle of the screen
        picker.setVisible(true);
        /*
            Creating the Jbutton for to select the answer.
        */
        JButton b = new JButton("Choose Dinner");
        b.setBounds(0,0,150,40);
        picker.add(b);
        /*
        Using the selection from the dinnerOptions() method we display the selection to the screen
        using a JLable on a new JFrame.
         */
        String choice = dinnerOptions();
        JFrame pick = new JFrame("The Choice is!");
        pick.setSize(200,100);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               picker.dispose();
               JLabel result = new JLabel(choice);
               result.setBounds(0, 0, 160,80);
               result.setFont(new Font("Font1", Font.BOLD, 35));
               pick.add(result);
               pick.setLayout(null);
               pick.setLocationRelativeTo(null);                   //sets the window to open in the middle of the screen
               pick.setVisible(true);
            }
        });


    }
/*
@returns String - The name of the restaurant that you have been chosen stored in a list and retrieved using
a random number generator
 */
    public static String dinnerOptions(){
        List<String> dinners = new ArrayList<String>();
        dinners.add("TEXAS");
        dinners.add("Aki");
        dinners.add("Mexican");
        dinners.add("Chic");
        dinners.add("Chipotle");
        dinners.add("Pizza");
        dinners.add("OG");
        int random = (int)(Math.random() * dinners.size());
        return dinners.get(random);

    }
}