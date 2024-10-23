package org.example;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Tile extends Rectangle {
    public  Tile (int x,int y){
        setWidth(DiceRoll.Tile_Size);
        setHeight(DiceRoll.Tile_Size);



        setFill(Color.PINK);
        setStroke(Color.BLACK);
    }


}
