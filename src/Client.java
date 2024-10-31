import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public void startClientConnection () {

        try {

            System.out.println("Client started, trying to connect...");
            Socket socket = new Socket("localhost", 6667);
            System.out.println("Connected to server!");

        }catch (IOException e){
            System.out.println("Server not found!");
        }
    }
}
