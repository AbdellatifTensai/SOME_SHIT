import java.io.*;
import java.net.*;

class Server{
    public static void main(String[] args){
        new Server().go();
    }

    public void go(){
        try{
            ServerSocket serverSocket = new ServerSocket(4242);

            while(true){
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                writer.println("hi");
                writer.close();
                System.out.println("i think it worked?");
            }
        }catch(IOException ex){ex.printStackTrace();}
    }
}