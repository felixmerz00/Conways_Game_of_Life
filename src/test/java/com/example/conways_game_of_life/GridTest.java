package com.example.conways_game_of_life;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
/*
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
*/
    // Test if makeGenerationStep method works for a dead playing field.
    @Test
    void testMakeGenerationStep1() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid();
        // create a completely dead grid
        Tile[][] deadGridArray = new Tile[18][18];
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                deadGridArray[y][x] = new Tile(x,y);
            }
        }

        // Assign the dead grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        gridField.set(aTestGrid, deadGridArray);

        // Call the UUT and make assertions.
        aTestGrid.makeGenerationStep();
        for(Tile t: aTestGrid){
            assertEquals(Color.WHITE, t.getColor());
        }
    }
}