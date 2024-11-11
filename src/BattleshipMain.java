import javafx.application.Application;

public class BattleshipMain {


    public static void main(String[] args) {

        Application.launch(BattleshipApp.class);


        Player player1 = new Player(PlayerOneInputWindow.playerOneName, PlayerOneInputWindow.isClient());

        System.out.println(player1.getName() + " is server: " + player1.isServer());

        Player player2 = new Player(PlayerTwoInputWindow.playerTwoName, PlayerTwoInputWindow.isClient());

        System.out.println(player2.getName() + " is server: " + player2.isServer());










    }





}
