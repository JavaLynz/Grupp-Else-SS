public class Player {

    private boolean isClient;
    private boolean isServer;
    private boolean gameOn;

    //Om det inte är en client så är det en server
    public Player (boolean isClient) {
        this.isClient = isClient;
        this.isServer = !isClient;
    }


    public boolean isClient() {
        return isClient;
    }

    public void setClient(boolean client) {
        isClient = client;
    }

    public boolean isServer() {
        return isServer;
    }

    public void setServer(boolean server) {
        isServer = server;
    }

    public void takeTurns() {
        System.out.println(isClient? "Clients " : "Servers " + " turn.");
    }


}
