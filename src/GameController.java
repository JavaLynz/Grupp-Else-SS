import javafx.application.Platform;

import java.io.IOException;

public class GameController {



    protected static Board playerBoard;
    protected static Board enemyBoard;
    private Player player;
    protected static boolean gameOn = true;
    private Network network;

    public GameController() {
        this.server = server;
        this.client = client;                       ///Borde inte behöva den här
        player = new Player("q", false);
    }

    public void setUpGame(boolean isServer) throws Exception {
        if (isServer) {
            network = new Network(PlayerOneInputWindow.getPort());
            network.startServer();
            player = new Player(PlayerTwoInputWindow.getPlayerTwoName(),false);
            System.out.println("Starting game...");

        } else {
            network = new Network(PlayerOneInputWindow.getPort());
            network.startClientConnection();
            player = new Player(PlayerOneInputWindow.getPlayerOneName(),true);
            System.out.println("Starting game...");


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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        player = player;
    }
}
