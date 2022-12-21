package com.example.conways_game_of_life;

public class Tile {
    //Tile Status (dead or alive)
    private boolean alive;

    //Tile Color: I think this is enough, so we don't have to assign a Player to a Tile,
    //because when a Color is assigned to the Player we can compare -> helper Method in Tile class (Tile.tileToPlayer)
    private Color aColor;
    private final Coordinate coordinate;

    //Constructor used when initializing Grid; set bool alive to true and Color to white
    public Tile(Coordinate coordinate) {
        alive = true;
        aColor = Color.WHITE;
        this.coordinate = coordinate;
    }

    public void setAlive(boolean bool) {
        alive = bool;
    }
    public boolean isAlive() {
        return alive;
    }

    public void setColor(Color color) {
        aColor = color;
    }

    public Color getColor() {return aColor;}


    public Coordinate getCoordinates() {
        return coordinate;
    }

    //helper method to check, if the player (input parameter) is assigned to the tile
    public boolean tileToPlayer(Player player) {
        return player.getPlayerColor().equals(aColor);
    }

}
