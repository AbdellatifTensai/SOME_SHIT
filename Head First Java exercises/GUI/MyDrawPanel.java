import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class MyDrawPanel extends JPanel{

    public void paintComponent(Graphics g){

        g.fillRect(0,0,this.getWidth(),this.getHeight());

        int red =  (int) (Math.random()*256);
        int green =  (int) (Math.random()*256);
        int blue =  (int) (Math.random()*256);

        Color rndColor = new Color(red, green, blue);
        g.setColor(rndColor);
        g.fillOval(70,70,100,100);
    }
}