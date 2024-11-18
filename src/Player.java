import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Player {

    private String name;
    private boolean isClient;
    private boolean isServer;
    private boolean gameOn;
    private Board playerBoard;
    private Board enemyBoard;
    private int enemyShipsDown = 0;
    private int lastX = 77;
    private int lastY = 77;
    //private int enemyLastX = 77;
    //private int enemyLastY = 77;
    //private int playerState = 77;
    //private int enemyState = 77;
    private int speedDelay = 3;
    private Random random;
    private Set<String> guessedPositions; //Lagrar gissningarna i ett sett så att gissningarna inte kan bli dubletter.

    //Konstruktor
    //Om det inte är en client så är det en server
    public Player(String name, boolean isClient) {
        this.name = name;
        this.isClient = isClient;
        this.isServer = !isClient;
        this.playerBoard = new Board();
        this.enemyBoard = new Board();
        this.random = new Random();
        this.guessedPositions = new HashSet<>();
    }


    public void takeTurns() {
        System.out.println(isClient ? "Clients " : "Servers " + " turn.");
    }

    //Startar spelet
    public void startGame() {
        System.out.println("Starting game...");
        System.out.println(name + " stars the game.");
        gameOn = true;

        playerBoard.createShips();
        playerBoard.placeAllShips();
        System.out.println(name + " has placed their ships.");
        gameLoop();
    }

    // Board playerBoard = new Board();
    // Board enemyBoard = new Board();

        // starta ny tråd och kör metod för att placera skepp
/*
• ett hangarfartyg (5x1 ruta)
• två slagskepp (4x1 ruta)
• tre kryssare (3x1 ruta)
• fyra ubåtar (2x1 ruta
        Ship ship5 = new Ship(5);
        Ship ship4a = new Ship(4);
        Ship ship4b = new Ship(4);
        Ship ship3a = new Ship(3);
        Ship ship3b = new Ship(3);
        Ship ship3c = new Ship(3);
        Ship ship2a = new Ship(2);
        Ship ship2b = new Ship(2);
        Ship ship2c = new Ship(2);
        Ship ship2d = new Ship(2);*/

        //Byter scen till GameFX och skickar med playerBoard.getMap(), enemyBoard.getMap();


    //Loop för spelet
    public void gameLoop() {
        gameOn = true;
        System.out.println("Game loop started");
        System.out.println(name + " turn.");
        while (gameOn) {
            if (isClient) {
                int[] guess = makeRandomGuess(); // Client gör första random gissningen
               // Ship hitShip  = enemyBoard.hit(guess[0],guess[1]);
               // if (hitShip)
                //System.out.println(hit ? "HIT!" : "MISS!");
            } else {
                System.out.println("Opponents turn....");
            }
            checkGameStatus();
        }
    }
//Test


        //Gissar slumpmässig position
    public int[] makeRandomGuess() {
        int row;
        int col;
        String guess;

        do {
            row = random.nextInt(10); //Slumpar rad 0-9
            col = random.nextInt(10); //Slumpar column 0-9
            guess = row + ", " + col;
        } while (guessedPositions.contains(guess)); //Kontrollerar om gissningen redan gjorts
        guessedPositions.add(guess); //Lägger till den i Set
        lastX = row;
        lastY = col;
        System.out.println(name + " gissade på: Rad " + row + ", " + col);
        return new int[] {row, col};
        }


        public void checkGameStatus() {
            boolean allEnemyShipSunk = enemyBoard.getFleet().stream().allMatch(ship -> !ship.isAlive());
            if (allEnemyShipSunk) {
                gameOn = false;
                System.out.println(name + " is the winner! All of the enemies ships have been sunk!");
            }
        }



/*
            if (isClient) {
                //shoot first shot/ guess = GuessRandom;
            } else {
                //wait for first strike from opponent
                //translate string to guess
                //AssessGuess;
                // take next guess / strike
                //if(
            }

            //update array
            //update map


        }
    }

 *

 ///  public static void guessRandom()
 */



//
//        Random random =  new Random();
//        int row = random.nextInt(10);
//        int col = random.nextInt(10);
//
//        System.out.println("Sköt på position " + "Rad: " + row + " Kolumn: " + col);
//        if (playerboard.hit(row, col)) {
//            System.out.println("Träff!");
//        }
//        else {
//            System.out.println("Miss!");
//        }
//        playerboard.printMap();
//    }

    public void shootPos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Välj en rad");
        int targetRow = sc.nextInt();
        System.out.println("Välj en kolumn");
        int targetCol = sc.nextInt();

        Ship hitShip = playerBoard.hit(targetRow, targetCol);

//        if (hitShip != null) {
////            System.out.println("Träff! " + hitShip.getName() + "(Player.shootPos)");
//        } else {
//            System.out.println("Miss!");
//        }

        playerBoard.printMap();
    }

    public void guessLogic() {

    }


    //public String assessGuess(){
    //is it a hit / miss / sink
    //if ("hit")
    //CheckShipStatus();
    //else return "miss";
    //return "hit or miss";


    //public void CheckShipStatus(){
    // check if all ships are Alive
    // if all ships are sunk
    // System.out.println("checking status of all ships..");
    //if ( > 1 ship for each player isAlive) {
    // do nothing?
    //}else {
    // gameOn = false
    // EndGame();


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

   /* public int getEnemyLastX() {
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

    */

    public int getSpeedDelay() {
        return speedDelay;
    }

    public void setSpeedDelay(int speedDelay) {
        this.speedDelay = speedDelay;
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


