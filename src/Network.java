import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Network {

    private String ipAddress;
    private InetAddress ip;
    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter out;
    private String shotFired = "";


    public Network(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = PlayerOneInputWindow.port;
    }

    public void startServer() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server starting... waiting for connection...");
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println("Server opened at " + ip.getHostAddress() + "Port: " + port);

            while (true) {
                socket = serverSocket.accept();
                System.out.println("Connection established.");
                }

        }catch (IOException e) {
            System.out.println("Connection failed.");
        }
        System.out.println("Server connection terminated.");
    }



public void handleCommunication(Socket socket) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

        while ((shotFired = reader.readLine()) != null) {
            System.out.println("Opponent shot at: " + shotFired);
            // run translate method
            }

    } catch (IOException e) {
        System.out.println("An error occurred: " + e.getMessage());
    }

    }
}

