package com.example.conways_game_of_life;

import org.junit.jupiter.api.Test;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    Random random = new Random();


    //no test for grid.getTile ?
    //helper method to make Grid.getTile accessible
    /*public Tile getTileAt(int x, int y) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Grid.class.getDeclaredMethod("getTileAt");
        method.setAccessible(true);
        return (Tile) method.invoke(x, y);
    }

    @Test
    void testGetTileAt() {
        //lists to implement MockUI
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();

        //create instance of MockUI
        MockUI ui = new MockUI(names, colors, deleteTile, setTile);

        //create instance of Grid
        Grid grid = new Grid(Color.BLUE, Color.AMBER, ui);


        //create Tile
        Tile tile = new Tile(random.nextInt(2,16), random.nextInt(2,16));

        Tile tile1= grid.getTileAt(tile.getX(), tile.getY()));
    }*/

    //helper method to make Grid.validKill accessible
    public boolean validKill(Tile tile, Player player) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Grid.class.getDeclaredMethod("validKill");
        method.setAccessible(true);
        return (boolean) method.invoke(tile, player);
    }
    @Test
    void testValidKill() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Player player = new Player("Test", Color.BLUE);
        Tile tile = new Tile(random.nextInt(2,16), random.nextInt(2,16));
        //when Tile.getColor == WHITE -> invalid kill
        assertFalse(validKill(tile, player));
        //when Tile.getColor == Player.getPlayerColor -> invalid kill
        tile.setColor(Color.BLUE);
        assertFalse(validKill(tile, player));
        //when Tile.getColor != Color.WHITE && Tile.getColor != Player.getPlayerColor
        tile.setColor(Color.MAGENTA);
        assertTrue(validKill(tile, player));
    }
/*
    @Test
    void testKill1() { //Input valid
        //create Tile in Grid which is valid to kill: tile.color is color of enemy player2 (AMBER)
        Tile validTile = new Tile(random.nextInt(2,16), random.nextInt(2, 16));
        validTile.setColor(Color.GREEN);

        //use MockUI to test -> create lists & only fill in lists i use in test
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();

        MockUI ui = new MockUI(names, colors, deleteTile, setTile);

        //create two players
        Player player1 = new Player("Test1", Color.BLUE);

        //create Grid
        Grid grid = new Grid(Color.BLUE, Color.GREEN, ui);

        //input tile is valid to kill for player1
        grid.kill(validTile.getX(), validTile.getY(), player1); //valid input: player.BLUE can kill tile.AMBER
        assertTrue(validTile.getColor() == Color.WHITE); //tile should be dead -> color white
    }

 */

    /*
    @Test
    void testKill2() {  //input not valid to kill
        //create Tile in Grid which is not valid to kill for player1 with color BLUE
        Tile invalidTile = new Tile(random.nextInt(2,16),random.nextInt(2,16));
        invalidTile.setColor(Color.BLUE);

        //create Tile in Grid which is valid to kill: tile.color is color of enemy player2 (AMBER)
        Tile validTile = new Tile(random.nextInt(2,16),random.nextInt(2,16));
        validTile.setColor(Color.GREEN);

        //use MockUI to test -> create lists & only fill in lists i use in test
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();

        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        deleteTile.add(new Coordinate(validTile.getX(),validTile.getY())); //validKill Tile

        MockUI ui = new MockUI(names, colors, deleteTile, setTile);

        //create two players
        Player player1 = new Player("Test1", Color.BLUE);

        //create Grid
        Grid grid = new Grid(Color.BLUE, Color.GREEN, ui);

        //input tile is invalid to kill for player1
        grid.kill(invalidTile.getX(), invalidTile.getY(), player1); //method will ask new tile (valid) from ui
        assertTrue(validTile.getColor() == Color.WHITE); //validTile: tile has to be dead
        assertFalse(invalidTile.getColor() == Color.BLUE); //invalidTile: nothing should be changed
    }
     */

    //helper method to make Grid.validSetTile accessible
    public boolean validSetTile(Tile tile) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = Grid.class.getDeclaredMethod("validSetTile");
        method.setAccessible(true);
        return (boolean) method.invoke(tile);
    }

    @Test
    void testValidSetTile() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Tile tile = new Tile(random.nextInt(2, 16), random.nextInt(2,16));
        //only if Tile.getColor = WHITE, Grid.validSetTile returns true
        assertTrue(validSetTile(tile));
        tile.setColor(Color.MAGENTA);
        assertFalse(validSetTile(tile)); // tile with color other than WHITE -> validSetTile returns false
    }

    /*
    @Test
    void testPlayerSetTile1() { // give valid input
        //create Tile in Grid which is valid to set: tile.color has to be Color.WHITE
        Tile validTile = new Tile(random.nextInt(18), random.nextInt(18)); //color White is set automatically

        //use MockUI to test -> create lists & only fill in lists we use in test
        //-> no lists needed because we have valid input
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();

        //create Instance of MockUI
        MockUI ui = new MockUI(names, colors, deleteTile, setTile);

        //create players
        Player player1 = new Player("Test1", Color.BLUE);

        //create Grid
        Grid grid = new Grid(Color.BLUE, Color.GREEN, ui);

        // TODO This test does test if the tile from the grid was changed.
        //input tile is valid to set for player1
        grid.playerSetTile(validTile.getCoordinate(), player1); //valid input: player can set tile.White
        assertTrue(validTile.getColor() == player1.getPlayerColor()); //valid tile should be assigned to player1
        assertFalse(validTile.getColor() == Color.WHITE);
    }

     */

    /*
    @Test
    void testPlayerSetTile2() {  //input tile not possible to set -> tile is assigned to a player
        //create Tile in Grid which is not valid to kill for player1 with color BLUE
        Tile invalidTile = new Tile(random.nextInt(2,16), random.nextInt(2, 16));
        invalidTile.setColor(Color.BLUE); //set color other than white

        //create Tile in Grid which is valid to kill: tile.color is WHITE
        Tile validTile = new Tile(random.nextInt(2, 16), random.nextInt(2, 16));
        validTile.setColor(Color.WHITE);

        //use MockUI to test -> create lists & only fill in lists i use in test
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Color> colors = new ArrayList<>();
        ArrayList<Coordinate> deleteTile = new ArrayList<>();

        //add valid tile to setTile list
        ArrayList<Coordinate> setTile = new ArrayList<>();
        setTile.add(new Coordinate(validTile.getX(),validTile.getY())); //validKill Tile

        MockUI ui = new MockUI(names, colors, deleteTile, setTile);

        //create two players
        Player player1 = new Player("Test1", Color.BLUE);

        //create Grid
        Grid grid = new Grid(Color.BLUE, Color.GREEN, ui);

        // TODO This test does not if the tile from the grid was changed.
        //input tile is invalid to kill for player1
        grid.playerSetTile(invalidTile.getCoordinate(), player1); //method will ask new tile from ui
        assertTrue(invalidTile.getColor() == Color.BLUE); //nothing should be changed at invalid tile
        assertTrue(validTile.getColor() == player1.getPlayerColor()); //tile has to be assigned to player1
    }

     */

    // Test if makeGenerationStep method works for a dead playing field.
    @Test
    void testMakeGenerationStep1() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid();
        // create a completely dead grid
        Tile[][] setupArray = getSetupArray();

        // Assign the dead grid to the grid field of the aTestGrid.
        Field gridField = Grid.class.getDeclaredField("grid");
        gridField.setAccessible(true);
        gridField.set(aTestGrid, setupArray);

        // Call the UUT and get the resulting array.
        aTestGrid.makeGenerationStep();
        Tile[][] actualArray = (Tile[][]) gridField.get(aTestGrid);
        // Make assertion: The actualArray should still be completely dead.
        assertEquals(setupArray, actualArray);
    }

    // Test if the tiles which are not displayed (row 0 & 17 and column 0 & 17) always remain dead.
    @Test
    void testMakeGenerationStep2() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid();
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
        Tile[][] setupArray = getSetupArray();

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

    // Test the method hasTiles. Test if the hasTiles detects the living blue tiles.
    @Test
    void testHasTiles1() throws NoSuchFieldException, IllegalAccessException {
        Grid aTestGrid = new Grid();
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
        Grid aTestGrid = new Grid();
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
        Grid aTestGrid = new Grid();
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
}