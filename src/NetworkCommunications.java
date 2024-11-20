import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class NetworkCommunications {
    private static String ipAddress;
    private InetAddress ip;
    private int port;
    private ServerSocket serverSocket;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private String host;
    private String fromEnemy;
    private boolean isServer;
    private String shotToFire;
    private String toEnemy;
    static Player player;
    private Thread communicationsThread;
    private static Socket currentSocket;




    public NetworkCommunications(boolean isServer) {
        this.isServer = isServer;
    }





    public void startServer(int port) throws IOException {
        try  {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server starting... waiting for connection...");
            InetAddress ip = InetAddress.getLocalHost();
            ipAddress = ip.getHostAddress();
            System.out.println("Server opened at " + ip.getHostAddress() + "Port: " + port);
            ConfirmServer.display();

            Socket socket = serverSocket.accept();
            System.out.println("Connection established.");
            this.socket = socket;
            socket.setKeepAlive(true);



            if (socket != null && socket.isConnected()) {
                try {

                    System.out.println("display GameFX screen");
                    GameFX gameFX = new GameFX(BattleshipApp.getStage());
                    GameController controller = new GameController(gameFX, this);
                    //Thread incomingThread = new Thread(new IncomingReader());
                    //incomingThread.start();


                    handleCommunications(socket);

                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }else {
                System.out.println("Socket is null or not connected.");
            }
        } catch (IOException ex) {
            System.out.println("Connection failed: " + ex.getMessage());
            ex.printStackTrace();
        }


    }

    public void startClientConnection() throws Exception {

        System.out.println("Client started, trying to connect to server...");
        host = PlayerTwoInputWindow.getIpAddress();
        int cPort = PlayerTwoInputWindow.getPort();
        System.out.println("IP address: " + (host != null ? host : "Not available"));
        System.out.println(" IP address: " + host);
        System.out.println("port: " + cPort);

        try  {
            Socket socket = new Socket(host,cPort);
            System.out.println("Connected to server!");
            this.socket = socket;
            socket.setKeepAlive(true);

//Thread outgoingThread = new Thread(new OutgoingWriter());
            //outgoingThread.start();
            System.out.println("display GameFX screen");
            GameFX gameFX = new GameFX(BattleshipApp.getStage());
            GameController controller = new GameController(gameFX, this);

            handleCommunications(socket);

        } catch (IOException e) {
            System.out.println("Server not found!");
        }


    }

    public class IncomingReader implements Runnable{
        public void run(){
            synchronized (NetworkCommunications.class) {
                currentSocket = socket;
                System.out.println(socket);
            String fromEnemy;
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(currentSocket.getInputStream()))){
                while((fromEnemy=reader.readLine()) != null){
                    System.out.println("Enemy sent: " + fromEnemy);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }}


    public class OutgoingWriter implements Runnable{

        public void run(){
            synchronized (NetworkCommunications.class) {
                currentSocket = socket;

            try(PrintWriter writer = new PrintWriter(currentSocket.getOutputStream(),true)){
                String toEnemy = Translate.getSend();
                writer.println(toEnemy);
                System.out.println("sent to enemy: "+ toEnemy);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }}


    public void handleComms(Socket socket) throws IOException {

        try(PrintWriter writer = new PrintWriter(socket.getOutputStream(),true);
           BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){

               while((fromEnemy=reader.readLine()) != null){
                   toEnemy = Translate.getSend();
                   handleOutgoingCommunication(writer,toEnemy);

                   fromEnemy = reader.readLine();
                   if (fromEnemy == null) break;

                   handleIncomingCommunication(reader, fromEnemy);
               }

        }


    }

    public String handleCommunications(Socket socket){
        synchronized (NetworkCommunications.class) {
             currentSocket = socket;

        communicationsThread = new Thread(() -> {
            try (PrintWriter writer = new PrintWriter(currentSocket.getOutputStream(), true);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(currentSocket.getInputStream()))) {
                while (true) {
                    handleOutgoingCommunication(writer, toEnemy);

                    fromEnemy = reader.readLine();
                    if (fromEnemy == null) break;

                    handleIncomingCommunication(reader, fromEnemy);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        communicationsThread.start();
            return fromEnemy;




}}


    public String handleIncomingCommunication(BufferedReader reader, String fromEnemy) throws IOException {


        while ((fromEnemy= reader.readLine()) != null) {
            System.out.println("Enemy sent: " + fromEnemy);
        }

        return fromEnemy;

    }

    public void handleOutgoingCommunication(PrintWriter writer, String toEnemy) throws IOException {

        writer.println(toEnemy);
        System.out.println("Sent: " + toEnemy);
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
        return toEnemy;
    }

    public void setSendToEnemy(String sendToEnemy) {
        this.toEnemy = sendToEnemy;
    }

    public static String getIpAddress() {
        return ipAddress;
    }

    public static void setIpAddress(String ipAddress) {
        NetworkCommunications.ipAddress = ipAddress;
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

    public PrintWriter getWriter() {
        return writer;
    }

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public static Player getPlayer() {
        return player;
    }


    public String getToEnemy() {
        return toEnemy;
    }
}
