import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        String secretWord = "kalle";

        try {
            System.out.println("Server starting... waiting for connection...");
            ServerSocket serversocket = new ServerSocket(6667);
            Socket socket = serversocket.accept();
            System.out.println("Connection established.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = reader.readLine()) != null) {
                if(message.equals(secretWord)) {
                    System.out.println("You are correct!");
                }else {
                    System.out.println("You are incorrect! Try again!");
                }
            }
        }catch (IOException e) {
        }
    }
}
