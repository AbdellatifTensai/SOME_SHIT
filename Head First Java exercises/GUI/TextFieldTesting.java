import javax.swing.*;
import java.awt.*;
import java.io.*;

class TextFieldTesting{
    JFrame frame;
    JPanel panel;
    
    public static void main(String[] args){
        new TextFieldTesting().go();
        //TextFieldTesting a = new TextFieldTesting();
        //a.go();
    }

    public void setupGui(){
        frame = new JFrame();
        panel = new JPanel();
        panel.setBackground(Color.darkGray);
        //panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,400);
        frame.setVisible(true);
        frame.getContentPane().add(panel, BorderLayout.NORTH);
    }

    public void go(){
        setupGui();
        JTextField textField = new JTextField(20);
        panel.add(textField);
    }
}