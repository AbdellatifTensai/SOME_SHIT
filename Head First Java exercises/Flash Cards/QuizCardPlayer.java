import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

class QuizCardPlayer{

    private JFrame frame;
    private JTextArea display;
    private JTextArea answer;
    private JButton nextButton;
    private QuizCard currentCard;
    private ArrayList<QuizCard> cardList;
    private int currentCardIndex;
    private boolean isShowAnswer;
    
    public static void main(String[] args){
        new QuizCardPlayer().go();
    }

    private void go(){

        frame = new JFrame("Quiz Card Player");
        JPanel panel = new JPanel();
        Font font = new Font("sanserif", Font.BOLD , 24);
        display = new JTextArea(10, 20);
        display.setFont(font);
        display.setLineWrap(true);
        display.setEditable(false);
        JScrollPane qScroller = new JScrollPane(display);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        nextButton = new JButton("Show Question");
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem loadMenuItem = new JMenuItem("Load Card Set");
        nextButton.addActionListener(new NextCardListener());
        loadMenuItem.addActionListener(new OpenMenuListener());
        panel.add(nextButton);
        panel.add(qScroller);
        fileMenu.add(loadMenuItem);
        menuBar.add(fileMenu);
        frame.setJMenuBar(menuBar);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.setSize(640,500);
        frame.setVisible(true);
    }

    public class NextCardListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            if(isShowAnswer){
                display.setText(currentCard.getAnswer());
                nextButton.setText("Next Card");
                isShowAnswer = false;
            }else{
                if(currentCardIndex < cardList.size()){
                    showNextCard();
                }else{
                    display.setText("That's it, go home!");
                    nextButton.setEnabled(false);
                }
            }
        }

    }
    public class OpenMenuListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            loadFile(fileOpen.getSelectedFile());
        }
    }
    private void loadFile(File file){
        cardList = new ArrayList<QuizCard>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while((line = reader.readLine()) != null){
                makeCard(line);
            }
            reader.close();
        }catch(IOException ex){
            System.out.println("can't read that shit");
            ex.printStackTrace();
        }
        showNextCard();
    }
    
    private void makeCard(String lineToParse){
        String[] result = lineToParse.split("/");
        QuizCard card = new QuizCard(result[0] , result[1]);
        cardList.add(card);
        System.out.println("Made A Card!");     
    }

    private void showNextCard(){
        currentCard = cardList.get(currentCardIndex);
        currentCardIndex++;
        display.setText(currentCard.getQuestion());
        nextButton.setText("Show Answer");
        isShowAnswer = true;
    }
}
