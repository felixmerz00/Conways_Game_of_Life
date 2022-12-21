package com.example.conways_game_of_life;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

public class Grid {
    private Tile[][] grid;

    public Grid() {
        grid = new Tile[18][18];    // Initialize grid.
        // Fill grid with Tile objects.
        for(int i = 0; i < 18; i++){
            for(int j = 0; j < 18; j++){
                Coordinate coordinate = new Coordinate(i,j);
                grid[i][j] = new Tile(coordinate);
            }
        }
    }

    //Method to get the Tile Instance in Grid at Input Coordinate
     private Tile getCoordinateTile(Coordinate coordinate) {
        return grid[coordinate.getX()][coordinate.getY()];
    }

    //This method sets Tile.alive to false and Tile.aColor to WHITE
    //we use design by contract for this method implementation

    /**
     * @pre tile.getColor != Color.WHITE && tile.isAlive == true
     * @pre killValid(grid) == true
     */
    public void kill(Coordinate coordinate) {
        Tile tile = getCoordinateTile(coordinate);
        tile.setAlive(false);
        tile.setColor(Color.WHITE);
    }

    //do we need to add post conditions for design by contract?
    /**
     * @post tile.getColor == Color.WHITE && tile.isAlive == false
     */

    //method to assign a player to a tile at given coordinate in grid
    //use Design by Contract

    /**
     * @pre tile.getColor == Color.WHITE && tile.isAlive == false
     * @pre setValid(grid, player) == true;
     */
    public void playerSetTile(Coordinate coordinate, Player player) {
        Tile tile = getCoordinateTile(coordinate);
        Color color = player.getPlayerColor();
        tile.setColor(color);
        tile.setAlive(true);
    }

    // This method replaces the current generation with its successor.
    public void makeGenerationStep(){
        // Iterate over all tiles
        for(tile: grid) {
            //If the cell is alive, then it stays alive if it has either 2 or 3 live neighbors


            // If the cell is dead, then it springs to life only in the case that it has 3 live neighbors
            ArrayList<Tile> aliveNeighbors = getAliveNeighbours(i, j);
            tile.setColor(getWinningColor(aliveNeighbors));
        }
        // TODO Write code to update the drawn grid on the GUI.
    }

    /* Takes the coordinates from which I want the alive neighbours. i = row, j = column
    * Returns an ArrayList of the alive neighbors. */
    public ArrayList<Tile> getAliveNeighbours(int i, int j){
        ArrayList<Tile> listOfNeighbours = new ArrayList<>();
        int x = -1;
        int y = -1;
        while (y <= i + 2){
            while (x <= j + 1){
                if (grid[y][x].isAlive()){
                    listOfNeighbours.add(grid[y][x]);
                }
                x++;
            }
            y++;
            x = j-1;
        }
        return listOfNeighbours;
    }

    /* This method takes the list of neighbours of a tile.
    * It compares the colors of the neighbours and
    * returns the color that should be used to color the tile. */
    private Color getWinningColor(ArrayList<Tile> aliveNeighbours){
        if(aliveNeighbours.size() != 3){
            return Color.WHITE;
        }
        Color colorZero = aliveNeighbours.get(0).getColor();
        Color colorOne = aliveNeighbours.get(1).getColor();
        Color colorTwo = aliveNeighbours.get(2).getColor();
        // This covers if-else block covers all six possible combinations of colors.
        if(colorZero == colorOne || colorZero == colorTwo){ // Color zero occurs twice
            return colorZero;
        }else{  // The color that occupies index one and two occurs twice.
            return colorOne;
        }
    }
}
