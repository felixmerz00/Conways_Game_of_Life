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
        Tile[][] deadArray = new Tile[18][18];
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                deadArray[y][x] = new Tile(x,y);
            }
        }

        // Assign the dead grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        gridField.set(aTestGrid, deadArray);

        // Call the UUT and get the resulting array.
        aTestGrid.makeGenerationStep();
        Tile[][] actualArray = (Tile[][]) gridField.get(aTestGrid);
        // Make assertion: The actualArray should still be completely dead.
        assertEquals(deadArray, actualArray);
    }

    // Test if the tiles which are not displayed (row 0 & 17 and column 0 & 17) always remain dead.
    @Test
    void testMakeGenerationStep2() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid();
        // Create grid on which I make the generation step.
        Tile[][] setupArray = new Tile[18][18];
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                setupArray[y][x] = new Tile(x,y);
            }
        }
        // Bring all tiles next to the border to live.
        for(int x = 1; x < 17; x++){    // Bring first row to live
            setupArray[1][x].setColor(Color.BLUE);
        }
        for(int x = 1; x < 17; x++){    // Bring last row to live
            setupArray[16][x].setColor(Color.BLUE);
        }
        for(int y = 2; y < 16; y++){    // Bring first column to live
            setupArray[y][1].setColor(Color.BLUE);
        }
        for(int y = 2; y < 16; y++){    // Bring last column to live
            setupArray[y][16].setColor(Color.BLUE);
        }

        // Assign the actual grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        gridField.set(aTestGrid, setupArray);

        // Create the expected grid.
        Tile[][] expectedArray = setupArray;
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

        // Call the UUT and get the resulting array.
        aTestGrid.makeGenerationStep();
        Tile[][] actualArray = (Tile[][]) gridField.get(aTestGrid);
        // Make assertion
        assertEquals(expectedArray, actualArray);
    }

    // Test a specific pattern.
    @Test
    void testMakeGenerationStep3() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid();
        // Create grid on which I make the generation step.
        Tile[][] setupArray = new Tile[18][18];
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                setupArray[y][x] = new Tile(x,y);
            }
        }
        // Create the pattern
        setupArray[1][2].setColor(Color.BLUE);
        setupArray[2][3].setColor(Color.RED);
        setupArray[3][1].setColor(Color.BLUE);
        setupArray[3][2].setColor(Color.BLUE);
        setupArray[3][3].setColor(Color.RED);

        // Assign the actual grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        gridField.set(aTestGrid, setupArray);

        // Create the expected grid.
        Tile[][] expectedArray = setupArray;
        expectedArray[1][2].setColor(Color.WHITE);
        expectedArray[2][1].setColor(Color.BLUE);
        expectedArray[3][1].setColor(Color.WHITE);
        expectedArray[4][2].setColor(Color.BLUE);

        // Call the UUT and get the resulting array.
        aTestGrid.makeGenerationStep();
        Tile[][] actualArray = (Tile[][]) gridField.get(aTestGrid);
        // Make assertion
        assertEquals(expectedArray, actualArray);
    }

    /* Test to cover the condition (colorZero == colorTwo) in getColorForDeadTile
    * which is currently on line 122. */
    @Test
    void testMakeGenerationStep4() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid();
        // Create grid on which I make the generation step.
        Tile[][] setupArray = new Tile[18][18];
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                setupArray[y][x] = new Tile(x,y);
            }
        }
        // Create the pattern
        setupArray[5][4].setColor(Color.BLUE);
        setupArray[5][5].setColor(Color.RED);
        setupArray[5][6].setColor(Color.BLUE);

        // Assign the actual grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        gridField.set(aTestGrid, setupArray);

        // Create the expected grid.
        Tile[][] expectedArray = setupArray;
        expectedArray[5][4].setColor(Color.WHITE);
        expectedArray[5][6].setColor(Color.WHITE);
        expectedArray[4][5].setColor(Color.BLUE);
        expectedArray[6][5].setColor(Color.BLUE);

        // Call the UUT and get the resulting array.
        aTestGrid.makeGenerationStep();
        Tile[][] actualArray = (Tile[][]) gridField.get(aTestGrid);
        // Make assertion
        assertEquals(expectedArray, actualArray);
    }

    // Test the method has Tiles.
    @Test
    void testHasTiles1() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid();
        // Create grid on which I make the generation step.
        Tile[][] setupArray = new Tile[18][18];
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                setupArray[y][x] = new Tile(x,y);
            }
        }
        // Random red values
        setupArray[5][13].setColor(Color.RED);
        setupArray[1][1].setColor(Color.RED);
        setupArray[16][15].setColor(Color.RED);
        // The blue tile I want to detect
        setupArray[16][16].setColor(Color.BLUE);

        // Assign the actual grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        gridField.set(aTestGrid, setupArray);

        // Call the UUT and assert the returned value.
        assertTrue(aTestGrid.hasTiles(Color.BLUE));
    }

    // TODO Test a few patterns with one or two players
}