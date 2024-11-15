import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private String host;
    Scanner scanner = new Scanner(System.in);
    private int guess;
    private boolean gameOver;
    private String messageFromServer;

    public Client(){

    }


    public void startClientConnection () {

        System.out.println("Client started, trying to connect to server...");
        host = "192.168.0.33";
        System.out.println(" IP address: " + host);

        try (Socket socket = new Socket(host, 6667)){

            System.out.println("Connected to server!");
            handleServerCommunication(socket);

        }catch (IOException e){
            System.out.println("Server not found!");
        }
    }

    public void handleServerCommunication(Socket socket) throws IOException {
        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
         ) {
            messageFromServer = "";
            System.out.println("Make a guess, an integer between 1 and 10.");
            gameOver = false;
            while (!gameOver){
                System.out.println("Your guess: ");
                guess = scanner.nextInt();
                writer.println(guess);
                messageFromServer = reader.readLine();
                System.out.println("Server messaged: " + messageFromServer);

                if(messageFromServer.contains("Correct")){
                    System.out.println("Server messaged that you are correct!");
                    System.out.println("Thank you for playing");
                    gameOver = true;
                    break;
                }

            }
            System.out.println("Thank you for playing! Exiting..");
        }
    }
    @Override
    public String toString() {
        return "Client";
    }


}
