import javafx.application.Platform;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GameController {



    protected static Board playerBoard;
    protected static Board enemyBoard;
    private static Player player;
    protected static boolean gameOn = true;
    private Translate translate = new Translate();
    private NetworkCommunications network;
    private GameFX gameFX;

    public GameController(GameFX gameFX, NetworkCommunications network) {
        this.gameFX = gameFX;
        this.network = network;
        gameOn = true;

    }

    public void setupGame(boolean isServer) throws Exception {
        if (isServer) {

            network.startServer(PlayerOneInputWindow.getPort());
            player = new Player(PlayerOneInputWindow.getPlayerOneName(),false);
            startGame();

        } else {

            network.startClientConnection();
            player = new Player(PlayerTwoInputWindow.getPlayerTwoName(),true);
            startGame();
        }
    }

    private void startGame() throws Exception {
        gameFX.display(player, this);
    }

    public void gameLoop () throws IOException {
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
         /* MyRunnable myRunnable = new MyRunnable(); // kallas det
        Thread thread1 = new Thread(myRunnable);
        thread1.start();
        MyThread myThread = new MyThread();
        myThread.start();
        Thread thread2 = new Thread(myRunnable);
        thread2.start(->{ player.getPlayerBoard().createShips();
            player.getPlayerBoard().placeAllShips();  });

        Platform.runLater(GameFX.pBoard()); */

        Runnable createAndPlaceShips = new Runnable() {
            @Override
            public void run() {
                player.getPlayerBoard().createShips();
                        player.getPlayerBoard().placeAllShips();
            }
        };
        Thread thread1 = new Thread(createAndPlaceShips);
        thread1.start();


            //place ship - Niklas
        // -------- tråd för att placera skep???


            //update gameFX
            if (player.isClient() == true) {

                //take a guess and update:   ----- Jocelyn
                Random random = new Random();
                int row = random.nextInt(10);
                int col = random.nextInt(10);       //// Placeholder till Jocelyn skrivit en metod.
                player.setLastX(row);
                player.setLastY(col);
                System.out.println(toEnemy);
                toEnemy=translate.translateToSend(player);
                network.handleCommunications(network.getSocket());//sendToEnemy??
                getPlayer().setLastX(row);
                getPlayer().setLastY(col);
                // send result of shot (playerState) and guess for translation ---- ny metod
                toEnemy=translate.translateToSend(player);
                network.handleCommunications(network.getSocket()); //send toEnemy??
                toTextWindow="You shot at " + (toEnemy.substring(7,9)) + "..." + "/n";
                System.out.println(toTextWindow);
                // update textWindow------
            }
            while (gameOn){

                fromEnemy = network.handleCommunications(network.getSocket());
                translate.translateReceived(player,fromEnemy);
                toTextWindow=translate.translateReceived(player,fromEnemy); //updates enemyMap, GameOn, enemyShipsDown,
                System.out.println(toTextWindow);
                //wait for communication from opponent  - Lynsey
                fromEnemy=network.getToEnemy();
                toTextWindow=translate.translateReceived(player,fromEnemy); //updates enemyMap, GameOn, enemyShipsDown,
                // update textwindow                                        //  enemyLastX, enemyLastY
                //update map/score
                // behövs nog metod för kolla motståndarens gissningen och uppdaterar player.setPlayerState
                //take a guess and update:
                Random random = new Random();
                int row = random.nextInt(10);       //// Placeholder till Jocelyn skrivit en metod.
                int col = random.nextInt(10);
                player.setLastX(row);
                player.setLastY(col);
                // send result of shot (playerState) and guess for translation
                toEnemy=translate.translateToSend(player); //updates
                System.out.println(toEnemy);
                toTextWindow="You shot at " + (toEnemy.substring(7,9)) + "..." + "\n";
                System.out.println(toTextWindow);
                toEnemy= translate.translateToSend(player);
                //updates
                toTextWindow="You shot at " + (toEnemy.substring(7,9)) + "..." + "/n";
                // update textwindow
                //update gameFX
                try {
                    Thread.sleep(player.getSpeedDelay()*1000);
                } catch (InterruptedException e){
                    throw new RuntimeException(e);
                }

                // send string to server/client to sent to opponent
        }


    }


    public void assessGuess(Player player) {
        Ship hitShip = player.getEnemyBoard().hit(player.getEnemyLastX(),player.getEnemyLastY());

        if(hitShip == null) {
            System.out.println("Miss! Position: " + player.getEnemyLastX() + "," + player.getEnemyLastY() + "(Player.assessGuess)");
               player.getEnemyBoard().updateCell(getPlayer().getEnemyLastX(), getPlayer().getEnemyLastY(), 12);
               player.setPlayerState(12);

        }
        else {
            System.out.println("Träff!" + hitShip.getName() + "(Player.assessGuess)");
            enemyBoard.updateCell(getPlayer().getEnemyLastX(), getPlayer().getEnemyLastY(), 11);
            player.setPlayerState(11);

            if(!hitShip.isAlive()) {
                System.out.println("Sänkte skepp " + hitShip.getName() + "(Player.assessGuess)");
                player.setEnemyShipsDown(player.getEnemyShipsDown()+1);
                player.setPlayerState(55);
            }
        }
    }

    public static void endGameClose(){
        boolean result = QuitConfirmBox.display();
        if (result){
            Platform.exit();
        }


    }

    public  void updateBoard(){


    }

    public static Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        player = player;
    }

    }

