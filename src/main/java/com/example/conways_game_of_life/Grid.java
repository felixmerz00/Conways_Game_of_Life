package com.example.conways_game_of_life;

import java.util.ArrayList;

public class Grid {
    private Tile[][] grid;
    private Color[][] lastGenColors;    // Store colors of the outgoing Generation. This is used as a reference for the makeGenerationStep method.

    public Grid() {
        grid = new Tile[18][18];    // Initialize grid.
        lastGenColors = new Color[18][18];
        // Fill grid with Tile objects.
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                Coordinate coordinate = new Coordinate(y,x);
                grid[y][x] = new Tile(coordinate);
            }
        }
    }

    //Method to get the Tile Instance in Grid at Input Coordinate
     private Tile getCoordinateTile(Coordinate coordinate) {
        return grid[coordinate.getX()][coordinate.getY()];
    }

    // Alternative method to get Tile at given coordinate.
    private Tile getTileAt(int x, int y){
        return grid[y][x];
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
        updateLastGenColors();  // Store outgoing configuration of colors in the lastGenColors array.
        // Iterate over all tiles except the tiles at the border (Row 0 & 17 and column 0 & 17)
        for(int y = 1; y < 17; y++){
            for(int x = 1; x < 17; x++){
                Tile curTile = grid[y][x];
                ArrayList<Tile> aliveNeighbors = getAliveNeighbours(y, x);

                /* Updating tiles
                * Different rules apply for coloring a tile, depending on whether it is dead or alive. */
                if(curTile.getColor() == Color.WHITE){  // Handle dead tiles
                    curTile.setColor(getColorForDeadTile(aliveNeighbors));
                }else{  // Handle live tiles
                    // If the cell is alive, then it stays alive if it has either 2 or 3 live neighbors
                    if(aliveNeighbors.size() != 2 && aliveNeighbors.size() != 3){
                        curTile.setColor(Color.WHITE);
                    }
                }
            }
        }
        // TODO Write code to update the drawn grid on the GUI.
    }

    /* Takes the coordinates from which I want the alive neighbours.
    * Returns an ArrayList of the alive neighbors. */
    private ArrayList<Tile> getAliveNeighbours(int row, int column){
        ArrayList<Tile> listOfNeighbours = new ArrayList<>();
        int y = row - 1;
        int x = column - 1;
        while (y <= row + 1){
            while (x <= column + 1){
                if (lastGenColors[y][x] != Color.WHITE){    // Add the neighbour to the list if it is alive.
                    listOfNeighbours.add(grid[y][x]);
                }
                x++;
            }
            y++;
            x = row - 1;
        }
        return listOfNeighbours;
    }

    /* This method takes the list of neighbours of a dead tile.
    * It compares the colors of the neighbours and
    * returns the color that should be used to color the tile. */
    private Color getColorForDeadTile(ArrayList<Tile> aliveNeighbours){
        // If the cell is dead, then it springs to life only in the case that it has 3 live neighbors.
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

    /* Before making a generation step we store the current configuration
    * of colors in the lastGenColors Array for reference. */
    private void updateLastGenColors() {
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                lastGenColors[y][x] = grid[y][x].getColor();
            }
        }
    }

    // Method takes a players color and checks if he still has tiles on the board.
    public boolean hasTiles(Color playersColor) {
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                if(grid[y][x].getColor() == playersColor){
                    return true;
                }
            }
        }
        return false;
    }
}
