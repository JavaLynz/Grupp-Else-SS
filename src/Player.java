import java.util.Scanner;

public class Player {

    private String name;
    private boolean isClient;
    private boolean isServer;
    private boolean gameOn;
    private Board playerBoard;
    private Board enemyBoard;
    private int enemyShipsDown=0;
    private int playerShipsDown=0;
    private int lastX=1;
    private int lastY=1;
    private int enemyLastX=5;
    private int enemyLastY=5;
    private int playerState=77;
    private int enemyState=77;
    private int speedDelay=3;


    //Om det inte är en client så är det en server
    public Player() {
        this.name = name;
        this.isClient = isClient;
        this.isServer = !isClient;
        this.playerBoard = new Board();
        this.enemyBoard = new Board();
        this.gameOn = gameOn;
    }


    public String getName() {
        return name;
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

    public void setPlayerBoard(Board board) {
        this.playerBoard = playerBoard;
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public void setEnemyBoard(Board board) {
        this.enemyBoard = enemyBoard;
    }

    public Board getEnemyBoard() {
        return enemyBoard;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGameOn() {
        return gameOn;
    }

    public void setGameOn(boolean gameOn) {
        this.gameOn = gameOn;
    }

    public int getEnemyShipsDown() {
        return enemyShipsDown;
    }

    public void setEnemyShipsDown(int enemyShipsDown) {
        this.enemyShipsDown = enemyShipsDown;
    }

    public int getLastY() {
        return lastY;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }

    public int getLastX() {
        return lastX;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public int getEnemyLastX() {
        return enemyLastX;
    }

    public void setEnemyLastX(int enemyLastX) {
        this.enemyLastX = enemyLastX;
    }

    public int getEnemyLastY() {
        return enemyLastY;
    }

    public void setEnemyLastY(int enemyLastY) {
        this.enemyLastY = enemyLastY;
    }

    public int getPlayerState() {
        return playerState;
    }

    public void setPlayerState(int playerState) {
        this.playerState = playerState;
    }

    public int getEnemyState() {
        return enemyState;
    }

    public void setEnemyState(int enemyState) {
        this.enemyState = enemyState;
    }

    public int getSpeedDelay() {
        return speedDelay;
    }

    public void setSpeedDelay(int speedDelay) {
        this.speedDelay = speedDelay;
    }

    public int getPlayerShipsDown() {
        return playerShipsDown;
    }

    public void setPlayerShipsDown(int playerShipsDown) {
        this.playerShipsDown = playerShipsDown;
    }


    public void guessLogic() {

    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", isClient=" + isClient +
                ", isServer=" + isServer +
                ", gameOn=" + gameOn +
                ", playerBoard=" + playerBoard +
                ", enemyBoard=" + enemyBoard +
                '}';
    }
}


