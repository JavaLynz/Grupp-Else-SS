import java.util.ArrayList;
import java.util.List;

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







    public void startGame () {
        //Avgör is clien/server
        //
        //

        System.out.println("Starting game...");
        Board playerBoard = new Board();
        Board enemyBoard = new Board();

        // starta ny tråd och kör metod för att placera skepp
/*
• ett hangarfartyg (5x1 ruta)
• två slagskepp (4x1 ruta)
• tre kryssare (3x1 ruta)
• fyra ubåtar (2x1 ruta*/
        Ship ship5 = new Ship(5);
        Ship ship4a = new Ship(4);
        Ship ship4b = new Ship(4);
        Ship ship3a = new Ship(3);
        Ship ship3b = new Ship(3);
        Ship ship3c = new Ship(3);
        Ship ship2a = new Ship(2);
        Ship ship2b = new Ship(2);
        Ship ship2c = new Ship(2);
        Ship ship2d = new Ship(2);

        playerBoard.placeShip(ship5);

        //Byter scen till GameFX och skickar med playerBoard.getMap(), enemyBoard.getMap();



//        GameLoop();
    }

    public void GameLoop(boolean isClient){
        gameOn = true;
        System.out.println("Game loop started");
    while (gameOn) {

            if (isClient = true) {
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

    public void GuessRandom(){

    }

    public void GuessLogik(){

    }


//    public AssessGuess(){
//        //is it a hit / miss / sink
//        if (hit)
//        CheckShipStatus();
//        else return "miss";
//    }

    public void CheckShipStatus(){
        // check if all ships are Alive
        // if all ships are sunk
        System.out.println("checking status of all ships..");



        //if ( > 1 ship for each player isAlive) {
        // do nothing?
        //}else {
        // gameOn = false
        // EndGame();
    }

}
