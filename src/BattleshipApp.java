import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class BattleshipApp extends Application {

    private static Stage window;
    private Player serverPlayer;
    private Player clientPlayer;
    private Board playerBoard;
    private Board enemyBoard;

    public static  Stage getStage() {
        return window;
    }

    public Player getClientPlayer() {
        return clientPlayer;
    }

    public Player getServerPlayer() {
        return serverPlayer;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene, scene1;
        window = primaryStage;
        window.setTitle("Welcome to Grupp Else's Battleship");

        Button button = new Button("Start a game");
        button.setStyle("-fx-background-color: #ffa500");
        button.setFont(new Font("Arial", 30));
        button.setOnAction(e->{
            button.setStyle("-fx-background-color: #fff");
            //get player's name and port;
            PlayerOneInputWindow.display();
            serverPlayer = new Player(PlayerOneInputWindow.getPlayerOneName(),playerBoard,enemyBoard,false);
            try {
                serverPlayer.startGame(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });

        Button button1 = new Button("Join a game");
        button1.setStyle("-fx-background-color: #ffa500");
        button1.setFont(new Font("Arial", 30));
        button1.setOnAction(e->{
            button1.setStyle("-fx-background-color: #fff");
            PlayerTwoInputWindow.display();
            Player clientPlayer = new Player(PlayerTwoInputWindow.getPlayerTwoName(),playerBoard, enemyBoard, true);
            try {
                clientPlayer.startGame(false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });

        Button closeButton = new Button("Quit");
        closeButton.setFont(new Font("Arial", 30));
        closeButton.setOnAction(e->{
            window.close();
            System.out.println("Goodbye, thank you for playing!");

        });


        Font fontBroadway = Font.loadFont("Broadway font.tff", 50);
        Label label = new Label("Welcome to Battleship!");
        label.setFont(fontBroadway);
        label.setStyle("-fx-font-weight: bold");

        Rectangle whiteRectangle = new Rectangle(200,200,550,600);
        whiteRectangle.setFill(Paint.valueOf("white"));
        whiteRectangle.minWidth(450);

        ImageView battleship = new ImageView("Battleship.png");
        battleship.fitHeightProperty().bind(whiteRectangle.widthProperty());
        battleship.fitWidthProperty().bind(whiteRectangle.widthProperty());

        HBox hbox = new HBox(button, button1, closeButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(50);

        VBox vbox = new VBox(label, battleship, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(50);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(whiteRectangle, vbox);

        scene = new Scene(layout, 700, 800);
        layout.setStyle("-fx-background-color: teal");
        layout.setAlignment(Pos.CENTER);

        window.setScene(scene);
        window.show();


    }


}
