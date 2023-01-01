package ui;

import core.GameLogic;
import model.Color;
import model.Coordinate;
import model.Player;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserInteractionTest {

    // Test setPlayerName for a correct input.
    @Test
    void testSetPlayerName1() throws NoSuchFieldException, IllegalAccessException {
        UserInteraction testUserInteraction;
        GameLogic testGameLogic = new GameLogic();
        Field uiField = GameLogic.class.getDeclaredField("ui");
        uiField.setAccessible(true);
        testUserInteraction = (UserInteraction) uiField.get(testGameLogic);

        // Set input stream.
        String input = "Felix";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Call UUT and make assertions
        String actual = testUserInteraction.setPlayerName(1);
        assertEquals("Felix", actual);
    }

    // Test setPlayerName for an incorrect input (a string of length zero).
    @Test
    void testSetPlayerName2() throws NoSuchFieldException, IllegalAccessException {
        UserInteraction testUserInteraction;
        GameLogic testGameLogic = new GameLogic();
        Field uiField = GameLogic.class.getDeclaredField("ui");
        uiField.setAccessible(true);
        testUserInteraction = (UserInteraction) uiField.get(testGameLogic);

        // Set input stream.
        String input = "\nFelix";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Call UUT and make assertions
        String actual = testUserInteraction.setPlayerName(1);
        assertEquals("Felix", actual);
    }

    // Test setPlayerColor for correct input.
    @Test
    void testSetPlayerColor1() throws NoSuchFieldException, IllegalAccessException {
        UserInteraction testUserInteraction;
        GameLogic testGameLogic = new GameLogic();
        Field uiField = GameLogic.class.getDeclaredField("ui");
        uiField.setAccessible(true);
        testUserInteraction = (UserInteraction) uiField.get(testGameLogic);

        // Set input stream.
        String input = "0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Call UUT and make assertions.
        Color actual = testUserInteraction.setPlayerColor(1);
        assertEquals(Color.YELLOW, actual);
    }

    // Test setPlayerColor with input of an integer below zero.
    @Test
    void testSetPlayerColor2() throws NoSuchFieldException, IllegalAccessException {
        UserInteraction testUserInteraction;
        GameLogic testGameLogic = new GameLogic();
        Field uiField = GameLogic.class.getDeclaredField("ui");
        uiField.setAccessible(true);
        testUserInteraction = (UserInteraction) uiField.get(testGameLogic);

        // Set input stream.
        String input = "-1\n8";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Call UUT and make assertions.
        Color actual = testUserInteraction.setPlayerColor(1);
        assertEquals(Color.GREEN, actual);
    }

    // Test setPlayerColor with input of an integer above eight.
    @Test
    void testSetPlayerColor3() throws NoSuchFieldException, IllegalAccessException {
        UserInteraction testUserInteraction;
        GameLogic testGameLogic = new GameLogic();
        Field uiField = GameLogic.class.getDeclaredField("ui");
        uiField.setAccessible(true);
        testUserInteraction = (UserInteraction) uiField.get(testGameLogic);

        // Set input stream.
        String input = "9\n7";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Call UUT and make assertions.
        Color actual = testUserInteraction.setPlayerColor(1);
        assertEquals(Color.TEAL, actual);
    }

    // Test setPlayerColor with input of an integer which was already chosen by the first player.
    @Test
    void testSetPlayerColor4() throws NoSuchFieldException, IllegalAccessException {
        UserInteraction testUserInteraction;
        GameLogic testGameLogic = new GameLogic();
        List<Player> testPlayerList = new ArrayList<>();

        Field uiField = GameLogic.class.getDeclaredField("ui");
        uiField.setAccessible(true);
        testUserInteraction = (UserInteraction) uiField.get(testGameLogic);

        testPlayerList.add(new Player("Felix", Color.TEAL));
        Field playerListField = UserInteraction.class.getDeclaredField("playerList");
        playerListField.setAccessible(true);
        playerListField.set(testUserInteraction, testPlayerList);

        // Set the input for the second player.
        String input = "7\n6";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Call UUT and make assertions.
        Color actual = testUserInteraction.setPlayerColor(2);
        assertEquals(Color.BLUE, actual);
    }

    // Test setPlayerColor with input which is of the wrong type e.g. not integer.
    @Test
    void testSetPlayerColor5() throws NoSuchFieldException, IllegalAccessException {
        UserInteraction testUserInteraction;
        GameLogic testGameLogic = new GameLogic();
        Field uiField = GameLogic.class.getDeclaredField("ui");
        uiField.setAccessible(true);
        testUserInteraction = (UserInteraction) uiField.get(testGameLogic);

        // Set input stream.
        String input = "ORANGE\n2";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Call UUT and make assertions.
        Color actual = testUserInteraction.setPlayerColor(1);
        assertEquals(Color.RED, actual);
    }

    // Test deleteTile for correct input.
    @Test
    void testDeleteTile1() throws NoSuchFieldException, IllegalAccessException {
        UserInteraction testUserInteraction;
        GameLogic testGameLogic = new GameLogic();
        Player testPlayer = new Player("Felix", Color.BLUE);

        Field uiField = GameLogic.class.getDeclaredField("ui");
        uiField.setAccessible(true);
        testUserInteraction = (UserInteraction) uiField.get(testGameLogic);

        // Set input stream
        String input = "1\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Call UUT and make assertions.
        Coordinate actual = testUserInteraction.deleteTile(testPlayer);
        Coordinate expected = new Coordinate(1, 1);
        assertEquals(expected, actual);
    }

    // Test deleteTile for an input smaller than 1.
    @Test
    void testDeleteTile2() throws NoSuchFieldException, IllegalAccessException {
        UserInteraction testUserInteraction;
        GameLogic testGameLogic = new GameLogic();
        Player testPlayer = new Player("Felix", Color.BLUE);

        Field uiField = GameLogic.class.getDeclaredField("ui");
        uiField.setAccessible(true);
        testUserInteraction = (UserInteraction) uiField.get(testGameLogic);

        // Set input stream
        String input = "0\n16\n1";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Call UUT and make assertions.
        Coordinate actual = testUserInteraction.deleteTile(testPlayer);
        Coordinate expected = new Coordinate(16, 1);
        assertEquals(expected, actual);
    }

    // Test deleteTile for an input greater than 16.
    @Test
    void testDeleteTile3() throws NoSuchFieldException, IllegalAccessException {
        UserInteraction testUserInteraction;
        GameLogic testGameLogic = new GameLogic();
        Player testPlayer = new Player("Felix", Color.BLUE);

        Field uiField = GameLogic.class.getDeclaredField("ui");
        uiField.setAccessible(true);
        testUserInteraction = (UserInteraction) uiField.get(testGameLogic);

        // Set input stream
        String input = "17\n1\n16";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Call UUT and make assertions.
        Coordinate actual = testUserInteraction.deleteTile(testPlayer);
        Coordinate expected = new Coordinate(1, 16);
        assertEquals(expected, actual);
    }

    // Test deleteTile for an input of the wrong type e.g. not an integer.
    @Test
    void testDeleteTile4() throws NoSuchFieldException, IllegalAccessException {
        UserInteraction testUserInteraction;
        GameLogic testGameLogic = new GameLogic();
        Player testPlayer = new Player("Felix", Color.BLUE);

        Field uiField = GameLogic.class.getDeclaredField("ui");
        uiField.setAccessible(true);
        testUserInteraction = (UserInteraction) uiField.get(testGameLogic);

        // Set input stream
        String input = "4.5\n16\n16";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Call UUT and make assertions.
        Coordinate actual = testUserInteraction.deleteTile(testPlayer);
        Coordinate expected = new Coordinate(16, 16);
        assertEquals(expected, actual);
    }

    // Test setTile for correct input.
    @Test
    void testSetTile1() throws NoSuchFieldException, IllegalAccessException {
        UserInteraction testUserInteraction;
        GameLogic testGameLogic = new GameLogic();
        Player testPlayer = new Player("Felix", Color.BLUE);

        Field uiField = GameLogic.class.getDeclaredField("ui");
        uiField.setAccessible(true);
        testUserInteraction = (UserInteraction) uiField.get(testGameLogic);

        // Set input stream
        String input = "13\n9";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Call UUT and make assertions.
        Coordinate actual = testUserInteraction.setTile(testPlayer);
        Coordinate expected = new Coordinate(13, 9);
        assertEquals(expected, actual);
    }
}