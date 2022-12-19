package com.example.conways_game_of_life;

import java.util.ArrayList;

public class Grid {
    // This method replaces the current generation with its successor.
    public void updateGeneration(){
        // Iterate over all tiles
        for(tile: grid){
            ArrayList<Tile> neighbors = getAliveNeighbours(i, j);
            tile.setColor(getWinningColor(neighbors));
        }
        // Update the drawn grid on the GUI.
        draw();
    }

    // Returns an ArrayList of the alive neighbors.
    public ArrayList<Tile> getAliveNeighbours(int i, int j){

    }

    /* This method takes the list of neighbours of a tile.
    * It compares the colors of the neighbours and
    * returns the color that should be used to color the tile. */
    private Color getWinningColor(ArrayList<Tile> neighbours){
    }
}
