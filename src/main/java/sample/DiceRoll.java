package sample;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class DiceRoll extends Application {

    public int rand;
    public Label randResult;
    public int cirPos[][] = new int[10][10];
    public int leadderPosition [][] = new int [6][3];
    public static final int Tile_Size = 60;
    public static final  int width = 10;
    public static final int height = 10;
    public Circle player1;
    public Circle player2;
    public int playerPosition1 = 1;
    public int playerPosition2 = 1;
    public boolean player1Turn = true;
    public boolean player2Turn = true;
    public static int player1XPos =30;
    public static int player1YPos = 570;
    public static int player2XPos = 30;
    public static int player2YPos = 570;

    public int posCir1 = 1;
    public int posCir2 = 1;
    public boolean gameStart = false;

    public Button gameButton;
    private Group tileGroup = new Group();

    private Parent createConetent(){
        Pane root = new Pane();
        root.setPrefSize(width*Tile_Size, (height*Tile_Size) + 60);
        root.getChildren().addAll(tileGroup);

        for(int i=0;i<height;i++)
        {
            for(int j=0;j<width;j++)
            {
                Tile tile = new Tile(Tile_Size,Tile_Size);
                tile.setTranslateX(j*Tile_Size);
                tile.setTranslateY(i*Tile_Size);
                tileGroup.getChildren().add(tile);
            }
        }

        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                System.out.print(cirPos[i][j] + " ");
            }
            System.out.println();
        }

        player1 = new Circle(24);
        player1.setId("Player1");
        player1.setFill(Color.AQUA);
        player1.getStyleClass().add("style.css");
        player1.setTranslateX(player1XPos);
        player1.setTranslateY(player1YPos);

        player2 = new Circle(24);
        player2.setId("Player2");
        player2.setFill(Color.CHOCOLATE);
        player2.getStyleClass().add("style.css");
        player2.setTranslateX(player2XPos);
        player2.setTranslateY(player2YPos);


        Button button2Player = new Button("Player2");
        button2Player.setStyle("-fx-background-color: #D2691E; -fx-background-radius: 50%; -fx-text-fill: white;");
        button2Player.setTranslateX(500);
        button2Player.setTranslateY(620);
        button2Player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(gameStart){
                    if(player2Turn){
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        move2Player();
                        traslatePlayer(player2XPos,player2YPos,player2);
                        player2Turn = false;
                        player1Turn = true;
                        playerPosition2 += rand;

                    }
                }
            }
        });

        Button button1Player = new Button("Player1");
        button1Player.setStyle("-fx-background-color: #00FFFF; -fx-background-radius: 50%; -fx-text-fill: white;");
        button1Player.setTranslateX(30);
        button1Player.setTranslateY(620);
        button1Player.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(gameStart){
                    if(player1Turn){
                        getDiceValue();
                        randResult.setText(String.valueOf(rand));
                        move1Player();
                        traslatePlayer(player1XPos,player1YPos,player1);
                        player1Turn = false;
                        player2Turn = true;
                        playerPosition1 += rand;

                    }
                }
            }
        });

        gameButton = new Button("Start Game");
        gameButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");
        gameButton.setTranslateX(280);
        gameButton.setTranslateY(620);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameButton.setText("Game Stated");
                player1XPos = 30;
                player1YPos = 570;

                player2XPos = 30;
                player2YPos = 570;

                player1.setTranslateX(player1XPos);
                player1.setTranslateY(player1YPos);

                player2.setTranslateX(player2XPos);
                player2.setTranslateY(player2YPos);

                gameStart = true;

            }
        });

        randResult = new Label ("0");
        randResult.setStyle("-fx-background-color: yellow; -fx-padding: 10;");

        randResult.setTranslateX(200);
        randResult.setTranslateY(620);
//        Image img = new Image("file:/C:/Users/HP/MyJavaProject/SnakeandLeadder/src/main/java/sample/snakebg.jpg");
//        ImageView bgImage = new ImageView();
//        bgImage.setImage(img);
//        bgImage.setFitHeight(600);
//        bgImage.setFitWidth(600);
        Image img = new Image("file:/C:/Users/HP/MyJavaProject/SnakeandLeadder/src/main/java/sample/ColorImage.jpg");
        ImageView bgImage = new ImageView();
        bgImage.setImage(img);
        bgImage.setFitHeight(600);
        bgImage.setFitWidth(600);

        tileGroup.getChildren().addAll(bgImage, player1, player2,button2Player,button1Player,gameButton,randResult);

        return root;
    }
    private  void getDiceValue(){
        rand = (int) (Math.random()*6 + 1);
    }
    private void move1Player(){
        for (int i=0;i<rand;i++){
            if(posCir1 % 2 == 1){
                player1XPos+=60;
            }

            if(posCir1 %2 == 0){
                player1XPos-=60;
            }

            if(player1XPos> 570){
                player1YPos -= 60;
                player1XPos -=60;
                posCir1++;
            }

            if(player1XPos < 30){
                player1YPos -=60;
                player1XPos +=60;
                posCir1++;
            }
            if(player1XPos < 20 || player1YPos < 20){
                player1XPos = 30;
                player1YPos = 30;
                gameStart = false;
                randResult.setText("Player 2 won");
                gameButton.setText("\nStart Again");

            }

        }
    }


    private void move2Player(){
        for (int i=0;i<rand;i++){
            if(posCir2 % 2 == 1){
                player2XPos+=60;
            }
            if(posCir2 %2 == 0){
                player2XPos-=60;
            }

            if(player2XPos > 570){
                player2YPos -= 60;
                player2XPos -=60;
                posCir2++;
            }

            if(player2XPos < 30){
                player2YPos -=60;
                player2XPos +=60;
                posCir2++;
            }
            if(player2XPos < 20 || player2YPos < 20){
                player2XPos = 30;
                player2YPos = 30;
                gameStart = false;
                randResult.setText("Player 1 won");
                gameButton.setText("\nStart Again");
            }

        }
    }



    private void traslatePlayer(int x,int y,Circle b){
        TranslateTransition animate = new TranslateTransition(Duration.millis(1000),b);
        animate.setToX(x);
        animate.setToY(y);
        animate.setAutoReverse(false);
        animate.play();

    }

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createConetent());
        //scene.getStylesheets().add("file:/C:\\Users\\HP\\MyJavaProject\\SnakeandLeadder\\src\\main\\java\\sample\\style.css");
        stage.setTitle("Snake and Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}