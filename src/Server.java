import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {

    private int secretNumber;
    private int port = PlayerOneInputWindow.port;
    private String message;
    private boolean gameOver;

    public Server(int port) {
        this.port = port;
    }

    public Server(){

    }
    


    public void startServer() throws IOException {

        Random random = new Random();
        secretNumber = random.nextInt(10);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server starting... waiting for connection...");
            InetAddress ip = InetAddress.getLocalHost();
            serverSocket.setSoTimeout(30000);
            System.out.println("Server opened at " + ip.getHostAddress() + "nominated port: " + port + "Port: " + serverSocket.getLocalPort());

            Socket socket = serverSocket.accept();
            System.out.println("Connection established.");
            handleClientCommunication(socket);

        }catch (IOException e) {
            System.out.println("Connection failed.");
        }
        System.out.println("Server connection terminated.");
    }

    public void handleClientCommunication(Socket socket) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            gameOver = false;


            while (!gameOver && (message = reader.readLine()) != null) {
                System.out.println("Client guessed: " + message);
                int guess = Integer.parseInt(message);

                if (guess != secretNumber) {
                    System.out.println("Client guessed wrong.");
                    writer.println("Wrong number, try again!");
                } else {
                    System.out.println("Client guessed correctly.");
                    writer.println("Correct!");
                    gameOver = true;
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }







    public int getPort(){
        return port;
    }


    @Override
    public String toString() {
        return "Server: port: " + port;
    }
}

