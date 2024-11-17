import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ClientEstablishedAlert {
    public static void display(){


        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Connection confirmation");
        window.setMinWidth(400);

        Label label = new Label("Connection has been established!");

        Button closeButton = new Button("Close");
        closeButton.setOnAction(e -> {
            window.close();
            try {
                GameFX.display();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        VBox root = new VBox();
        root.getChildren().addAll(label, closeButton);
        root.setSpacing(30);
        root.setAlignment(Pos.CENTER);


        Scene scene = new Scene(root, 400, 300);
        window.setScene(scene);
        window.showAndWait();
    }

}

