package com.example.conways_game_of_life;

import java.util.Iterator;

public class GridIterator implements Iterator<Tile> {
    private Tile[][] grid;
    private int x, y;

    public GridIterator(Tile[][] grid) {
        this.grid = grid;
        x = 1;
        y = 1;
    }

    @Override
    public boolean hasNext() {
        // True if the coordinates do not point to the very last tile yet.
        return !(x == 16 && y == 16);
    }

    @Override
    public Tile next() {
        // Move the coordinates to the next tile.
        // Can I just assume that hasNext() equals true? If not I must check that first. How would I handle hasNext() == false?
        if(x == 16){
            x = 1;
            y++;
        } else{
            x++;
        }
        return grid[y][x];
    }
}
