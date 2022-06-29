import java.awt.*;
import javax.swing.*;
import javax.sound.midi.*;
import java.util.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.JFileChooser;

class BeatBoxMachine{

    JFrame frame;
    JPanel mainPanel;
    JPanel backGround;
    ArrayList<JCheckBox> checkBoxList;
    Box buttonBox;
    Box nameBox;
    GridLayout grid;
    Sequencer sequencer;
    Sequence sequence;
    Track track;
    String[] instrumentNames = {"Bass Drum", "Closed Hi-Hat","Open Hi-Hat","Acoustic Snare",
                            "Crash Cymbal", "Hand Clap","High Tom", "Hi Bongo",
                             "Maracas","Whistle", "Low Conga","Cowbell", "Vibraslap",
                             "Low-mid Tom","High Agogo","Open Hi Conga"};
    
    int[] instruments = {35,42,46,38,49,39,50,60,70,72,64,56,58,47,67,63};
    
    public static void main(String[] args){
        new BeatBoxMachine().setUpGui();
    }

    public void setUpGui(){
        frame = new JFrame();   
        BorderLayout layout = new BorderLayout();
        backGround = new JPanel(layout);
        backGround.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        checkBoxList = new ArrayList<JCheckBox>();
        buttonBox = new Box(BoxLayout.Y_AXIS);

        JButton start = new JButton("START");
        start.addActionListener(new BStartListener());
        buttonBox.add(start);

        JButton stop = new JButton("STOP");
        stop.addActionListener(new BStopListener());
        buttonBox.add(stop);

        JButton tempoUp = new JButton("TEMPO UP");
        tempoUp.addActionListener(new BTempoUpListener());
        buttonBox.add(tempoUp);

        JButton tempoDown = new JButton("TEMPO DOWN");
        tempoDown.addActionListener(new BTempoDownListener());
        buttonBox.add(tempoDown);
        //2nd version:
        JButton save = new JButton("Save Pattern");
        save.addActionListener(new BSaveListener());
        buttonBox.add(save);
        
        JButton load = new JButton("load Pattern");
        load.addActionListener(new BLoadListener());
        buttonBox.add(load);
        //2nd version;
        nameBox = new Box(BoxLayout.Y_AXIS);
        for(String a : instrumentNames){nameBox.add(new Label(a));}

        grid = new GridLayout(16,16);
        grid.setVgap(1);
        grid.setHgap(2);
        mainPanel = new JPanel(grid);

        backGround.add(BorderLayout.EAST, buttonBox);
        backGround.add(BorderLayout.WEST, nameBox);
        backGround.add(BorderLayout.CENTER, mainPanel);
        frame.getContentPane().add(backGround);

        for(int b=0;b<256;b++){
            JCheckBox c = new JCheckBox();
            c.setSelected(false);
            checkBoxList.add(c);
            mainPanel.add(c);
        }

        setUpMidi();
        frame.setBounds(50,50,300,300);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setUpMidi(){
        try{
            sequencer = MidiSystem.getSequencer();
            sequencer.open();
            sequence = new Sequence(Sequence.PPQ,4);
            track = sequence.createTrack();
            sequencer.setTempoInBPM(120);
        }catch(Exception ex){ex.printStackTrace();}
    }

    public void checkBeatsAndStart(){
        int[] trackList = null;
        sequence.deleteTrack(track);
        track = sequence.createTrack();

        for(int x=0;x<16;x++){
            trackList = new int[16];
            int key = instruments[x];
            for(int y=0;y<16;y++){
                JCheckBox jc = checkBoxList.get(y + 16*x);
                if(jc.isSelected()){
                    trackList[y] = key;
                }else{
                    trackList[y] = 0;
                }
            }
        makeTracks(trackList);
        track.add(makeEvent(176,1,127,0,16));
        }

        track.add(makeEvent(192,9,1,0,15));
        try{
            sequencer.setSequence(sequence);
            sequencer.setLoopCount(sequencer.LOOP_CONTINUOUSLY);
            sequencer.start();
            sequencer.setTempoInBPM(120);
        }catch(Exception e){e.printStackTrace();}
    }

    public class BStartListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            checkBeatsAndStart();
        }
    }

    public class BStopListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            sequencer.stop();
        }
    }

    public class BTempoUpListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * 1.03));
        }
    }

    public class BTempoDownListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            float tempoFactor = sequencer.getTempoFactor();
            sequencer.setTempoFactor((float) (tempoFactor * .97));
        }
    }

    public void makeTracks(int[] list){
        for(int h=0;h<16;h++){
            int key = list[h];
            if(key != 0){
                track.add(makeEvent(144,9,key,100,h));
                track.add(makeEvent(128,9,key,100,h+1));
            }
        }
    }

    public MidiEvent makeEvent(int a,int b,int c,int d,int tick){
        MidiEvent event = null;
        try{
            ShortMessage msg = new ShortMessage();
            msg.setMessage(a,b,c,d);
            event = new MidiEvent(msg, tick);
        }catch(Exception exc){exc.printStackTrace();}
        return event;
    }
    public class BSaveListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            boolean[] checkBoxState = new boolean[256];
            for(JCheckBox check : checkBoxList){
                if(check.isSelected()){
                    checkBoxState[checkBoxList.indexOf(check)] = true;
                }
            }
            try{
                ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileOpen.getSelectedFile()));
                os.writeObject(checkBoxState);
            }catch(Exception ex){ex.printStackTrace();}
        }
    }
    public class BLoadListener implements ActionListener{
        public void actionPerformed(ActionEvent ev){
            JFileChooser fileOpen = new JFileChooser();
            fileOpen.showOpenDialog(frame);
            boolean[] checkBoxState = new boolean[256];
            try{
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(fileOpen.getSelectedFile()));
                checkBoxState = (boolean[]) is.readObject();
            }catch(Exception ex){ex.printStackTrace();}
            
            for(JCheckBox check : checkBoxList){
                if(checkBoxState[checkBoxList.indexOf(check)]){
                    check.setSelected(true);
                }else{
                    check.setSelected(false);
                }
            }
            sequencer.stop();
        }
    }
}

            