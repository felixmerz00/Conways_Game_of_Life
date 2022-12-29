package com.example.conways_game_of_life;

public class MockGrid extends Grid{

    InterfaceUI ui;
    //set all Tiles in grid dead/white

    //set 2,2 to wished color
    public MockGrid(Color color, InterfaceUI ui){
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                grid[y][x] = new Tile(x,y);
            }
        }
        grid[2][2].setColor(color);
    }

    public MockGrid(Color color1, Color color2, InterfaceUI ui){
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
}
