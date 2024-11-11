import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class GameFX extends Application {
    int[][] annasPlayerBoardArray = new int[][]{
            {0,0,0,0,0,0,0,0,0,0},
            {0,1,1,1,0,0,0,0,0,0},
            {0,0,3,0,0,0,0,0,0,0},
            {0,0,0,1,1,1,0,0,0,0},
            {0,0,0,0,3,0,0,0,0,0},
            {0,0,0,0,0,2,2,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0}
    };

    int[][] annasEnnemyBoardArray = new int[][]{
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},
            {0,0,3,0,0,0,0,0,0,0},
            {0,0,0,3,0,0,0,0,0,0},
            {0,0,0,0,2,0,0,0,0,0},
            {0,0,0,0,0,2,0,0,0,0},
            {0,3,3,0,0,0,2,0,0,0},
            {0,0,2,2,0,0,0,3,0,0},
            {0,0,0,0,0,0,0,0,3,0},
            {0,0,0,0,0,0,0,0,0,2}
    };

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane allOfItPane = new AnchorPane();
        allOfItPane.setPrefSize(750,500);
        allOfItPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));


        Label label1 = new Label("⪻BATTLESHIP⪼");
        Label label2 = new Label("YOUR SCORE: ");
        Label label3 = new Label("ENEMY SCORE: ");
        Label label4 = new Label("YOUR GAME BOARD");
        Label label5 = new Label("ENEMY GAME BOARD");


        label1.setFont(new Font("Broadway", 30));
        label2.setFont(new Font("Arial bold", 12));
        label3.setFont(new Font("Arial bold", 12));

        label1.setLayoutX(250);
        label1.setLayoutY(10);
        label2.setLayoutX(10);
        label2.setLayoutY(22);
        label3.setLayoutX(550);
        label3.setLayoutY(22);

        label4.setLayoutX(100);
        label5.setLayoutX(400);


        AnchorPane playerBoard = new AnchorPane();
        playerBoard.setBackground(new Background(new BackgroundFill(Color.GREY,null,null)));


        AnchorPane enemyBoard = new AnchorPane();
        enemyBoard.setBackground(new Background(new BackgroundFill(Color.DARKCYAN,null,null)));

        AnchorPane yourScore = new AnchorPane();
        List<Circle> playerScoreList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Circle circle = new Circle();
            circle.setFill(Color.DARKRED);
            circle.setRadius(8);
            circle.setLayoutX((i*20));
            playerScoreList.add(circle);
        }
        yourScore.getChildren().addAll(playerScoreList);

        yourScore.setLayoutX(110);
        yourScore.setLayoutY(30);

        AnchorPane enemyScore = new AnchorPane();
        List<Circle> enemyScoreList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Circle circle = new Circle();
            circle.setFill(Color.GREEN);
            circle.setRadius(8);
            circle.setLayoutX((i*20));
            enemyScoreList.add(circle);
        }
        enemyScore.getChildren().addAll(enemyScoreList);

        enemyScore.setLayoutX(655);
        enemyScore.setLayoutY(30);

        Label labelA = new Label("  A   ");
        Label labelB = new Label("  B   ");
        Label labelC = new Label("  C   ");
        Label labelD = new Label("  D   ");
        Label labelE = new Label("  E   ");
        Label labelF = new Label("  F   ");
        Label labelG = new Label("  G   ");
        Label labelH = new Label("  H   ");
        Label labelI = new Label("  I    ");
        Label labelJ = new Label("  J    ");

        labelA.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN,null,null)));
        labelA.prefWidth(65);
        labelA.prefHeight(25);


        GridPane lettersGrid = new GridPane();
        lettersGrid.setGridLinesVisible(true);
        lettersGrid.add(labelA, 0,0);
        lettersGrid.add(labelB, 1,0);
        lettersGrid.add(labelC, 2,0);
        lettersGrid.add(labelD, 3,0);
        lettersGrid.add(labelE, 4,0);
        lettersGrid.add(labelF, 5,0);
        lettersGrid.add(labelG, 6,0);
        lettersGrid.add(labelH, 7,0);
        lettersGrid.add(labelI, 8,0);
        lettersGrid.add(labelJ, 9,0);

        lettersGrid.setBackground(new Background(new BackgroundFill(Color.BURLYWOOD,null,null)));
        lettersGrid.setHgap(5);
        lettersGrid.setCenterShape(true);

        lettersGrid.setLayoutX(100);
        lettersGrid.setLayoutY(100);




        List<Rectangle> playerBoardList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (annasPlayerBoardArray[i][j]==0){                //Water
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(Color.DEEPSKYBLUE);
                    rectangle.setWidth(20);
                    rectangle.setHeight(20);
                    rectangle.setLayoutX((j*25)+5);
                    rectangle.setLayoutY((i*25)+5);
                    playerBoardList.add(rectangle);
                } else if (annasPlayerBoardArray[i][j]==1) {         //Boat
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(Color.DARKSLATEGREY);
                    rectangle.setWidth(20);
                    rectangle.setHeight(20);
                    rectangle.setLayoutX((j*25)+5);
                    rectangle.setLayoutY((i*25)+5);
                    playerBoardList.add(rectangle);
                } else if (annasPlayerBoardArray[i][j]==2) {         //hit
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(Color.DARKRED);
                    rectangle.setWidth(20);
                    rectangle.setHeight(20);
                    rectangle.setLayoutX((j*25)+5);
                    rectangle.setLayoutY((i*25)+5);
                    playerBoardList.add(rectangle);
                } else {                                               //miss
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(Color.WHITE);
                    rectangle.setWidth(20);
                    rectangle.setHeight(20);
                    rectangle.setLayoutX((j*25)+5);
                    rectangle.setLayoutY((i*25)+5);
                    playerBoardList.add(rectangle);}

            }
        }

        List<Rectangle> enemyList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.FIREBRICK);
                rectangle.setWidth(20);
                rectangle.setHeight(20);
                rectangle.setLayoutX((j*25)+5);
                rectangle.setLayoutY((i*25)+5);
                enemyList.add(rectangle);
            }
        }

        playerBoard.getChildren().addAll(playerBoardList);
        playerBoard.setPrefWidth(255);
        playerBoard.setPrefHeight(255);
        playerBoard.setLayoutX(100);
        playerBoard.setLayoutY(125);

        enemyBoard.getChildren().addAll(enemyList);
        enemyBoard.setPrefWidth(255);
        enemyBoard.setPrefHeight(255);
        enemyBoard.setLayoutX(365);
        enemyBoard.setLayoutY(125);

        Button speed = new Button("Change delay speed");
        Button giveUp = new Button("Give up the game and quit");

        speed.setStyle("-fx-font: 10 arial; -fx-base: #b6e7c9;");
        giveUp.setStyle("-fx-font: 10 arial; -fx-base: RED;");
        giveUp.setOnAction((e)-> System.exit(0));

        TextArea textArea = new TextArea("Show latest moves");
        textArea.setMaxWidth(200);
        textArea.setLayoutX(300);

        AnchorPane boardNames = new AnchorPane(label4, label5);
        boardNames.setLayoutX(75);
        boardNames.setLayoutY(75);
        GridPane sideNumbers = new GridPane();
      // sideNumbers.getChildren().addAll(labelA, labelB);



        allOfItPane.getChildren().addAll(label1,label2, yourScore, label3, enemyScore,
                boardNames,lettersGrid,
                sideNumbers,playerBoard, enemyBoard);
      //          ,speed,textArea,giveUp


        Scene scene = new Scene(allOfItPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Battleship the Game");
        primaryStage.show();


    }
}
