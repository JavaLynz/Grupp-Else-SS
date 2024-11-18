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

    private static String playerTwoName;
    private static String ipAddress;
    private static int port;

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Player Two Name");
        window.setMinWidth(400);
        window.setMinHeight(500);

        Label nameLabel = new Label();
        nameLabel.setText("Please enter your name: ");
        nameLabel.setAlignment(Pos.TOP_CENTER);
        nameLabel.setFont(new Font("Arial",30));

        TextField name = new TextField("Player Two");

        Label ipAddressLabel = new Label();
        ipAddressLabel.setText("Please enter the server IP address: ");
        ipAddressLabel.setAlignment(Pos.CENTER);
        ipAddressLabel.setFont(new Font("Arial",30));

        TextField ipAddressInput = new TextField("");

        Label portLabel = new Label();
        portLabel.setText("Please enter the server Port: ");
        portLabel.setAlignment(Pos.CENTER);
        portLabel.setFont(new Font("Arial",30));

        TextField portInput = new TextField("");

        Button done = new Button("Done");
        done.setDefaultButton(true);
        done.setOnAction(e -> {
            System.out.println("Player Two Name: " + name.getText());
            playerTwoName = name.getText();
            ipAddress = ipAddressInput.getText();
            port = Integer.parseInt(portInput.getText());
            window.close();
        });


        VBox layout = new VBox(20);
        layout.getChildren().addAll(nameLabel, name, ipAddressLabel, ipAddressInput, portLabel, portInput, done);
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


    public static String getIpAddress() {
        return ipAddress;
    }

    public static int getPort() {
        return port;

    }
}

