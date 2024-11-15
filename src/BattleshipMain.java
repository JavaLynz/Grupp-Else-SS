import java.util.List;
import java.util.Scanner;

import javafx.application.Application;


public class BattleshipMain {
    public static void main(String[] args) {

//        Application.launch(BattleshipApp.class);

//        Board playerBoard = new Board();
//        Board enemyBoard = new Board();

        Board board = new Board();
        Player player = new Player("player",true);
        player.getPlayerBoard().placeAllShips();
        player.getPlayerBoard().printMap();
        player.getEnemyBoard().printMap();

        player.getPlayerBoard().checkNumberOfShipsLeft();
        player.getEnemyBoard().checkNumberOfShipsLeft();


        while (true) {
            System.out.println("Tryck på Enter för att skjuta");
            player.shootPos();
        }
    }

}

