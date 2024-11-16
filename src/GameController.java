import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.List;

public class GameController {



    protected static Board playerBoard = new Board();
    protected static Board enemyBoard = new Board();
    private Server server;
    private Client client;
    private Player player;
    protected static boolean gameOn = true;

    public GameController() {
        this.server = server;
        this.client = client;                       ///Borde inte behöva den här
        player = new Player("q", false);
    }

    public void setUpGame(boolean isServer) throws IOException {
        if (isServer) {
            server = new Server();
            server.startServer();
            player = new Player(PlayerTwoInputWindow.getPlayerTwoName(),true);
            System.out.println("Starting game...");
            //Board playerBoard = new Board();
            Board enemyBoard = new Board();
            //playerBoard.placeShip();

        } else {
            client = new Client();
            client.startClientConnection();
            System.out.println("Starting game...");
            player = new Player(PlayerOneInputWindow.getPlayerOneName(),false);
            //Board playerBoard = new Board();
            //Board enemyBoard = new Board();
            //playerBoard.placeShip();
        }
    }

    public void annasLoop (){
        System.out.println("nu kör vi!");

        ListView<String> listView = new ListView<>();
        ObservableList<String> data = FXCollections.observableArrayList();
        listView.setItems(data);
        data.add("String from translation");

            //place ship
            //update gameFX
            if (player.isClient() == true) {
                // get coordinates to guess
                //translate coordinates to string
                // update textwindow
                //send string to server to send to client
                //server/client communicate
            }
            while (gameOn){
                //wait for communication from opponent
                // send response to translation
                        //Send to string and update textwindow
                        //get result of shot and update map & save to return
                        //update ships alive
                        //update game status
                            //if false end game else
                //take a guess
                // send result of shot and guess for translation
                // update textwindow
                // send string to server/client to sent to opponent
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        player = player;
    }
}
