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
    protected static  int port;

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Player One Name");
        window.setMinWidth(400);
        window.setMinHeight(500);
        Label label = new Label();
        label.setText("Please enter your name: ");
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Arial",30));

        TextField name = new TextField("Player One");

        Label portInputLabel = new Label();
        portInputLabel.setAlignment(Pos.CENTER);
        portInputLabel.setText("Port: ");
        portInputLabel.setFont(new Font("Arial",30));

        TextField portInput = new TextField("6667");

        Button done = new Button("Done");
        done.setDefaultButton(true);
        done.setOnAction(e -> {
            System.out.println("Player One Name: " + name.getText());
            playerOneName = name.getText();
            port = Integer.parseInt(portInput.getText());
            System.out.println("Port: " + port);
            window.close();
            try {
                GameFX.display();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });




        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, name, portInputLabel , portInput ,done);
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



}