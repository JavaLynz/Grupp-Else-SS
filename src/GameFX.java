import javafx.application.Application;
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

    int[][] annasEnemyBoardArray = new int[][]{
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

        AnchorPane pLetters = new AnchorPane();
        List<Label> pLettersList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String string = " " +(char)(i+65);
            Label label = new Label(string);
            label.setBackground(new Background(new BackgroundFill(Color.PALEVIOLETRED,null,null)));
            label.setFont(new Font("Arial bold", 12));
            label.setPrefWidth(15);
            label.setPrefHeight(15);
            label.setLayoutX((i*20)+5);
            pLettersList.add(label);
        }
        pLetters.getChildren().addAll(pLettersList);

        AnchorPane eLetters = new AnchorPane();
        List<Label> eLettersList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String string = " " +(char)(i+65);
            Label label = new Label(string);
            label.setBackground(new Background(new BackgroundFill(Color.PALEVIOLETRED,null,null)));
            label.setFont(new Font("Arial bold", 12));
            label.setPrefWidth(15);
            label.setPrefHeight(15);
            label.setLayoutX((i*20)+5);
            eLettersList.add(label);
        }
        eLetters.getChildren().addAll(eLettersList);

        AnchorPane pNumbers = new AnchorPane();
        List<Label> pNumbersList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String string = " " +i;
            Label label = new Label(string);
            label.setBackground(new Background(new BackgroundFill(Color.PALEVIOLETRED,null,null)));
            label.setFont(new Font("Arial bold", 12));
            label.setPrefWidth(15);
            label.setPrefHeight(15);
            label.setLayoutY((i*20)+5);
            pNumbersList.add(label);
        }
        pNumbers.getChildren().addAll(pNumbersList);

        AnchorPane eNumbers = new AnchorPane();
        List<Label> eNumbersList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String string = " " +i;
            Label label = new Label(string);
            label.setBackground(new Background(new BackgroundFill(Color.PALEVIOLETRED,null,null)));
            label.setFont(new Font("Arial bold", 12));
            label.setPrefWidth(15);
            label.setPrefHeight(15);
            label.setLayoutY((i*20)+5);
            eNumbersList.add(label);
        }
        eNumbers.getChildren().addAll(eNumbersList);



        List<Rectangle> playerBoardList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (annasPlayerBoardArray[i][j]==0){                //Water
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(Color.DEEPSKYBLUE);
                    rectangle.setWidth(15);
                    rectangle.setHeight(15);
                    rectangle.setLayoutX((j*20)+5);
                    rectangle.setLayoutY((i*20)+5);
                    playerBoardList.add(rectangle);
                } else if (annasPlayerBoardArray[i][j]==1) {         //Boat
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(Color.DARKSLATEGREY);
                    rectangle.setWidth(15);
                    rectangle.setHeight(15);
                    rectangle.setLayoutX((j*20)+5);
                    rectangle.setLayoutY((i*20)+5);
                    playerBoardList.add(rectangle);
                } else if (annasPlayerBoardArray[i][j]==2) {         //hit
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(Color.DARKRED);
                    rectangle.setWidth(15);
                    rectangle.setHeight(15);
                    rectangle.setLayoutX((j*20)+5);
                    rectangle.setLayoutY((i*20)+5);
                    playerBoardList.add(rectangle);
                } else {                                               //miss
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(Color.WHITE);
                    rectangle.setWidth(15);
                    rectangle.setHeight(15);
                    rectangle.setLayoutX((j*20)+5);
                    rectangle.setLayoutY((i*20)+5);
                    playerBoardList.add(rectangle);}
            }
        }

        List<Rectangle> enemyList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                Rectangle rectangle = new Rectangle();
                rectangle.setFill(Color.FIREBRICK);
                rectangle.setWidth(15);
                rectangle.setHeight(15);
                rectangle.setLayoutX((j*20)+5);
                rectangle.setLayoutY((i*20)+5);
                enemyList.add(rectangle);
            }
        }

        playerBoard.getChildren().addAll(playerBoardList);
        playerBoard.setPrefWidth(205);
        playerBoard.setPrefHeight(205);


        enemyBoard.getChildren().addAll(enemyList);
        enemyBoard.setPrefWidth(205);
        enemyBoard.setPrefHeight(205);



        Button speed = new Button("Change delay speed");
        Button giveUp = new Button("Give up the game and quit");

        speed.setStyle("-fx-font: 10 arial; -fx-base: #b6e7c9;");
        giveUp.setStyle("-fx-font: 10 arial; -fx-base: RED;");
        giveUp.setOnAction((e)-> System.exit(0));

        TextArea textArea = new TextArea("Show latest moves");
        textArea.setMaxWidth(200);
        textArea.setLayoutX(300);





        label1.setLayoutX(50);
        label1.setLayoutY(10);
        label2.setLayoutX(10);
        label2.setLayoutY(72);
        label3.setLayoutX(250);
        label3.setLayoutY(72);

        label4.setLayoutX(100);
        label4.setLayoutY(100);
        label5.setLayoutX(325);
        label5.setLayoutY(100);

        enemyScore.setLayoutX(355);
        enemyScore.setLayoutY(80);


        yourScore.setLayoutX(110);
        yourScore.setLayoutY(80);

        playerBoard.setLayoutX(50);
        playerBoard.setLayoutY(150);

        pLetters.setLayoutX(50);
        pLetters.setLayoutY(130);

        eLetters.setLayoutX(315);
        eLetters.setLayoutY(130);

        enemyBoard.setLayoutX(315);
        enemyBoard.setLayoutY(150);
        pNumbers.setLayoutX(30);
        pNumbers.setLayoutY(150);

        eNumbers.setLayoutX(300);
        eNumbers.setLayoutY(150);

        allOfItPane.getChildren().addAll(label1,label2, yourScore, label3, enemyScore,
                label4,label5,pLetters, eLetters, pNumbers, eNumbers,
                playerBoard, enemyBoard);
        //          ,speed,textArea,giveUp


        Scene scene = new Scene(allOfItPane);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Battleship the Game");
        primaryStage.show();


    }
}
