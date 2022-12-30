package com.example.conways_game_of_life;

public class Tile {

    private Color aColor;

    private final int x;    // Column

    private final int y;    // Row

    //Constructor used when initializing Grid; set bool alive to true and Color to white
    public Tile(int x, int y) {
        aColor = Color.WHITE;
        this.y = y;
        this.x = x;
    }

    public Coordinate getCoordinate(){
        return new Coordinate(x, y);
    }

    public void setColor(Color color) {
        aColor = color;
    }

    public Color getColor() {return aColor;}


    //helper method to check, if the player (input parameter) is assigned to the tile
    public boolean tileToPlayer(Player player) {
        return player.getPlayerColor().equals(aColor);
    }

    @Override
    public String toString() {
        return aColor.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(this.getClass().equals(obj.getClass())) {
            Tile t2 = (Tile) obj;
            return aColor == t2.getColor() && this.x == t2.getCoordinate().x() && this.y == t2.getCoordinate().y();
        }
        return false;
    }
}
