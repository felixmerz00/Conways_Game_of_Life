package com.example.conways_game_of_life;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.lang.reflect.Field;

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
        Field uiField = GameLogic.class.getDeclaredField("ui");
        uiField.setAccessible(true);
        testUserInteraction = (UserInteraction) uiField.get(testGameLogic);

        // Set input for the first player.
        String input = "7\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        testUserInteraction.setPlayerColor(1);
        // Set the input for the second player.
        input = "7\n6";
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
}