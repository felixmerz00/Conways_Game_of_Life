package core;

import model.Color;
import model.Coordinate;
import model.Player;
import model.Tile;
import ui.InterfaceUI;

public class MockGrid extends Grid {

    //set all Tiles in grid dead/white
    //set 2,2 to wished color to test getWinner
    public MockGrid(Color color, InterfaceUI ui){
        super(color, Color.WHITE, ui);
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                grid[y][x] = new Tile(x,y);
            }
        }
        grid[2][2].setColor(color);
    }

    //designs a pattern to test a couple rounds of the game to test playGame
    public MockGrid(Color color1, Color color2, InterfaceUI ui){
        super(color1, color2, ui);
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                grid[y][x] = new Tile(x,y);
            }
        }
        grid[2][2].setColor(color1);
        grid[2][3].setColor(color1);
        grid[3][2].setColor(color1);
        grid[3][3].setColor(color1);

        grid[6][2].setColor(color2);
        grid[6][3].setColor(color2);
        grid[7][2].setColor(color2);
        grid[7][3].setColor(color2);

    }

    @Override
    public void setKill(Coordinate c, Player player){
        Tile inputTile = grid[c.x()][c.y()];
        inputTile.setColor(Color.WHITE);
    }

    @Override
    public void setTile(Coordinate c, Player player){
        Tile tile = grid[c.x()][c.y()];
        tile.setColor(player.getPlayerColor());
    }
    @Override
    public void makeGenerationStep(){}
}
