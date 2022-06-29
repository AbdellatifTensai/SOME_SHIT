import java.io.*;
import java.net.*;

class Client{
    public static void main(String[] args){
        new Client().go();
    }

    public void go(){
        try{
            Socket socket = new Socket("127.0.0.1", 4242);
            InputStreamReader streamReader = new InputStreamReader(socket.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            String msg = reader.readLine();
            System.out.println("it worked!" + msg);
            reader.close();
        }catch(IOException ex){ex.printStackTrace();}
    }
}