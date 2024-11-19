import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public static  Stage getStage() {
        return window;
    }
    private static Network network;

    public static Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public static Stage getWindow() {
        return window;
    }

    public static void setWindow(Stage window) {
        BattleshipApp.window = window;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Scene scene;
        window = primaryStage;
        window.setTitle("Welcome to Grupp Else's Battleship");

        Button button = new Button("Start a game");
        button.setStyle("-fx-background-color: #ffa500");
        button.autosize();
        button.setFont(new Font("Arial", 20));
        button.setOnAction(e -> {
            button.setStyle("-fx-background-color: #fff");
            //get player's name and port;
            PlayerOneInputWindow.display();
            network = new Network(true);



            try {
                network.setupGame(true);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        Button button1 = new Button("Join a game");
        button1.setStyle("-fx-background-color: #ffa500");
        button1.setFont(new Font("Arial", 20));
        button1.autosize();
        button1.setOnAction(e -> {
            button1.setStyle("-fx-background-color: #fff");
            PlayerTwoInputWindow.display();
            network = new Network(false);

            try {
                network.setupGame(false);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }


        });

        Button closeButton = new Button("Quit");
        closeButton.setFont(new Font("Arial", 20));
        closeButton.autosize();
        closeButton.setOnAction(e -> {
            QuitConfirmBox.display();
        });

        Label label = new Label("Battleship!");
        label.setFont(new Font("Broadway", 40));
        label.setStyle("-fx-font-weight: bold");

        Rectangle whiteRectangle = new Rectangle(200, 200, 300, 300);
        whiteRectangle.setFill(Paint.valueOf("white"));
        whiteRectangle.minWidth(300);

        ImageView battleship = new ImageView("Battleship.png");
        battleship.fitHeightProperty().bind(whiteRectangle.widthProperty());
        battleship.fitWidthProperty().bind(whiteRectangle.widthProperty());

        HBox hbox = new HBox(button, button1, closeButton);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(30);

        VBox vbox = new VBox(label, battleship, hbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(30);

        StackPane layout = new StackPane();
        layout.getChildren().addAll(whiteRectangle, vbox);

        scene = new Scene(layout, 570, 550);
        layout.setStyle("-fx-background-color: teal");
        layout.setAlignment(Pos.CENTER);

        window.setOnCloseRequest(event -> {
            event.consume();
            GameController.endGameClose();

        });
        window.setScene(scene);
        window.show();


    }


}
