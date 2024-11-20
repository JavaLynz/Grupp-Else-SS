import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ConfirmServer {
    public static void display(){

        String portText = String.valueOf(PlayerOneInputWindow.port);
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Connection Opened At: ");
        window.setMinWidth(400);
        window.setMinHeight(500);

        Label label = new Label("IP Address: ");
        TextArea IPAddress = new TextArea(NetworkCommunications.getIpAddress());
        Label label2 = new Label("Port: ");
        TextArea port = new TextArea(portText);
        Label label3 = new Label("Please send this information to the client, before closing this window.");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> window.close());

        VBox root = new VBox();
        root.getChildren().addAll(label, IPAddress, label2, port, label3, closeButton);
        root.setSpacing(30);
        root.setAlignment(Pos.CENTER);


        Scene scene = new Scene(root, 400, 500);
        window.setScene(scene);
        window.showAndWait();
    }

}
