import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class GameFX{

    public static void display() throws Exception {
    Player player = new Player("e", true);
    String text = "Show latest moves";
    int pShipsAlive = 2;
    int eShipsAlive = 4;


        AnchorPane allOfItPane = new AnchorPane();
        allOfItPane.setPrefSize(570,550);
        allOfItPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY,null,null)));


        Label label1 = new Label("⪻BATTLESHIP⪼");
        Label label2 = new Label("YOUR SCORE: ");
        Label label3 = new Label("ENEMY SCORE: ");
        Label label4 = new Label("YOUR GAME BOARD");
        Label label5 = new Label("ENEMY GAME BOARD");


        label1.setFont(new Font("Broadway", 30));
        label2.setFont(new Font("Arial bold", 12));
        label3.setFont(new Font("Arial bold", 12));
        label4.setFont(new Font("Cambria bold", 12));
        label5.setFont(new Font("Cambria bold", 12));

        AnchorPane yourScore = new AnchorPane();
        List<Circle> playerScoreList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (i<pShipsAlive) {
            Circle circle = new Circle();
            circle.setFill(Color.GREEN);
            circle.setRadius(8);
            circle.setLayoutX((i*20));
            playerScoreList.add(circle);
            } else {
                Circle circle = new Circle();
                circle.setFill(Color.BEIGE);
                circle.setRadius(8);
                circle.setLayoutX((i * 20));
                playerScoreList.add(circle);
            }
        }
        yourScore.getChildren().addAll(playerScoreList);

        AnchorPane enemyScore = new AnchorPane();
        List<Circle> enemyScoreList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (i<eShipsAlive) {
                Circle circle = new Circle();
                circle.setFill(Color.DARKRED);
                circle.setRadius(8);
                circle.setLayoutX((i * 20));
                enemyScoreList.add(circle);
            } else {
                Circle circle = new Circle();
                circle.setFill(Color.BEIGE);
                circle.setRadius(8);
                circle.setLayoutX((i * 20));
                enemyScoreList.add(circle);
            }
        }
        enemyScore.getChildren().addAll(enemyScoreList);

        AnchorPane pLetters = new AnchorPane();
        pLetters.setBackground(new Background(new BackgroundFill(Color.GREY,null,null)));
        pLetters.setPrefWidth(225);
        pLetters.setPrefHeight(20);
        List<Label> pLettersList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String string = " " +(char)(i+65);
            Label label = new Label(string);
            label.setBackground(new Background(new BackgroundFill(Color.PALEVIOLETRED,null,null)));
            label.setFont(new Font("Arial bold", 12));
            label.setPrefWidth(15);
            label.setPrefHeight(15);
            label.setLayoutX((i*20)+25);
            label.setLayoutY(5);
            pLettersList.add(label);
        }
        pLetters.getChildren().addAll(pLettersList);

        AnchorPane eLetters = new AnchorPane();
        eLetters.setBackground(new Background(new BackgroundFill(Color.GREY,null,null)));
        eLetters.setPrefWidth(225);
        eLetters.setPrefHeight(20);
        List<Label> eLettersList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String string = " " +(char)(i+65);
            Label label = new Label(string);
            label.setBackground(new Background(new BackgroundFill(Color.PALEVIOLETRED,null,null)));
            label.setFont(new Font("Arial bold", 12));
            label.setPrefWidth(15);
            label.setPrefHeight(15);
            label.setLayoutX((i*20)+25);
            label.setLayoutY(5);
            eLettersList.add(label);
        }
        eLetters.getChildren().addAll(eLettersList);

        AnchorPane pNumbers = new AnchorPane();
        pNumbers.setBackground(new Background(new BackgroundFill(Color.GREY,null,null)));
        pNumbers.setPrefHeight(205);
        pNumbers.setPrefWidth(20);
        List<Label> pNumbersList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String string = " " +i;
            Label label = new Label(string);
            label.setBackground(new Background(new BackgroundFill(Color.PALEVIOLETRED,null,null)));
            label.setFont(new Font("Arial bold", 12));
            label.setPrefWidth(15);
            label.setPrefHeight(15);
            label.setLayoutX(5);
            label.setLayoutY((i*20)+5);
            pNumbersList.add(label);
        }
        pNumbers.getChildren().addAll(pNumbersList);

        AnchorPane eNumbers = new AnchorPane();
        eNumbers.setBackground(new Background(new BackgroundFill(Color.GREY,null,null)));
        eNumbers.setPrefHeight(205);
        eNumbers.setPrefWidth(20);
        List<Label> eNumbersList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String string = " " +i;
            Label label = new Label(string);
            label.setBackground(new Background(new BackgroundFill(Color.PALEVIOLETRED,null,null)));
            label.setFont(new Font("Arial bold", 12));
            label.setPrefWidth(15);
            label.setPrefHeight(15);
            label.setLayoutX(5);
            label.setLayoutY((i*20)+5);
            eNumbersList.add(label);
        }
        eNumbers.getChildren().addAll(eNumbersList);


        AnchorPane playerBoard = new AnchorPane();
        playerBoard.setBackground(new Background(new BackgroundFill(Color.GREY,null,null)));

        AnchorPane enemyBoard = new AnchorPane();
        enemyBoard.setBackground(new Background(new BackgroundFill(Color.GRAY,null,null)));


        Image water = new Image("vatten2.png");
        Image hit = new Image("explode.png");
        List<Rectangle> playerBoardList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (player.getPlayerBoard().getMap()[i][j]==0){                //Water
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(new ImagePattern(water));
                    rectangle.setWidth(15);
                    rectangle.setHeight(15);
                    rectangle.setLayoutX((j*20)+5);
                    rectangle.setLayoutY((i*20)+5);
                    playerBoardList.add(rectangle);
                } else if (player.getPlayerBoard().getMap()[i][j]==12) {         //miss
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(Color.LIGHTGRAY);
                    rectangle.setWidth(15);
                    rectangle.setHeight(15);
                    rectangle.setLayoutX((j*20)+5);
                    rectangle.setLayoutY((i*20)+5);
                    playerBoardList.add(rectangle);
                } else if (player.getPlayerBoard().getMap()[i][j]==11) {         //hit
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(new ImagePattern(hit));
                    rectangle.setWidth(15);
                    rectangle.setHeight(15);
                    rectangle.setLayoutX((j*20)+5);
                    rectangle.setLayoutY((i*20)+5);
                    playerBoardList.add(rectangle);
                } else {                                               //Boat
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(Color.DARKSLATEGREY);
                    rectangle.setWidth(15);
                    rectangle.setHeight(15);
                    rectangle.setLayoutX((j*20)+5);
                    rectangle.setLayoutY((i*20)+5);
                    playerBoardList.add(rectangle);}
            }
        }

        List<Rectangle> enemyBoardList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (player.getEnemyBoard().getMap()[i][j]==12){                 //miss
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(Color.LIGHTGRAY);
                    rectangle.setWidth(15);
                    rectangle.setHeight(15);
                    rectangle.setLayoutX((j*20)+5);
                    rectangle.setLayoutY((i*20)+5);
                    enemyBoardList.add(rectangle);
                } else if (player.getEnemyBoard().getMap()[i][j]==11) {         //hit
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(new ImagePattern(hit));
                    rectangle.setWidth(15);
                    rectangle.setHeight(15);
                    rectangle.setLayoutX((j*20)+5);
                    rectangle.setLayoutY((i*20)+5);
                    enemyBoardList.add(rectangle);
                } else {                                               //Water
                    Rectangle rectangle = new Rectangle();
                    rectangle.setFill(new ImagePattern(water));
                    rectangle.setWidth(15);
                    rectangle.setHeight(15);
                    rectangle.setLayoutX((j*20)+5);
                    rectangle.setLayoutY((i*20)+5);
                    enemyBoardList.add(rectangle);}
            }
        }

        playerBoard.getChildren().addAll(playerBoardList);
        playerBoard.setPrefWidth(205);
        playerBoard.setPrefHeight(205);

        enemyBoard.getChildren().addAll(enemyBoardList);
        enemyBoard.setPrefWidth(205);
        enemyBoard.setPrefHeight(205);


        //Button speed = new Button("Change delay speed");
        Button giveUp = new Button("Give up the game and quit");
        Button startGame = new Button("Start game and go to war");

        ComboBox speed = new ComboBox();
        speed.getItems().add("Delay rounds 0 second");
        speed.getItems().add("Delay rounds 1 second");
        speed.getItems().add("Delay rounds 2 second");
        speed.getItems().add("Delay rounds 3 second");
        speed.getItems().add("Delay rounds 4 second");
        speed.getItems().add("Delay rounds 5 second");

        speed.setValue("Delay rounds 3 second");

        String choice = (String) speed.getValue();
        int setSpeed = Integer.parseInt(choice.substring(13, 14));


        speed.setStyle("-fx-font: 10 arial; -fx-base: #b6e7c9;");       ///???????????
        startGame.setStyle("-fx-font: 10 arial; -fx-base: #b6e7c9;");
        giveUp.setStyle("-fx-font: 10 arial; -fx-base: RED;");
        GameController controller = new GameController();               ///???????????
        giveUp.setOnAction((e)-> System.exit(0));
        startGame.setOnAction((e)-> controller.annasLoop());





        TextArea textArea = new TextArea(text);
        textArea.setMaxWidth(225);
        textArea.setMaxHeight(120);


        label1.setLayoutX(160);
        label1.setLayoutY(10);

        label2.setLayoutX(40);
        label2.setLayoutY(67);
        yourScore.setLayoutX(140);
        yourScore.setLayoutY(75);

        label3.setLayoutX(305);
        label3.setLayoutY(67);
        enemyScore.setLayoutX(420);
        enemyScore.setLayoutY(75);

        label4.setLayoutX(95);
        label4.setLayoutY(110);

        label5.setLayoutX(360);
        label5.setLayoutY(110);

        playerBoard.setLayoutX(60);
        playerBoard.setLayoutY(150);
        pLetters.setLayoutX(40);
        pLetters.setLayoutY(130);
        pNumbers.setLayoutX(40);
        pNumbers.setLayoutY(150);

        enemyBoard.setLayoutX(325);
        enemyBoard.setLayoutY(150);
        eLetters.setLayoutX(305);
        eLetters.setLayoutY(130);
        eNumbers.setLayoutX(305);
        eNumbers.setLayoutY(150);

        textArea.setLayoutX(40);
        textArea.setLayoutY(380);

        speed.setLayoutX(350);
        speed.setLayoutY(430);

        giveUp.setLayoutX(350);
        giveUp.setLayoutY(480);

        startGame.setLayoutX(350);
        startGame.setLayoutY(380);


        allOfItPane.getChildren().addAll(label1,label2, yourScore, label3, enemyScore,
                label4,label5,pLetters, eLetters, pNumbers, eNumbers,
                playerBoard, enemyBoard,speed,textArea,giveUp,startGame);



        Scene scene = new Scene(allOfItPane);

        BattleshipApp.getStage().setOnCloseRequest(e -> {
            e.consume();
            GameController.endGameClose();
        });
        BattleshipApp.getStage().sizeToScene();
        BattleshipApp.getStage().setScene(scene);
        BattleshipApp.getStage().setTitle("Battleship The Game");
        BattleshipApp.getStage().show();

    }
}