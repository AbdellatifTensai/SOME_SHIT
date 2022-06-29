import javax.sound.midi.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;

class MiniMusicPlayer {
    static JFrame frame = new JFrame("hi");
    static drawPanel panel;

    public static void main(String[] args){
        MiniMusicPlayer a = new MiniMusicPlayer();
        a.go();
    }

    public void setGUI(){
        panel = new drawPanel();
        frame.setContentPane(panel);
        frame.setBounds(30,30,300,300);
        frame.setVisible(true);
    }

    public void go(){
        setGUI();

        try{
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            
            int[] eventIWant = {127};
            sequencer.addControllerEventListener(panel, eventIWant);
            
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();
            int r =0;
            for(int i =0;i<60;i += 4){
                r = (int) ((Math.random()*50)+1);
                track.add(makeEvent(144,1,r,100,i));
                track.add(makeEvent(176,1,127,0,i));
                track.add(makeEvent(128,1,r,100,i+2));
            }
            
            sequencer.setSequence(seq);
            sequencer.setTempoInBPM(220);
            sequencer.start();
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public static MidiEvent makeEvent(int a, int b, int c, int d, int tick){
        MidiEvent event = null;
        
        try{
            ShortMessage msg = new ShortMessage();
            msg.setMessage(a,b,c,d);
            event = new MidiEvent(msg, tick);
        
        }catch(Exception ex){}
            
        return event;
    }

    class drawPanel extends JPanel implements ControllerEventListener{
        boolean msg= false;

        public void controlChange(ShortMessage event){
            System.out.println("everything is fine");
            msg = true;
            frame.repaint();
        }

        public void paintComponent(Graphics g){
            if(msg == true){
                
                int r = (int) (Math.random()*250);
                int gr = (int) (Math.random()*250);
                int b = (int) (Math.random()*250);
                g.setColor(new Color(r,gr,b));

                int height = (int) ((Math.random()*120)+10);
                int width = (int) ((Math.random()*120)+10);
                int x = (int) ((Math.random()*40)+10);
                int y = (int) ((Math.random()*40)+10);
                g.fillRect(x,y,width,height);
                
                msg = false;
            }else{
                System.out.println("everything is not fine");
            }
        }
    }
}