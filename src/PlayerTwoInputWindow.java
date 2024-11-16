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

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Player Two Name");
        window.setMinWidth(400);

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

        Button done = new Button("Done");
        done.setDefaultButton(true);
        done.setOnAction(e -> {
            System.out.println("Player Two Name: " + name.getText());
            String playerTwoName = name.getText();
            String ipAddress = ipAddressInput.getText();
            window.close();
            try {
                GameFX.display();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });


        VBox layout = new VBox(20);
        layout.getChildren().addAll(nameLabel, name, ipAddressLabel, ipAddressInput, done);
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
}

