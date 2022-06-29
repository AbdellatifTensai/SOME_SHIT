import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

class QuizCardBuilder{

    private JTextArea question;
    private JTextArea answer;
    private JFrame frame;
    private ArrayList<QuizCard> cardList;

    public static void main(String[] args){
        new QuizCardBuilder().go();
    }

    public void go(){
        frame = new JFrame("Quiz Card Builder");
        JPanel panel = new JPanel();
        Font font = new Font("sanserif", Font.BOLD, 24);
        
        question = new JTextArea(6,20);
        question.setLineWrap(true);
        question.setWrapStyleWord(true);
        question.setFont(font);

        JScrollPane qScroller = new JScrollPane(question);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        answer = new JTextArea(6,20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(font);
        JScrollPane aScroller = new JScrollPane(answer);
        aScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        aScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        JButton nextButton = new JButton("Next Card");
        JLabel aLabel = new JLabel("Answer:");
        JLabel qLabel = new JLabel("Question:");
        cardList = new ArrayList<QuizCard>();

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("save");

        nextButton.addActionListener(new NextCardListener());
        newMenuItem.addActionListener(new NewMenuListener());
        saveMenuItem.addActionListener(new SaveMenuListener());

        menuBar.add(fileMenu);
        fileMenu.add(newMenuItem);
        fileMenu.add(saveMenuItem);
        panel.add(aLabel);
        panel.add(qLabel);
        panel.add(aScroller);
        panel.add(qScroller);
        panel.add(nextButton);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(800,600);
        frame.setVisible(true);

    }

    public class NextCardListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);
            clearCard();
        }
    }
    public class SaveMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);
            JFileChooser fileSave = new JFileChooser();
            fileSave.showSaveDialog(frame);
            saveFile(fileSave.getSelectedFile());
        }
    }
    public class NewMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            cardList.clear();
            clearCard();
        }
    }
    private void clearCard(){
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }
    private void saveFile(File file){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for(QuizCard card:cardList){
                writer.write(card.getQuestion() + "/");
                writer.write(card.getAnswer() + "\n");
            }
            writer.close();
        }catch(IOException ex){
            System.out.println("can't write the cardList out!");
            ex.printStackTrace();
        }
    }
    
}