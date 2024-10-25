import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try {
            System.out.println("Client started, connecting...");
            Socket socket = new Socket("localhost", 6667);
            System.out.println("Connected to server!");
            PrintWriter write = new PrintWriter(socket.getOutputStream(), true);
            while(true) {
                System.out.println("Guess the number");
                Scanner scanner = new Scanner(System.in);

            }


        }catch (IOException e){

        }
    }
}
