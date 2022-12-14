package core;

import ui.InterfaceUI;
import model.Color;
import model.Coordinate;
import model.Player;
import model.Tile;

import java.util.ArrayList;
import java.util.Iterator;

public class Grid implements Iterable<Tile>{
    protected Tile[][] grid;
    private final Color[][] lastGenColors;    // Store colors of the outgoing Generation. This is used as a reference for the makeGenerationStep method.

    private final InterfaceUI ui;

    private int numberOfGenerations = 0;

    // This constructor initializes the Tile[][] grid with a predetermined starting pattern.
    public Grid(Color colorPlayerOne, Color colorPlayerTwo, InterfaceUI ui) {
        this.ui = ui;
        grid = getInitialGrid(colorPlayerOne, colorPlayerTwo);    // Create grid with initial configuration.
        lastGenColors = new Color[18][18];  // Initialize lastGenColors. I don't need to set the values here.
    }

    // This is a helper method for the constructor. It creates the Tile[][] with determined starting pattern.
    private Tile[][] getInitialGrid(Color colorPlayerOne, Color colorPlayerTwo){
        Tile[][] initialGrid = new Tile[18][18];
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                initialGrid[y][x] = new Tile(x,y);
            }
        }
        // Block 1
        initialGrid[2][7].setColor(colorPlayerOne);
        initialGrid[2][8].setColor(colorPlayerOne);
        initialGrid[2][9].setColor(colorPlayerOne);
        initialGrid[4][6].setColor(colorPlayerOne);
        initialGrid[4][8].setColor(colorPlayerOne);
        initialGrid[4][10].setColor(colorPlayerOne);
        initialGrid[5][6].setColor(colorPlayerOne);
        initialGrid[5][7].setColor(colorPlayerOne);
        initialGrid[5][9].setColor(colorPlayerOne);
        initialGrid[5][10].setColor(colorPlayerOne);
        initialGrid[7][8].setColor(colorPlayerOne);
        // Block 2
        initialGrid[6][4].setColor(colorPlayerTwo);
        initialGrid[6][5].setColor(colorPlayerTwo);
        initialGrid[7][2].setColor(colorPlayerTwo);
        initialGrid[7][5].setColor(colorPlayerTwo);
        initialGrid[8][2].setColor(colorPlayerTwo);
        initialGrid[8][4].setColor(colorPlayerTwo);
        initialGrid[8][7].setColor(colorPlayerTwo);
        initialGrid[9][2].setColor(colorPlayerTwo);
        initialGrid[9][5].setColor(colorPlayerTwo);
        initialGrid[10][4].setColor(colorPlayerTwo);
        initialGrid[10][5].setColor(colorPlayerTwo);
        // Block 3
        initialGrid[7][12].setColor(colorPlayerOne);
        initialGrid[7][13].setColor(colorPlayerOne);
        initialGrid[8][12].setColor(colorPlayerOne);
        initialGrid[8][15].setColor(colorPlayerOne);
        initialGrid[9][10].setColor(colorPlayerOne);
        initialGrid[9][13].setColor(colorPlayerOne);
        initialGrid[9][15].setColor(colorPlayerOne);
        initialGrid[10][12].setColor(colorPlayerOne);
        initialGrid[10][15].setColor(colorPlayerOne);
        initialGrid[11][12].setColor(colorPlayerOne);
        initialGrid[11][13].setColor(colorPlayerOne);
        // Block 4
        initialGrid[10][9].setColor(colorPlayerTwo);
        initialGrid[12][7].setColor(colorPlayerTwo);
        initialGrid[12][8].setColor(colorPlayerTwo);
        initialGrid[12][10].setColor(colorPlayerTwo);
        initialGrid[12][11].setColor(colorPlayerTwo);
        initialGrid[13][7].setColor(colorPlayerTwo);
        initialGrid[13][9].setColor(colorPlayerTwo);
        initialGrid[13][11].setColor(colorPlayerTwo);
        initialGrid[15][8].setColor(colorPlayerTwo);
        initialGrid[15][9].setColor(colorPlayerTwo);
        initialGrid[15][10].setColor(colorPlayerTwo);

        return initialGrid;
    }

    /* This method kills the tile at the given coordinate, if this is not possible
    * it asks for another tile to kill.*/
    public void setKill(Coordinate c, Player player) {
        Tile inputTile = grid[c.y()][c.x()];
        // A player can kill a tile if it isn't his own tile or a dead tile.
        if (inputTile.getColor() != player.getPlayerColor() && inputTile.getColor() != Color.WHITE) {
            inputTile.setColor(Color.WHITE);
        }
        else {  //we need other Tile to kill
            setKill(ui.deleteTile(player), player);
        }
    }

    /* This method brings the tile at the given coordinate to life for the given player.
     * If this is not possible for the given tile, it asks for another tile.*/
    public void setTile(Coordinate c, Player player) {
        Tile inputTile = grid[c.y()][c.x()];
        // If the given tile is dead it can be brought to life for the given player.
        if (inputTile.getColor() == Color.WHITE) {
            inputTile.setColor(player.getPlayerColor());
        }
        else {
            setTile(ui.setTile(player), player);
        }
    }

    // This method replaces the current generation with its successor.
    public void makeGenerationStep(){
        updateLastGenColors();  // Store outgoing configuration of colors in the lastGenColors array.
        // Iterate over all tiles except the tiles at the border (Row 0 & 17 and column 0 & 17)
        for(int y = 1; y < 17; y++){
            for(int x = 1; x < 17; x++){
                Tile curTile = grid[y][x];
                ArrayList<Color> aliveNeighbors = getAliveNeighbours(y, x);

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
        numberOfGenerations++;
    }

    /* Takes the coordinates from which I want the alive neighbours.
    * Returns an ArrayList of the alive neighbors. */
    private ArrayList<Color> getAliveNeighbours(int row, int column){
        ArrayList<Color> listOfNeighbours = new ArrayList<>();
        int y = row - 1;
        int x = column - 1;
        while (y <= row + 1){
            while (x <= column + 1){
                // Add the neighbour to the list if it is alive (and in fact a neighbour).
                if (lastGenColors[y][x] != Color.WHITE && (y != row || x != column)){
                    listOfNeighbours.add(lastGenColors[y][x]);
                }
                x++;
            }
            y++;
            x = column - 1;
        }
        return listOfNeighbours;
    }

    /* This method takes the list of neighbours of a dead tile.
    * It compares the colors of the neighbours and
    * returns the color that should be used to color the tile. */
    private Color getColorForDeadTile(ArrayList<Color> aliveNeighbours){
        // If the cell is dead, then it springs to life only in the case that it has 3 live neighbors.
        if(aliveNeighbours.size() != 3){
            return Color.WHITE;
        }
        Color colorZero = aliveNeighbours.get(0);
        Color colorOne = aliveNeighbours.get(1);
        Color colorTwo = aliveNeighbours.get(2);
        // This covers if-else block covers all six possible combinations of colors.
        if(colorZero == colorOne || colorZero == colorTwo){ // Color zero occurs twice
            return colorZero;
        }else{  // The colorOne and colorTwo are equal, meaning this is the winning color.
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
        for(int y = 1; y < 17; y++){
            for(int x = 1; x < 17; x++){
                if(grid[y][x].getColor() == playersColor){
                    return true;
                }
            }
        }
        return false;
    }

    /* When using this method, do not store the iterator. Updates in the grid will not show in the
    * Iterator<Tile> object. Create a new iterator every time. */
    @Override
    public Iterator<Tile> iterator() {
        return new GridIterator(grid);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("\n");
        if (numberOfGenerations > 0){
            s.append("  ======================   NEW GENERATION   =======================\n");
        }else {
            s.append("  =======================   STARTING GRID   =======================\n");
        }
        s.append("    1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16 \n");
        s.append("--+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+--\n");

        for(int y = 1; y < 17; y++){
            if(y < 10){s.append("0").append(y);
            }else{s.append(y);}
            for(int x = 1; x < 17; x++){
                s.append("| ");
                s.append((grid[y][x]).toString());
                s.append(" ");
            }
            s.append("|");
            if(y < 10){s.append("0").append(y).append("\n");
            }else{s.append(y).append("\n");}
        }

        s.append("--+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+---+--\n");
        s.append("    1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16 \n");
        s.append("---------------------------------------------------------------------\n");

        return s.toString();
    }

    public int getNumberOfGenerations() {
        return numberOfGenerations;
    }
}
