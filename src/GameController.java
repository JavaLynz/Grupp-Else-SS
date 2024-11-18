import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GameController {



    //protected static Board playerBoard = new Board();
    //protected static Board enemyBoard = new Board();
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
        // Variabler i loopen
        String fromEnemy = "";
        String toEnemy = "";
        String toTextWindow;
        int xToGuess;
        int yToGuess;



        System.out.println("nu kör vi!");

/*
        ListView<String> listView = new ListView<>();                       //oklart var detta ska ligga.
        ObservableList<String> data = FXCollections.observableArrayList();
        listView.setItems(data);
        data.add("String from translation");
*/

            //place ship - Niklas
            //update gameFX
            if (player.isClient() == true) {
                //take a guess and update:   ----- Jocelyn
                Random random = new Random();
                int row = random.nextInt(10);
                int col = random.nextInt(10);       //// Placeholder till Jocelyn skrivit en metod.
                getPlayer().setLastX(row);
                getPlayer().setLastY(col);
                // send result of shot (playerState) and guess for translation ---- ny metod
                toEnemy=Translate.translateToSend(player);
                //send string toEnemy to server to send to client - Lynsey
                toTextWindow="You shot at " + (toEnemy.substring(7,9)) + "..." + "/n";
                // update textWindow------
            }
            while (gameOn){
                //wait for communication from opponent  - Lynsey
                toTextWindow=Translate.translateReceived(player,fromEnemy); //updates enemyMap, GameOn, enemyShipsDown,
                // update textwindow                                        //  enemyLastX, enemyLastY
                //update map/score
                // behövs nog metod för kolla motståndarens gissningen och uppdaterar player.setPlayerState
                //take a guess and update:
                Random random = new Random();
                int row = random.nextInt(10);       //// Placeholder till Jocelyn skrivit en metod.
                int col = random.nextInt(10);
                getPlayer().setLastX(row);
                getPlayer().setLastY(col);
                // send result of shot (playerState) and guess for translation
                toTextWindow=Translate.translateToSend(player); //updates
                // update textwindow
                try {
                    Thread.sleep(player.getSpeedDelay()*1000);
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }

                // send string to server/client to sent to opponent
        }
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
