package com.example.conways_game_of_life;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    Random random = new Random();
    Grid grid = new Grid();

    TileTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    }

    //make method public
    private Tile getCoordinateTile(Coordinate coordinate) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Grid.class.getDeclaredMethod("getCoordinateTile");
        method.setAccessible(true);
        return (Tile) method.invoke(coordinate);
    }
    Coordinate coordinate = new Coordinate(random.nextInt(18), random.nextInt(18));
    Tile tile = getCoordinateTile(coordinate);
    Player player = new Player("TestPlayer", Color.MAGENTA);

    @Test
    void testTilePlayer() {
        boolean out = tile.tileToPlayer(player);
        //because Tile has Color WHITE and Player MAGENTA, out should be false
        assertFalse(out);
    }

    @Test
    void testSetAlive() {
        boolean before = tile.isAlive();
        tile.setAlive(!before);
        boolean after = tile.isAlive();
        assertFalse(before == after);
    }

    @Test
    void testIsAlive() {
        tile.setAlive(true);
        assertTrue(tile.isAlive());

        tile.setAlive(false);
        assertFalse(tile.isAlive());
    }

    @Test
    void testSetColor() {
        tile.setColor(Color.GREEN);
        assertTrue(tile.getColor() == Color.GREEN);
    }

    //maybe check if class of returned value is Color
    @Test
    void testGetColor() {}

    @Test
    void testTileToPlayer() {
        Color tileColor = tile.getColor();
        Color playerColor = player.getPlayerColor();
        if (tileColor.equals(playerColor)) {
            assertTrue(tile.tileToPlayer(player));
        }
        else {assertFalse(tile.tileToPlayer(player));}
    }
}