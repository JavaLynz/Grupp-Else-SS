import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class WelcomeController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToGame() throws IOException {

        System.out.println("<switch to game window>");   //placeholder

    }

    @FXML
    private Button startaSpel;

    @FXML
    private void startaSpelet() {
        startaSpel.setStyle("-fx-background-color: blue;");
        System.out.println("nytt spel startas..");
        Server server = new Server();
        try{
            switchToGame();
            server.StartServer();
            Platform.exit();

        } catch(IOException e) {
            e.printStackTrace();
        } // close catch

    } // close method

    @FXML
    private Button joinGameButton;

    @FXML
    private void joinGame() {
        joinGameButton.setStyle("-fx-background-color: blue;");
        System.out.println("Letar efter ett spel..");
        Client client = new Client();
        try{
            switchToGame();
            client.startClientConnection();
            Platform.exit();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


} // close class
