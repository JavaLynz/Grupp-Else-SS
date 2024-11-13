import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlayerOneInputWindow {

    protected static String playerOneName;
    protected static boolean isServer = true;
    protected static boolean isClient = false;
    protected static String port;

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Player One Info");
        window.setMinWidth(400);

        Label nameLabel = new Label();
        nameLabel.setText("Please enter your name: ");
        nameLabel.setAlignment(Pos.TOP_CENTER);
        nameLabel.setFont(new Font("Arial",20));

        TextField name = new TextField("Player One");

        Label portLabel = new Label();
        portLabel.setText("Please enter the port: ");
        portLabel.setAlignment(Pos.BOTTOM_CENTER);
        portLabel.setFont(new Font("Arial",20));

        TextField portInput = new TextField("6667");
        port = portInput.getText();

        Button done = new Button("Done");
        done.setOnAction(e -> {
            System.out.println("Player One Name: " + name.getText());
            playerOneName = name.getText();
            window.close();
            GameFXa.display(playerOneName);
        });


        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20,20,20,20));
        layout.getChildren().addAll(nameLabel, name, portLabel, portInput, done);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();





    }

    public static String getPlayerOneName() {
        return playerOneName;
    }
    public static void setPlayerOneName(String playerOneName) {
        PlayerOneInputWindow.playerOneName = playerOneName;
    }

    public static boolean isServer() {
        return isServer;
    }

    public static void setIsServer(boolean isServer) {
        PlayerOneInputWindow.isServer = isServer;
    }

    public static boolean isClient() {
        return isClient;
    }

    public static void setIsClient(boolean isClient) {
        PlayerOneInputWindow.isClient = isClient;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        PlayerOneInputWindow.port = port;
    }

}