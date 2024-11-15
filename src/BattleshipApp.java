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
    private final GameController controller = new GameController();

    public static  Stage getStage() {
        return window;
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

            try {
                controller.setUpGame(true);
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
            try {
                controller.setUpGame(false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }


        });

        Button closeButton = new Button("Quit");
        closeButton.setFont(new Font("Arial", 30));
        closeButton.setOnAction(e->{
            QuitConfirmBox.display();
            });

        Label label = new Label("Welcome to Battleship!");
        label.setFont(new Font("Broadway", 50));
        label.setStyle("-fx-font-weight: bold");

        Rectangle whiteRectangle = new Rectangle(200,200,520,500);
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
