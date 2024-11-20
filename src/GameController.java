import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GameController {



    protected static boolean gameOn = true;
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
        ////GameLoop gemensamt av gruppen
    public void gameLoop (Player player, AnchorPane pMap, AnchorPane pScore, AnchorPane eMap, AnchorPane eScore) throws IOException {
        // Variabler i loopen
        String fromEnemy = "";
        String toEnemy = "";
        String toTextWindow;
        int xToGuess;
        int yToGuess;


        System.out.println("nu kör vi!");

        Runnable createAndPlaceShips = new Runnable() {
            @Override
            public void run() {
                player.getPlayerBoard().createShips();
                player.getPlayerBoard().placeAllShips();
                updatePlayerMap(pMap,player.getPlayerBoard());

            }
        };
        Runnable updatePlayerBoard = new Runnable() {
            @Override
            public void run() {
                updatePlayerMap(pMap,player.getPlayerBoard());
                updatePlayerScore(pScore,player.getEnemyShipsDown());
            }
        };

        Runnable updateEnemyBoard = new Runnable() {
            @Override
            public void run() {
                updateEnemyMap(eMap,player.getEnemyBoard());
                updateEnemyScore(eScore, player.getPlayerShipsDown());

            }
        };
        Thread thread1 = new Thread(createAndPlaceShips);
        thread1.start();
        Thread thread2 = new Thread(updateEnemyBoard);
        thread2.start();
        Thread thread3 = new Thread(updatePlayerBoard);
        thread3.start();


            if (player.isClient() == true) {
                //network = BattleshipApp.getNetwork();
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
            }
            while (gameOn){

                fromEnemy = network.handleIncomingCommunication(network.getSocket());
                toTextWindow=Translate.translateReceived(player,fromEnemy); //updates enemyMap, GameOn, enemyShipsDown,
                System.out.println(toTextWindow);
                thread3.start();
                Random random = new Random();
                int row = random.nextInt(10);       //// Placeholder till Jocelyn skrivit en metod.
                int col = random.nextInt(10);
                player.setLastX(row);
                player.setLastY(col);
                assessGuess(player);
                toEnemy=Translate.translateToSend(player);
                thread2.start();
                System.out.println(toEnemy);
                toTextWindow="You shot at " + (toEnemy.substring(7,9)) + "..." + "\n";
                System.out.println(toTextWindow);
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
               player.getEnemyBoard().updateCell(player.getEnemyLastX(), player.getEnemyLastY(), 12);
               player.setPlayerState(12);

        }
        else {
            System.out.println("Träff!" + hitShip.getName() + "(Player.assessGuess)");
            player.getEnemyBoard().updateCell(player.getEnemyLastX(), player.getEnemyLastY(), 11);
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


    public void updateEnemyMap(AnchorPane anchorPane, Board enemyBoard){       /////Anna
        Image hit = new Image("explode.png");
        int k = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Node r = anchorPane.getChildren().get(k);
                k++;
                if (enemyBoard.getMap()[i][j]==11){
                    if (r  instanceof Rectangle) {
                        Rectangle currentRectangle = (Rectangle) r;
                        Platform.runLater(()->currentRectangle.setFill(new ImagePattern(hit)));
                    }
                } else if (enemyBoard.getMap()[i][j]==12) {
                    Rectangle currentRectangle = (Rectangle) r;
                    Platform.runLater(()->currentRectangle.setFill(Color.LIGHTGRAY));
                }
            }
        }
    }


    public void updateEnemyScore(AnchorPane anchorPane, int eScore){       /////Anna
        for (int i = 0; i < eScore; i++) {
            Node r = anchorPane.getChildren().get(i);
            if (r  instanceof Circle) {
                Circle currentCircle = (Circle) r;
                Platform.runLater(()->currentCircle.setFill(Color.DARKRED));
            }
        }
    } public void updatePlayerMap(AnchorPane anchorPane, Board pBoard){       /////Anna
        Image hit = new Image("explode.png");
        int k = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Node r = anchorPane.getChildren().get(k);
                k++;
                if (pBoard.getMap()[i][j]==11){
                    if (r  instanceof Rectangle) {
                        Rectangle currentRectangle = (Rectangle) r;
                        Platform.runLater(()->currentRectangle.setFill(new ImagePattern(hit)));
                    }
                } else if (pBoard.getMap()[i][j]==12) {
                    Rectangle currentRectangle = (Rectangle) r;
                    Platform.runLater(()->currentRectangle.setFill(Color.LIGHTGRAY));
                } else if (pBoard.getMap()[i][j]<=0) {
                }else if (pBoard.getMap()[i][j]<=5) {
                    Rectangle currentRectangle = (Rectangle) r;
                    Platform.runLater(()->currentRectangle.setFill(Color.DARKSLATEGREY));
                }
            }
        }
    }

    public void updatePlayerScore(AnchorPane anchorPane, int pScore){       /////Anna
        for (int i = 0; i < pScore; i++) {
            Node r = anchorPane.getChildren().get(i);
            if (r instanceof Circle) {
                Circle currentCircle = (Circle) r;
                Platform.runLater(()->currentCircle.setFill(Color.GREEN));
            }
        }

    public static Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        player = player;
    }

    }

