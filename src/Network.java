import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Network {

    private static String ipAddress;
    private InetAddress ip;
    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter out;
    private String host;
    private static String fromEnemy;
    private boolean isServer;
    private String shotToFire;
    private String sendToEnemy;

    public Network(boolean isServer) {
        this.isServer = isServer;
    }


    public void setupGame(boolean isServer) throws Exception {
        if (isServer) {

            startServer(PlayerOneInputWindow.getPort());
            //player = new Player(PlayerTwoInputWindow.getPlayerTwoName(),false);

        } else {

            startClientConnection();
            //player = new Player(PlayerOneInputWindow.getPlayerOneName(),true);
        }
    }


    public void startServer(int port) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server starting... waiting for connection...");
            InetAddress ip = InetAddress.getLocalHost();
            ipAddress = ip.getHostAddress();
            System.out.println("Server opened at " + ip.getHostAddress() + "Port: " + port);
            ConfirmServer.display();

            socket = serverSocket.accept();
            System.out.println("Connection established.");
            if (socket.isConnected()) {
                try {
                    System.out.println("display GameFX screen");
                    GameFX.display();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }


    public void startClientConnection() throws Exception {

        System.out.println("Client started, trying to connect to server...");
        host = PlayerTwoInputWindow.getIpAddress();
        int cPort = PlayerTwoInputWindow.getPort();
        System.out.println("IP address: " + (host != null ? host : "Not available"));
        System.out.println(" IP address: " + host);
        System.out.println("port: " + cPort);

        try (Socket socket = new Socket(host, cPort)) {

            System.out.println("Connected to server!");

        } catch (IOException e) {
            System.out.println("Server not found!");
        }
        System.out.println("display GameFX screen");
        GameFX.display();
    }

    public String handleIncomingCommunication(Socket socket) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {


            while ((fromEnemy= reader.readLine()) != null) {
                System.out.println("Enemy sent: " + fromEnemy);
            }

        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return fromEnemy;

    }

    public void handleOutgoingCommunication(Socket socket, String sendToEnemy) throws IOException {
        try (PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

            while (!socket.isClosed()) {
                try {


                    writer.println(sendToEnemy);
                    System.out.println(sendToEnemy);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }


    // getters and setters
    public boolean isServer() {
        return isServer;
    }

    public void setServer(boolean server) {
        isServer = server;
    }

    public String getShotToFire() {
        return shotToFire;
    }

    public void setShotToFire(String shotToFire) {
        this.shotToFire = shotToFire;
    }



    public String getSendToEnemy() {
        return sendToEnemy;
    }

    public void setSendToEnemy(String sendToEnemy) {
        this.sendToEnemy = sendToEnemy;
    }

    public static String getIpAddress() {
        return ipAddress;
    }

    public static void setIpAddress(String ipAddress) {
        Network.ipAddress = ipAddress;
    }

    public InetAddress getIp() {
        return ip;
    }

    public void setIp(InetAddress ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

   }