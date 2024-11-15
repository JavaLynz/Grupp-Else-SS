import java.util.List;
import java.util.Scanner;

import javafx.application.Application;


public class BattleshipMain {
    public static void main(String[] args) {

//        Application.launch(BattleshipApp.class);

        Board playerBoard = new Board();
        Board enemyBoard = new Board();


        Player player = new Player("player", playerBoard, enemyBoard, true);

        while (true) {
            System.out.println("Tryck på Enter för att skjuta");
            player.shootPos();
        }
    }

}

