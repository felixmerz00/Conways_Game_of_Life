package com.example.conways_game_of_life;

public class Tile {
    //Tile Status (dead or alive)
    //private boolean alive;

    //Tile Color: I think this is enough, so we don't have to assign a Player to a Tile,
    //because when a Color is assigned to the Player we can compare -> helper Method in Tile class (Tile.tileToPlayer)
    private Color aColor;

    //row
    private final int x;
    //line
    private final int y;

    //Constructor used when initializing Grid; set bool alive to true and Color to white
    public Tile(int x, int y) {
        //alive = true;
        aColor = Color.WHITE;
        this.y = y;
        this.x = x;
    }

    //check if methods are necessary at end
    public int tileX() {
        return x;
    }
    public int tileY() {
        return y;
    }

    //we don't need setAlive method because the field aColor serves as information -> setColor
    public boolean isAlive() {
        return aColor != Color.WHITE;
    }

    public void setColor(Color color) {
        aColor = color;
    }

    public Color getColor() {return aColor;}


    //helper method to check, if the player (input parameter) is assigned to the tile
    public boolean tileToPlayer(Player player) {
        return player.getPlayerColor().equals(aColor);
    }

}
