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
}