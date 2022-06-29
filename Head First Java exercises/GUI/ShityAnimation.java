import javax.swing.*;
import java.awt.*;

class ShityAnimation{
    int x = 70;
    int y = 70;
    
    public static void main(String[] args){
        ShityAnimation a = new ShityAnimation();
        a.go();
    }

    public void go(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ShityPanel myPanel = new ShityPanel();

        frame.getContentPane().add(myPanel);
        frame.setSize(300,300);
        frame.setVisible(true);

        for(int n = 0;n<=130;n++){
            x++;
            y++;
            myPanel.repaint();

            try{
                Thread.sleep(50);
            }catch(Exception ex){}
        }
        
    }

    class ShityPanel extends JPanel{
        public void paintComponent(Graphics g){
            g.setColor(Color.white);
            g.fillRect(0,0,this.getWidth(), this.getHeight());
            g.setColor(Color.green);
            g.fillOval(x,y,40,40);
        }
    }
}