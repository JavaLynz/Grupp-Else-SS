import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private String secretWord = "kalle";


    public void StartServer(){

        try {
            System.out.println("Server starting... waiting for connection...");
            ServerSocket serversocket = new ServerSocket(6667);
//            Socket socket = serversocket.accept();
//            System.out.println("Connection established.");

        }catch (IOException e) {
            System.out.println("Connection failed.");
        }
    }
}
