package com.example.conways_game_of_life;

import java.util.ArrayList;

public class Grid {
    private Tile[][] grid;

    public Grid() {
        grid = new Tile[18][18];    // Initialize grid.
        // Fill grid with Tile objects.
        for(int i = 0; i < 18; i++){
            for(int j = 0; j < 18; j++){
                grid[i][j] = new Tile();
            }
        }
    }

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

    /* Takes the coordinates from which I want the neighbours. i = row, j = column
    * Returns an ArrayList of the alive neighbors. */
    public ArrayList<Tile> getAliveNeighbours(int i, int j){
        ArrayList<Tile> listOfNeighbours = new ArrayList<>();
        int x = -1;
        int y = -1;
        while (y <= i + 2){
            while (x <= j + 1){
                listOfNeighbours.add(grid[y][x]);
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
    private Color getWinningColor(ArrayList<Tile> neighbours){
        // TODO Maybe put this color finding code in separate method.
        Color colorOne; // Store one color in this field. TODO Maybe make an instance variable for this.
        Color colorTwo; // Store the other color in this field. TODO Maybe make an instance variable for this.
        int i = 0;
        while(!colorOne || !colorTwo){
            if(neighbours.get(i).isAlive() && !colorOne){
                colorOne = neighbours.get(i).getColor();
            } else if (neighbours.get(i).isAlive()) {
                colorTwo = neighbours.get(i).getColor();
            }
        }

        // Find the winning color
        int countColorOne = 0;
        int countColorTwo = 0;
        for(i = 0; i < neighbours.size(); i++){
            // TODO Is this a valid usage of switch or is this a SWITCH anti pattern?
            switch(neighbours.get(i).getColor()){
                case colorOne -> countColorOne++;
                case colorTwo -> countColorTwo++;
            }
        }
        // TODO Look at Andres rules if this is the correct behavior.
        if(countColorOne > countColorTwo) {
            return colorOne;
        }else{
            return colorTwo;
        }
    }
}
