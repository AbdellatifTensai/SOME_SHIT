import javax.swing.*;
import java.awt.*;
import java.util.*;

class GuiTesting{

    JFrame frame = new JFrame();

    public static void main(String[] args){
        GuiTesting a = new GuiTesting();
        a.go();
    }

    public void go(){
        setupGui();
        JPanel panel = new JPanel();
        panel.setBackground(Color.darkGray);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        frame.getContentPane().add(BorderLayout.EAST, panel);
        JButton b1 = new JButton("i love you");
        JButton b2 = new JButton("i hate you");
        JButton b3 = new JButton("i miss you");

        ArrayList<JButton> buttons = new ArrayList<JButton>();
        buttons.add(b1);
        buttons.add(b2);
        buttons.add(b3);

        for(JButton a : buttons){panel.add(a);}
    }

    public void setupGui(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setVisible(true);
    }
}