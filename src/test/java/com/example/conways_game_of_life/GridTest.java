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

        // Call the UUT
        aTestGrid.makeGenerationStep();
        // Make assertions.
        // Note I cannot use the Grid.iterator method because I also want to test the hidden border tiles.
        Tile[][] newGenGrid = (Tile[][]) gridField.get(aTestGrid);
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                assertEquals(Color.WHITE, newGenGrid[y][x].getColor());
            }
        }
    }

    // Test if the tiles which are not displayed (row 0 & 17 and column 0 & 17) always remain dead.
    @Test
    void testMakeGenerationStep2() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid();
        // Create grid on which I make the generation step.
        Tile[][] actualArray = new Tile[18][18];
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                actualArray[y][x] = new Tile(x,y);
            }
        }
        // Bring all tiles next to the border to live.
        for(int x = 1; x < 17; x++){    // Bring first row to live
            actualArray[1][x].setColor(Color.BLUE);
        }
        for(int x = 1; x < 17; x++){    // Bring last row to live
            actualArray[16][x].setColor(Color.BLUE);
        }
        for(int y = 2; y < 16; y++){    // Bring first column to live
            actualArray[y][1].setColor(Color.BLUE);
        }
        for(int y = 2; y < 16; y++){    // Bring last column to live
            actualArray[y][16].setColor(Color.BLUE);
        }

        // Assign the actual grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        gridField.set(aTestGrid, actualArray);

        // Create the expected grid.
        Tile[][] expectedArray = actualArray;
        for(int x = 3; x < 15; x++){    // Bring part of second row to live
            expectedArray[2][x].setColor(Color.BLUE);
        }
        for(int x = 3; x < 15; x++){    // Bring part of second last row to live
            expectedArray[15][x].setColor(Color.BLUE);
        }
        for(int y = 3; y < 15; y++){    // Bring part of second column to live
            expectedArray[y][2].setColor(Color.BLUE);
        }
        for(int y = 3; y < 15; y++){    // Bring part of second last column to live
            expectedArray[y][15].setColor(Color.BLUE);
        }

        // Call the UUT
        aTestGrid.makeGenerationStep();
        // Make assertions.
        // Note I cannot use the Grid.iterator method because I also want to test the hidden border tiles.
        assertEquals(expectedArray, actualArray);
    }
}