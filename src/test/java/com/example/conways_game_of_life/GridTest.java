package com.example.conways_game_of_life;

import org.junit.jupiter.api.Test;


import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    @Test
    void testKill1() throws NoSuchFieldException, IllegalAccessException { //Input valid

        ArrayList<String> names = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();

        MockUI mockUI = new MockUI(names, colors, deleteTile, setTile);

        Grid aGrid = new Grid(Color.GREEN, Color.BLUE, mockUI);

        Tile[][] setupArray = getSetupArray();
        Coordinate c = new Coordinate(4,4);
        setupArray[c.y()][c.x()].setColor(Color.GREEN);

        // Assign the dead grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        // I pass a copy of the setup array to ensure having two separate instances.
        gridField.set(aGrid, setupArray);

        // Create a player
        Player player1 = new Player("Test1", Color.BLUE);

        // Call UUT
        aGrid.setKill(c, player1);

        // Make assertions
        Tile[][] actual = (Tile[][]) gridField.get(aGrid);
        assertSame(actual[c.y()][c.x()].getColor(), Color.WHITE);
    }

    @Test
    void testKill2() throws NoSuchFieldException, IllegalAccessException {  //input not valid to kill
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();

        //create coordinates to add there a valid tile in grid
        Coordinate validC = new Coordinate(4,4);
        deleteTile.add(validC);

        MockUI mockUI = new MockUI(names, colors, deleteTile, setTile);

        Grid aGrid = new Grid(Color.GREEN, Color.BLUE, mockUI);

        Tile[][] setupArray = getSetupArray();
        Coordinate c = new Coordinate(7,7);
        setupArray[c.y()][c.x()].setColor(Color.BLUE);  //tile is assigned to playing player -> invalid
        setupArray[validC.y()][validC.x()].setColor(Color.GREEN); // tile assigned to enemy player -> valid

        // Assign the dead grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        // I pass a copy of the setup array to ensure having two separate instances.
        gridField.set(aGrid, setupArray);

        // Create a player
        Player player1 = new Player("Test1", Color.BLUE);

        // Call UUT
        aGrid.setKill(c, player1);

        // Make assertions
        Tile[][] actual = (Tile[][]) gridField.get(aGrid);
        assertSame(actual[c.y()][c.x()].getColor(), Color.BLUE);
        assertSame(actual[validC.y()][validC.x()].getColor(), Color.WHITE);
    }

    @Test
    void testKill3() throws NoSuchFieldException, IllegalAccessException {  //input not valid to kill
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();

        //create coordinates to add there a valid tile in grid
        Coordinate validC = new Coordinate(3,3);
        deleteTile.add(validC);

        //Coordinate != validC to pass to method -> invalid
        Coordinate c = new Coordinate(6,6);

        MockUI mockUI = new MockUI(names, colors, deleteTile, setTile);

        Grid aGrid = new Grid(Color.GREEN, Color.BLUE, mockUI);

        Tile[][] setupArray = getSetupArray(); //all tiles are dead -> invalid to kill
        setupArray[validC.y()][validC.x()].setColor(Color.GREEN); // tile assigned to enemy player -> valid

        // Assign the dead grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        // I pass a copy of the setup array to ensure having two separate instances.
        gridField.set(aGrid, setupArray);

        // Create a player
        Player player1 = new Player("Test1", Color.BLUE);

        // Call UUT
        aGrid.setKill(c, player1);

        // Make assertions
        Tile[][] actual = (Tile[][]) gridField.get(aGrid);
        assertSame(actual[validC.y()][validC.x()].getColor(), Color.WHITE);
    }

    @Test
    void testSetTile1() throws IllegalAccessException, NoSuchFieldException { // give valid input
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();

        MockUI mockUI = new MockUI(names, colors, deleteTile, setTile);

        Grid aGrid = new Grid(Color.GREEN, Color.BLUE, mockUI);

        Tile[][] setupArray = getSetupArray(); //all tiles are dead -> valid
        Coordinate c = new Coordinate(2,2);

        // Assign the dead grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        // I pass a copy of the setup array to ensure having two separate instances.
        gridField.set(aGrid, setupArray);

        // Create a player
        Player player1 = new Player("Test1", Color.BLUE);

        // Call UUT
        aGrid.setTile(c, player1);

        // Make assertions
        Tile[][] actual = (Tile[][]) gridField.get(aGrid);
        assertSame(actual[c.y()][c.x()].getColor(), Color.BLUE);
    }

    @Test
    void testPlayerSetTile2() throws NoSuchFieldException, IllegalAccessException {  //input tile not possible to set -> tile is assigned to a player
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();

        //create coordinates to add there a valid tile in grid
        Coordinate validC = new Coordinate(15,4);
        setTile.add(validC); // tile is dead -> valid

        MockUI mockUI = new MockUI(names, colors, deleteTile, setTile);

        Grid aGrid = new Grid(Color.GREEN, Color.BLUE, mockUI);

        Tile[][] setupArray = getSetupArray();
        Coordinate c = new Coordinate(7, 12);
        setupArray[c.y()][c.x()].setColor(Color.BLUE);  //tile is assigned to playing player -> invalid

        // Assign the dead grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        // I pass a copy of the setup array to ensure having two separate instances.
        gridField.set(aGrid, setupArray);

        // Create a player
        Player player1 = new Player("Test1", Color.BLUE);

        // Call UUT
        aGrid.setTile(c, player1);

        // Make assertions
        Tile[][] actual = (Tile[][]) gridField.get(aGrid);
        assertSame(actual[c.y()][c.x()].getColor(), Color.BLUE);
        assertSame(actual[validC.y()][validC.x()].getColor(), Color.BLUE);
    }
    @Test
    void testPlayerSetTile3() throws NoSuchFieldException, IllegalAccessException {  //input not valid to kill -> tile of enemy player
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();

        //create coordinates to add there a valid tile in grid
        Coordinate validC = new Coordinate(3,3);
        setTile.add(validC);

        //invalid Coordinate to pass to method
        Coordinate c = new Coordinate(6,6);

        MockUI mockUI = new MockUI(names, colors, deleteTile, setTile);

        Grid aGrid = new Grid(Color.GREEN, Color.BLUE, mockUI);

        Tile[][] setupArray = getSetupArray();
        setupArray[c.y()][c.x()].setColor(Color.GREEN); //tile of enemy player

        // Assign the dead grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        // I pass a copy of the setup array to ensure having two separate instances.
        gridField.set(aGrid, setupArray);

        // Create a player
        Player player1 = new Player("Test1", Color.BLUE);

        // Call UUT
        aGrid.setTile(c, player1);

        // Make assertions
        Tile[][] actual = (Tile[][]) gridField.get(aGrid);
        assertSame(actual[validC.y()][validC.x()].getColor(), Color.BLUE);
        assertSame(actual[c.y()][c.x()].getColor(), Color.GREEN);
    }

    // Test if makeGenerationStep method works for a dead playing field.
    @Test
    void testMakeGenerationStep1() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid(Color.YELLOW, Color.ORANGE, getEmptyMockUI());
        // create a completely dead grid
        Tile[][] setupArray = getSetupArray();

        // Assign the dead grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        // I pass a copy of the setup array to ensure having two separate instances.
        gridField.set(aTestGrid, getCopy(setupArray));

        // Call the UUT and get the resulting array.
        aTestGrid.makeGenerationStep();
        Tile[][] actualArray = (Tile[][]) gridField.get(aTestGrid);
        // Make assertion: The actualArray should still be completely dead.
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                assertEquals(setupArray[y][x], actualArray[y][x]);
            }
        }
    }

    // Test if the tiles which are not displayed (row 0 & 17 and column 0 & 17) always remain dead.
    @Test
    void testMakeGenerationStep2() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid(Color.YELLOW, Color.ORANGE, getEmptyMockUI());
        // Create grid on which I make the generation step.
        Tile[][] setupArray = getSetupArray();

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
        gridField.set(aTestGrid, getCopy(setupArray));

        // Create the expected grid.
        Tile[][] expectedArray = getCopy(setupArray);
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
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                assertEquals(expectedArray[y][x], actualArray[y][x]);
            }
        }
    }

    // Test a specific pattern.
    @Test
    void testMakeGenerationStep3() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid(Color.YELLOW, Color.ORANGE, getEmptyMockUI());
        // Create grid on which I make the generation step.
        Tile[][] setupArray = getSetupArray();

        // Create the pattern
        setupArray[1][2].setColor(Color.BLUE);
        setupArray[2][3].setColor(Color.RED);
        setupArray[3][1].setColor(Color.BLUE);
        setupArray[3][2].setColor(Color.BLUE);
        setupArray[3][3].setColor(Color.RED);

        // Assign the actual grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        gridField.set(aTestGrid, getCopy(setupArray));

        // Create the expected grid.
        Tile[][] expectedArray = getCopy(setupArray);
        expectedArray[1][2].setColor(Color.WHITE);
        expectedArray[2][1].setColor(Color.BLUE);
        expectedArray[3][1].setColor(Color.WHITE);
        expectedArray[4][2].setColor(Color.BLUE);

        // Call the UUT and get the resulting array.
        aTestGrid.makeGenerationStep();
        Tile[][] actualArray = (Tile[][]) gridField.get(aTestGrid);
        // Make assertion
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                assertEquals(expectedArray[y][x], actualArray[y][x]);
            }
        }
    }

    /* Test to cover the condition (colorZero == colorTwo) in getColorForDeadTile
    * which is currently on line 122. */
    @Test
    void testMakeGenerationStep4() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid(Color.YELLOW, Color.ORANGE, getEmptyMockUI());
        // Create grid on which I make the generation step.
        Tile[][] setupArray = getSetupArray();

        // Create the pattern
        setupArray[5][4].setColor(Color.BLUE);
        setupArray[5][5].setColor(Color.RED);
        setupArray[5][6].setColor(Color.BLUE);

        // Assign the actual grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        gridField.set(aTestGrid, getCopy(setupArray));

        // Create the expected grid.
        Tile[][] expectedArray = getCopy(setupArray);
        expectedArray[5][4].setColor(Color.WHITE);
        expectedArray[5][6].setColor(Color.WHITE);
        expectedArray[4][5].setColor(Color.BLUE);
        expectedArray[6][5].setColor(Color.BLUE);

        // Call the UUT and get the resulting array.
        aTestGrid.makeGenerationStep();
        Tile[][] actualArray = (Tile[][]) gridField.get(aTestGrid);
        // Make assertion
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                assertEquals(expectedArray[y][x], actualArray[y][x]);
            }
        }
    }

    /* Test a specific configuration */
    @Test
    void testMakeGenerationStep5() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid(Color.YELLOW, Color.ORANGE, getEmptyMockUI());
        // Create grid on which I make the generation step.
        Tile[][] setupArray = getSetupArray();

        // Create the pattern
        setupArray[5][4].setColor(Color.BLUE);

        // Tile (1,1) should be orange.
        setupArray[1][1].setColor(Color.ORANGE);
        // Block 1
        setupArray[2][7].setColor(Color.YELLOW);
        setupArray[2][8].setColor(Color.YELLOW);
        setupArray[2][9].setColor(Color.YELLOW);
        setupArray[4][6].setColor(Color.YELLOW);
        setupArray[4][8].setColor(Color.YELLOW);
        setupArray[4][10].setColor(Color.YELLOW);
        setupArray[5][6].setColor(Color.YELLOW);
        setupArray[5][7].setColor(Color.YELLOW);
        setupArray[5][9].setColor(Color.YELLOW);
        setupArray[5][10].setColor(Color.YELLOW);
        setupArray[7][8].setColor(Color.YELLOW);
        // Block 2
        setupArray[6][4].setColor(Color.ORANGE);
        setupArray[6][5].setColor(Color.ORANGE);
        // This tile from block two remains dead.
        setupArray[7][5].setColor(Color.ORANGE);
        setupArray[8][2].setColor(Color.ORANGE);
        setupArray[8][4].setColor(Color.ORANGE);
        setupArray[8][7].setColor(Color.ORANGE);
        setupArray[9][2].setColor(Color.ORANGE);
        setupArray[9][5].setColor(Color.ORANGE);
        setupArray[10][4].setColor(Color.ORANGE);
        setupArray[10][5].setColor(Color.ORANGE);
        // Block 3
        setupArray[7][12].setColor(Color.YELLOW);
        setupArray[7][13].setColor(Color.YELLOW);
        setupArray[8][12].setColor(Color.YELLOW);
        setupArray[8][15].setColor(Color.YELLOW);
        setupArray[9][10].setColor(Color.YELLOW);
        setupArray[9][13].setColor(Color.YELLOW);
        setupArray[9][15].setColor(Color.YELLOW);
        setupArray[10][12].setColor(Color.YELLOW);
        setupArray[10][15].setColor(Color.YELLOW);
        setupArray[11][12].setColor(Color.YELLOW);
        setupArray[11][13].setColor(Color.YELLOW);
        // Block 4
        setupArray[10][9].setColor(Color.ORANGE);
        setupArray[12][7].setColor(Color.ORANGE);
        setupArray[12][8].setColor(Color.ORANGE);
        setupArray[12][10].setColor(Color.ORANGE);
        setupArray[12][11].setColor(Color.ORANGE);
        setupArray[13][7].setColor(Color.ORANGE);
        setupArray[13][9].setColor(Color.ORANGE);
        setupArray[13][11].setColor(Color.ORANGE);
        setupArray[15][8].setColor(Color.ORANGE);
        setupArray[15][9].setColor(Color.ORANGE);
        setupArray[15][10].setColor(Color.ORANGE);

        // Assign the actual grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        gridField.set(aTestGrid, setupArray);

        // Create the expected grid.
        Tile[][] expectedArray = getCopy(setupArray);
        expectedArray[5][4].setColor(Color.WHITE);
        expectedArray[5][6].setColor(Color.WHITE);
        expectedArray[4][5].setColor(Color.BLUE);
        expectedArray[6][5].setColor(Color.BLUE);

        // Call the UUT and get the resulting array.
        aTestGrid.makeGenerationStep();
        Tile[][] actualArray = (Tile[][]) gridField.get(aTestGrid);
        // Make assertion
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                assertEquals(expectedArray[y][x], actualArray[y][x]);
            }
        }
    }

    // Test the method hasTiles. Test if the hasTiles detects the living blue tiles.
    @Test
    void testHasTiles1() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid(Color.YELLOW, Color.ORANGE, getEmptyMockUI());
        // Create grid on which I make the generation step.
        Tile[][] setupArray = getSetupArray();

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

    /* Test the method hasTiles. Test if the hasTiles returns false if no tiles
    * of the given colors are alive anymore. */
    @Test
    void testHasTiles2() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid(Color.YELLOW, Color.ORANGE, getEmptyMockUI());
        // Create grid on which I make the generation step.
        Tile[][] setupArray = getSetupArray();

        // Random red values
        setupArray[5][13].setColor(Color.RED);
        setupArray[1][1].setColor(Color.RED);
        setupArray[16][15].setColor(Color.RED);

        // Assign the actual grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        gridField.set(aTestGrid, setupArray);

        // Call the UUT and assert the returned value.
        assertFalse(aTestGrid.hasTiles(Color.BLUE));
    }

    /* Test the method iterator.
     * Test if the iterator returns 16^2 elements as it should. */
    @Test
    void testIterator1() {
        Grid aTestGrid = new Grid(Color.YELLOW, Color.ORANGE, getEmptyMockUI());
        int countReturnedTiles = 0;

        for(Tile t: aTestGrid){
            countReturnedTiles++;
        }

        assertEquals(256, countReturnedTiles);
    }

    /* Test the method iterator.
     * Test if the iterator throws a NoSuchElementException if I try to access border tiles. */
    @Test
    void testIterator2() {
        Grid aTestGrid = new Grid();
        Iterator<Tile> aTestIterator = aTestGrid.iterator();
        // Run through all the visible tiles.
        while(aTestIterator.hasNext()){
            aTestIterator.next();
        }

        // Try to access an invisible border tile.
        try{
            aTestIterator.next();
            fail("NoSuchElementException should be thrown.");
        }catch (NoSuchElementException e){
            // If the code reaches this point, the test should pass.
        }
    }

    /* This is a helper method for other tests to avoid duplicate code.
    * It returns an array filled with dead Tile objects. */
    Tile[][] getSetupArray(){
        Tile[][] setupArray = new Tile[18][18];
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                setupArray[y][x] = new Tile(x,y);
            }
        }
        return setupArray;
    }

    private MockUI getEmptyMockUI(){
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();

        return new MockUI(names, colors, deleteTile, setTile);
    }

    // Takes a Tile[][] and returns a new Tile[][] with new tiles but the same color configuration.
    private Tile[][] getCopy(Tile[][] original){
        Tile[][] copy = new Tile[18][18];
        for(int y = 0; y < 18; y++){
            for(int x = 0; x < 18; x++){
                copy[y][x] = new Tile(x,y);
                copy[y][x].setColor(original[y][x].getColor());
            }
        }
        return copy;

    }
}