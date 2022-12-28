package com.example.conways_game_of_life;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    Player testPlayer;

    @BeforeEach
    void init(){
        testPlayer = new Player("testPlayer", Color.ORANGE);
    }

    @Test
    void nameTest(){
        assertEquals("testPlayer", testPlayer.getName());
    }

    @Test
    void colorTest(){
        assertEquals(Color.ORANGE, testPlayer.getPlayerColor());
    }
}
