package com.example.conways_game_of_life;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import static com.example.conways_game_of_life.Color.AMBER;
import static com.example.conways_game_of_life.Color.YELLOW;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameLogicTest {

    @Test
    void setUpGameTest() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        GameLogic aGame = new GameLogic();

        //ArrayList creation
        ArrayList<String> names = new ArrayList<>();
        names.add("TestPlayer2");
        names.add("TestPlayer1");

        ArrayList<Color> colors = new ArrayList<>();
        colors.add(YELLOW);
        colors.add(AMBER);

        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();
        MockUI mockUI = new MockUI(names, colors, deleteTile, setTile);

        //injection of mockUI
        Field ui = GameLogic.class.getDeclaredField("ui");
        ui.setAccessible(true);
        ui.set(aGame, mockUI);

        //setUp game
        Method setUp = GameLogic.class.getDeclaredMethod("gameSetup");
        setUp.setAccessible(true);
        Object Null = setUp.invoke(aGame);
        System.out.println(Null);

        //make players in aGame accessible
        Field players = GameLogic.class.getDeclaredField("players");
        players.setAccessible(true);
        ArrayList<Player> actualPlayers = (ArrayList<Player>) players.get(aGame);

        //Test if player are setup correctly, in the correct order
        assertEquals(actualPlayers.get(0).getName(),"TestPlayer1" );
        assertEquals(actualPlayers.get(0).getPlayerColor(), AMBER);

        assertEquals(actualPlayers.get(1).getName(),"TestPlayer2" );
        assertEquals(actualPlayers.get(1).getPlayerColor(), YELLOW);

        //Test if grid is setup correctly?

    }
    //make sure that one player has no active tile?
    @Test
    void getWinnerTest(){

    }

    @Test
    void allPlayerHaveTilesTest(){

    }
}
