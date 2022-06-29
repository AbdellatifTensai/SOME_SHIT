import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ShityGui{

    JFrame frame;
    JLabel label;

    public static void main (String[] args) {
        ShityGui gui = new ShityGui();
        gui.go();
    }

    public void go() {
    
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton labelButton = new JButton("change label");
        labelButton.addActionListener(new LabelListener());
        JButton colorButton = new JButton("change color");
        colorButton.addActionListener(new ColorListener());

        label = new JLabel("hi, i missed you");

        MyDrawPanel drawPanel =  new MyDrawPanel();

        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
        frame.getContentPane().add(BorderLayout.WEST, label);
        frame.getContentPane().add(BorderLayout.EAST, labelButton);
        frame.setSize(300,300);
        frame.setVisible(true);
        
    }
   class LabelListener implements ActionListener{
       public void actionPerformed(ActionEvent ev){
           label.setText("fuck it");
       }
   }
   class ColorListener implements ActionListener{
       public void actionPerformed(ActionEvent ev){
           frame.repaint();
       }
   }
}