package com.example.conways_game_of_life;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    GridTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
    }

    //make method public
    private Tile getCoordinateTile(Coordinate coordinate) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Grid.class.getDeclaredMethod("getCoordinateTile");
        method.setAccessible(true);
        return (Tile) method.invoke(coordinate);
    }
    Random random = new Random();
    Grid grid = new Grid();
    Coordinate coordinate = new Coordinate(random.nextInt(18), random.nextInt(18));
    Tile tile = getCoordinateTile(coordinate);
    Player player = new Player("TestPlayer", Color.MAGENTA);


    @Test
    void testKill() {
        grid.kill(coordinate);
        assertFalse(tile.isAlive());
        assertTrue(tile.getColor() == Color.WHITE);
    }

    @Test
    void testPlayerSetTile() {
        grid.playerSetTile(coordinate, player);
        assertTrue(tile.getColor().equals(player.getPlayerColor()));
        assertTrue(tile.isAlive());
    }
}