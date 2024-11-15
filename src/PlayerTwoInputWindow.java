import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PlayerTwoInputWindow {

    protected static String playerTwoName;
    protected static boolean isClient = true;
    protected static boolean isServer = false;
    protected static String ipAddress;

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Player Two Name");
        window.setMinWidth(400);
        Label label = new Label();
        label.setText("Please enter your name: ");
        label.setAlignment(Pos.TOP_CENTER);
        label.setFont(new Font("Arial",30));

        TextField name = new TextField("Player Two");

        Label ipAddressLabel = new Label();
        label.setText("Please enter the server IP address: ");
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Arial",30));

        TextField ipAddressInput = new TextField("");

        Button done = new Button("Done");
        done.setDefaultButton(true);
        done.setOnAction(e -> {
            System.out.println("Player Two Name: " + name.getText());
            String playerTwoName = name.getText();
            String ipAddress = ipAddressInput.getText();
            window.close();
            GameFXa.display(playerTwoName);
        });


        VBox layout = new VBox(20);
        layout.getChildren().addAll(label, name, ipAddressLabel, ipAddressInput, done);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();





    }

    public static String getPlayerTwoName() {
        return playerTwoName;
    }
    public static void setPlayerOneName(String playerTwoName) {
        PlayerTwoInputWindow.playerTwoName = playerTwoName;
    }

    public static boolean isServer() {
        return isServer;
    }

    public static void setIsServer(boolean isServer) {
        PlayerTwoInputWindow.isServer = isServer;
    }

    public static boolean isClient() {
        return isClient;
    }

    public static void setIsClient(boolean isClient) {
        PlayerTwoInputWindow.isClient = isClient;

    }
}

