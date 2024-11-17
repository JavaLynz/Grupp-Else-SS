import java.util.List;
import java.util.Scanner;

import javafx.application.Application;


public class BattleshipMain {
    public static void main(String[] args) {

//        Application.launch(BattleshipApp.class);


//        Board playerBoard = new Board();
//        Board enemyBoard = new Board();

        Player player = new Player("player",true);

        player.getPlayerBoard().createShips();
        player.getPlayerBoard().placeAllShips();

        player.getEnemyBoard().printMap();
        player.getPlayerBoard().printMap();


        player.getEnemyBoard().checkNumberOfShipsLeft();
        player.getPlayerBoard().checkNumberOfShipsLeft();


        while (true) {
            System.out.println("Tryck på Enter för att skjuta");
            player.shootPos();
            player.getPlayerBoard().checkNumberOfShipsLeft();
        }
    }

}

