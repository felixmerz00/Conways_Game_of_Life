package com.example.conways_game_of_life;

public class MockGrid extends Grid{

    //set all Tiles in grid dead/white
    //set 2,2 to wished color to test getWinner
    public MockGrid(Color color){
        grid[2][2].setColor(color);
    }

    //designs a pattern to test a couple rounds of the game to test playGame
    public MockGrid(Color color1, Color color2){
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
