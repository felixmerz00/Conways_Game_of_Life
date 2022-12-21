package com.example.conways_game_of_life;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    Random random = new Random();
    Coordinate coordinate = new Coordinate(random.nextInt(18), random.nextInt(18));
    Grid grid = new Grid();

    //check again because method is private (in Grid class)
    //Tile tile = grid.getCoordinateTile(coordinate);
    Player player = new Player("TestPlayer", Color.MAGENTA);

    @Test
    void testTilePlayer() {
        boolean out = tile.tileToPlayer(player);
        //because Tile has Color WHITE and Player MAGENTA, out should be false
        assertFalse(out);
    }
}