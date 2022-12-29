package com.example.conways_game_of_life;

public class MockGrid extends Grid{

    InterfaceUI ui;
    //set all Tiles in grid dead/white

    //set 2,2 to wished color
    public MockGrid(Color color, InterfaceUI ui){
        this.ui = ui;
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                grid[y][x] = new Tile(x,y);
            }
        }
        grid[2][2].setColor(color);
    }

    public MockGrid(Color color1, Color color2, InterfaceUI ui){
        this.ui = ui;
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

    private Tile getTileAt(int x, int y){
        return grid[y][x];
    }
    @Override
    public void kill(int x, int y, Player player){
        Tile inputTile = getTileAt(x, y);
        inputTile.setColor(Color.WHITE);
    }

    @Override
    public void playerSetTile(int x, int y, Player player){
        Tile tile = getTileAt(x,y);
        tile.setColor(player.getPlayerColor());
    }
    @Override
    public void makeGenerationStep(){}
}
