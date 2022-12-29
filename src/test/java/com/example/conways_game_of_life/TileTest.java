package com.example.conways_game_of_life;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    Random random = new Random();

    //Test if constructor sets x and y correctly && tile.color to WHITE and
    @Test
    void testTile() {
        int x = random.nextInt(18);
        int y = random.nextInt(18);
        Tile tile = new Tile(x,y);
        //in constructor, color of tile is automatically set to Color.WHITE
        assertTrue(tile.getColor() == Color.WHITE);
        assertEquals(tile.getX(), x);
        assertEquals(tile.getY(), y);
    }

    @Test
    void testSetColor() {
        Tile tile = new Tile(random.nextInt(18), random.nextInt(18));
        tile.setColor(Color.MAGENTA);
        assertTrue(tile.getColor() == Color.MAGENTA);
    }

    @Test
    void testGetColor() {
        Tile tile = new Tile(random.nextInt(18), random.nextInt(18));
        assertTrue(tile.getColor() == Color.WHITE);
        tile.setColor(Color.GREEN);
        assertTrue(tile.getColor() == Color.GREEN);
    }

    @Test
    void testGetX() {
        Tile tile = new Tile(2, 3); //x == 2
        assertTrue(tile.getX() == 2);
    }

    @Test
    void testGetY() {
        Tile tile = new Tile(2, 3); //y == 3
        assertTrue(tile.getY() == 3);
    }

    @Test
    void testTileToPlayer() {
        Tile tile = new Tile(random.nextInt(18), random.nextInt(18));
        Player player = new Player("Test", Color.BLUE);
        tile.setColor(Color.BLUE);
        assertTrue(tile.tileToPlayer(player));
    }
}
