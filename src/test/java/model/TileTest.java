package model;

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
        assertEquals(tile.getColor(), Color.WHITE);
        assertEquals(tile.getCoordinate().x(), x);
        assertEquals(tile.getCoordinate().y(), y);
    }

    @Test
    void testSetColor() {
        Tile tile = new Tile(random.nextInt(18), random.nextInt(18));
        tile.setColor(Color.MAGENTA);
        assertEquals(tile.getColor(), Color.MAGENTA);
    }

    @Test
    void testGetColor() {
        Tile tile = new Tile(random.nextInt(18), random.nextInt(18));
        assertEquals(tile.getColor(), Color.WHITE);
        tile.setColor(Color.GREEN);
        assertEquals(tile.getColor(), Color.GREEN);
    }

    // The tiles are equal.
    @Test
    void testEquals1() {
        Tile t1 = new Tile(0, 0);
        Tile t2 = new Tile(0, 0);
        assertEquals(t1, t2);
    }

    // The tiles differ in their color.
    @Test
    void testEquals2() {
        Tile t1 = new Tile(0, 0);
        t1.setColor(Color.BLUE);
        Tile t2 = new Tile(0, 0);
        assertNotEquals(t1, t2);
    }

    // The tiles differ in their x coordinate.
    @Test
    void testEquals3() {
        Tile t1 = new Tile(0, 0);
        Tile t2 = new Tile(1, 0);
        assertNotEquals(t1, t2);
    }

    // The tiles differ in their y coordinate.
    @Test
    void testEquals4() {
        Tile t1 = new Tile(0, 0);
        Tile t2 = new Tile(0, 1);
        assertNotEquals(t1, t2);
    }

    // Object 2 has a different type.
    @Test
    void testEquals5() {
        Tile t1 = new Tile(0, 0);
        Integer t2 = 10;
        assertNotEquals(t1, t2);
    }

    @Test
    void testToString() {
        Tile testTile = new Tile(0, 0);
        testTile.setColor(Color.BLUE);
        assertEquals("B", testTile.toString());
    }
}
