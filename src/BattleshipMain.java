import java.util.List;
import java.util.Scanner;

public class BattleshipMain {
    public static void main(String[] args) {

        Player player = new Player(true);

        while(true) {
            System.out.println("Tryck på Enter för att skjuta");
            player.shootPos();
        }
    }
}
