import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.*;
import java.awt.image.*;
import java.io.*;

class readImageFromURL{
    static BufferedReader br;
    public static void main(String[] args){
        for(int x=4700;x<=4900;x++){
            try{
            URL link = new URL("https://prnt.sc/gb"+ x);
            URLConnection url = link.openConnection();
            url.addRequestProperty("User-Agent", "Mozilla/5.0");
            br = new BufferedReader(new InputStreamReader(url.getInputStream()));
            }catch(Exception e){e.printStackTrace();}
            
            Pattern p = Pattern.compile("\\b((?:https?|ftp|file)://image[-a-zA-Z0-9+&@#/%?=~_|!:, .;]*[-a-zA-Z0-9+&@#/%=~_|])", Pattern.CASE_INSENSITIVE);
            String input;
            String output;
            try{
                if((input = br.readLine()) != null){
                    Matcher m = p.matcher(input);
                    if(m.find()){
                        output = input.substring(m.start(), m.end());
                        System.out.println(output);
                        BufferedImage img = ImageIO.read(new URL(output));
                        ImageIO.write(img, "png", new File("image" + x +".png"));   
                    }
                }
            }catch(Exception ex){ex.printStackTrace();}
        }
    }
}