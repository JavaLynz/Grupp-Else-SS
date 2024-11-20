import javafx.application.Platform;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GameController {



    protected static Board playerBoard;
    protected static Board enemyBoard;
    private Player player;
    protected static boolean gameOn = true;
    private Network network;
    private Guesses guesses; //Needed??

    public GameController() {
                       ///Borde inte behöva den här
        player = new Player("q", false);
        guesses = new Guesses();
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
            playerBoard.createShips();
            playerBoard.placeAllShips();
            //update gameFX
            if (player.isClient() == true) {
                int [] guess = guesses.makeAGuess(player.getName());
                //Random random = new Random(); Ta bort??
                //int row = random.nextInt(10); //Bort??
                //int col = random.nextInt(10);   //Bort?     //// Placeholder till Jocelyn skrivit en metod.
                getPlayer().setLastX(guess [0]);
                getPlayer().setLastY(guess[1]);
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
                int [] guess = guesses.makeAGuess(player.getName());
                //Random random = new Random();
                //int row = random.nextInt(10);       //// Placeholder till Jocelyn skrivit en metod.
                //int col = random.nextInt(10);
                getPlayer().setLastX(guess[0]);
                getPlayer().setLastY(guess[1]);
                // send result of shot (playerState) and guess for translation
                toTextWindow=Translate.translateToSend(player); //updates
                // update textwindow

                if (!gameOn) {
                    System.out.println("Game over");
                    break;
                }

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
