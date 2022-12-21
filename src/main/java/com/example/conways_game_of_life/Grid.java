package com.example.conways_game_of_life;

import java.util.ArrayList;
import java.util.Optional;

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
    public void makeGenerationStep(){
        // Iterate over all tiles
        for(tile: grid) {
            ArrayList<Tile> aliveNeighbors = getAliveNeighbours(i, j);
            tile.setColor(getWinningColor(aliveNeighbors));
        }
        // Update the drawn grid on the GUI.
        draw();
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
        // TODO Maybe put this color finding code in separate method.
        Optional<Color> colorOne = Optional.empty(); // Store one color in this field. TODO Maybe make an instance variable for this.
        Optional<Color> colorTwo = Optional.empty(); // Store the other color in this field. TODO Maybe make an instance variable for this.
        int i;
        for (i = 0; i < aliveNeighbours.size(); i++){
            Color cur = aliveNeighbours.get(i).getColor();
            if(colorOne.isEmpty()){
                colorOne = Optional.of(cur);
            } else if(colorTwo.isEmpty() && cur != colorOne.get()){   // If there is an alive cell with the second color, I store it in colorTwo
                colorTwo = Optional.of(cur);
            }
        }

        // Find the winning color
        int countColorOne = 0;
        int countColorTwo = 0;
        for(i = 0; i < aliveNeighbours.size(); i++){
            Color cur = aliveNeighbours.get(i).getColor();
            if(colorOne.get() == cur){countColorOne++;  // Color one is never empty, that's why I don't have to check it.
            } else if (colorTwo.isPresent() && colorTwo.get() == cur) {countColorTwo++;}
        }
        // TODO Look at Andres rules if this is the correct behavior.
        if(countColorOne > countColorTwo) {
            return colorOne;
        }else{
            return colorTwo;
        }
    }
}
