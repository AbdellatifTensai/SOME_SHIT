import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ChatClientA {

    JTextField textField;
    Socket socket;
    PrintWriter writer;
    public static JFrame frame = new JFrame();

    public static void main(String[] args) {
        new ChatClientA().go();
    }

    private void go() {
        JPanel panel = new JPanel();
        textField = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new BSend());
        panel.add(textField);
        panel.add(sendButton);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(400, 500);
        frame.setVisible(true);
        setUpNetworking();
    }

    private void setUpNetworking() {
        try {
            socket = new Socket("127.0.0", 5000);
            writer = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private class BSend implements ActionListener {
        public void actionPerformed(ActionEvent ev) {
            try {
                writer.println(textField.getText());
                writer.flush();
                System.out.println("i think the shit is working!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            textField.setText("");
            textField.requestFocus();
        }
    }
}
