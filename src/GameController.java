import javafx.application.Platform;

import java.io.IOException;

public class GameController {

    private Board playerBoard = new Board();
    private Board enemyBoard = new Board();
    private Server server;
    private Client client;
    private Player clientPlayer = new Player(PlayerOneInputWindow.getPlayerOneName(),false);
    private Player serverPlayer = new Player(PlayerTwoInputWindow.getPlayerTwoName(),true);
    protected static boolean gameOn = true;



    public void setUpGame (boolean isServer) throws IOException {
        if (isServer) {
            server = new Server();
            server.startServer();
            System.out.println("Starting game...");
            Board playerBoard = new Board();
            Board enemyBoard = new Board();
            //playerBoard.placeShip();
        } else {
            client = new Client();
            client.startClientConnection();
            System.out.println("Starting game...");
            Board playerBoard = new Board();
            Board enemyBoard = new Board();
            //playerBoard.placeShip();
        }
    /*
    public void gameLoop(){

    while(GameOn) {
        if(isClientTurn) {
            guessRandom(clientPlayer);  // first turn
            guessLogic(clientPlayer);
            receiveTranslateGuess(serverPlayer);
            assessGuess(serverPlayer);
            updateEnemyBoard(clientPlayer)      // thread 1?
            updatePlayerBoard(serverPlayer)     // thread 2?
            confirmGuess(serverPlayer);
            switchPlayerTurn();
        }else {
            guessRandom(serverPlayer);  // first turn
            guessLogic(serverPlayer);
            receiveGuess(clientPlayer);
            translateGuess(clientPlayer);
            assessGuess(clientPlayer);
            updateEnemyBoard(serverPlayer)      // thread 1?
            updatePlayerBoard(clientPlayer)     // thread 2?
            confirmGuess(clientPlayer);
            switchPlayerTurn();
       }

       public void GameLoop(boolean isClient){
        gameOn = true;
        System.out.println("Game loop started");
    while (gameOn) {

            if (isClient = true) {
                Player.GuessRandom();
            } else {
                //wait for first strike from opponent
                //translate string to guess
                //AssessGuess;
                // take next guess / strike
                //if(
            }

            //update array
            //update map


     */
    }
    public static void endGameClose(){
        boolean result = QuitConfirmBox.display();
        if (result){
            Platform.exit();
        }


    }

}
