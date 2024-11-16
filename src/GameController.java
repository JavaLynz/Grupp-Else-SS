import javafx.application.Platform;

import java.io.IOException;

public class GameController {



    protected static Board playerBoard = new Board();
    protected static Board enemyBoard = new Board();
    private Server server;
    private Client client;
    private Player player;
    protected static boolean gameOn = true;



    public void setUpGame(boolean isServer) throws IOException {
        if (isServer) {
            server = new Server();
            server.startServer();
            player = new Player(PlayerTwoInputWindow.getPlayerTwoName(),false);
            System.out.println("Starting game...");

        } else {
            client = new Client();
            client.startClientConnection();
            player = new Player(PlayerOneInputWindow.getPlayerOneName(),true);
            System.out.println("Starting game...");


        }
    }



    public void gameLoop(boolean isClient) {
        gameOn = true;
        System.out.println("Game loop started");
        while (gameOn) {

            if (isClient = true) {
                Player.guessRandom();
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


//        if(isClientTurn) {
//            guessRandom(clientPlayer);  // first turn
//            guessLogic(clientPlayer);
//            receiveTranslateGuess(serverPlayer);
//            assessGuess(serverPlayer);
//            updateEnemyBoard(clientPlayer)      // thread 1?
//            updatePlayerBoard(serverPlayer)     // thread 2?
//            confirmGuess(serverPlayer);
//            switchPlayerTurn();
//        }else {
//            guessRandom(serverPlayer);  // first turn
//            guessLogic(serverPlayer);
//            receiveGuess(clientPlayer);
//            translateGuess(clientPlayer);
//            assessGuess(clientPlayer);
//            updateEnemyBoard(serverPlayer)      // thread 1?
//            updatePlayerBoard(clientPlayer)     // thread 2?
//            confirmGuess(clientPlayer);
//            switchPlayerTurn();
//       }
    }

    public static void endGameClose(){
        boolean result = QuitConfirmBox.display();
        if (result){
            Platform.exit();
        }


    }
    public Board getPlayerBoard() {
        return playerBoard;
    }

    public void setPlayerBoard(Board playerBoard) {
        this.playerBoard = playerBoard;
    }

    public Board getEnemyBoard() {
        return enemyBoard;
    }

    public void setEnemyBoard(Board enemyBoard) {
        this.enemyBoard = enemyBoard;
    }
}
