package com.example.conways_game_of_life;

public class Tile {
    //Tile Status (dead or alive)
    private boolean alive;

    //Tile Color:
    private Color aColor;

    //Constructor used when initializing Grid; set bool alive to true and Color to white
    public Tile() {
        alive = true;
        aColor = Color.WHITE;
    }

    public Color getColor() {return aColor;}

    public void setColor(Color c) {
        aColor = c;
    }

}
