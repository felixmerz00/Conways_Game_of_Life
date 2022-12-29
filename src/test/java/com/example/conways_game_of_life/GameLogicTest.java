package com.example.conways_game_of_life;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import static com.example.conways_game_of_life.Color.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogicTest {
    private ArrayList<String> names;
    private ArrayList<Color> colors;

    @BeforeEach
    void init(){
        names = new ArrayList<>();
        names.add("TestPlayer2");
        names.add("TestPlayer1");

        colors = new ArrayList<>();
        colors.add(YELLOW);
        colors.add(ORANGE);
    }

    @Test
    void setUpGameTest() throws NoSuchFieldException, IllegalAccessException {
        GameLogic aGame = new GameLogic();

        //ArrayList creation
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();
        MockUI mockUI = new MockUI(names, colors, deleteTile, setTile);

        //injection of mockUI
        Field ui = GameLogic.class.getDeclaredField("ui");
        ui.setAccessible(true);
        ui.set(aGame, mockUI);

        //setUp game
        aGame.gameSetup();

        //make players in aGame accessible
        Field players = GameLogic.class.getDeclaredField("players");
        players.setAccessible(true);
        ArrayList<Player> actualPlayers = (ArrayList<Player>) players.get(aGame);

        //Test if player are setup correctly, in the correct order
        assertEquals(actualPlayers.get(0).getName(),"TestPlayer1" );
        assertEquals(actualPlayers.get(0).getPlayerColor(), ORANGE);

        assertEquals(actualPlayers.get(1).getName(),"TestPlayer2" );
        assertEquals(actualPlayers.get(1).getPlayerColor(), YELLOW);
    }
    //Tests when the second player wins
    @Test
    void getWinnerTest1() throws IllegalAccessException, NoSuchFieldException {
        GameLogic aGame = new GameLogic();

        //ArrayList creation & MockUI
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();
        MockUI mockUI = new MockUI(names, colors, deleteTile, setTile);

        //injection of mockUI
        Field ui = GameLogic.class.getDeclaredField("ui");
        ui.setAccessible(true);
        ui.set(aGame, mockUI);

        //GameSetUp
        aGame.gameSetup();

        //MockGrid initialization and injection
        MockGrid aMockGrid = new MockGrid(YELLOW, mockUI);
        Field grid = GameLogic.class.getDeclaredField("aGrid");
        grid.setAccessible(true);
        grid.set(aGame, aMockGrid);

        //declare Winner
        aGame.getWinner();

        assertSame(YELLOW, mockUI.getWinner().getPlayerColor());
        assertSame("TestPlayer2", mockUI.getWinner().getName());
    }

    //Tests when first player wins
    @Test
    void getWinnerTest2() throws IllegalAccessException, NoSuchFieldException {
        GameLogic aGame = new GameLogic();

        //ArrayList creation & MockUI
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();
        MockUI mockUI = new MockUI(names, colors, deleteTile, setTile);

        //injection of mockUI
        Field ui = GameLogic.class.getDeclaredField("ui");
        ui.setAccessible(true);
        ui.set(aGame, mockUI);

        //GameSetUp
        aGame.gameSetup();

        //MockGrid initialization and injection
        MockGrid aMockGrid = new MockGrid(ORANGE, mockUI);
        Field grid = GameLogic.class.getDeclaredField("aGrid");
        grid.setAccessible(true);
        grid.set(aGame, aMockGrid);

        //declare Winner
        aGame.getWinner();

        assertSame(ORANGE, mockUI.getWinner().getPlayerColor());
        assertSame("TestPlayer1", mockUI.getWinner().getName());
    }

    //Tests when both players lose
    @Test
    void getWinnerTest3() throws IllegalAccessException, NoSuchFieldException {
        GameLogic aGame = new GameLogic();

        //ArrayList creation & MockUI
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        ArrayList<Coordinate> setTile = new ArrayList<>();
        MockUI mockUI = new MockUI(names, colors, deleteTile, setTile);

        //injection of mockUI
        Field ui = GameLogic.class.getDeclaredField("ui");
        ui.setAccessible(true);
        ui.set(aGame, mockUI);

        //GameSetUp
        aGame.gameSetup();

        //MockGrid initialization and injection
        MockGrid aMockGrid = new MockGrid(WHITE, mockUI);
        Field grid = GameLogic.class.getDeclaredField("aGrid");
        grid.setAccessible(true);
        grid.set(aGame, aMockGrid);

        //declare Winner
        aGame.getWinner();

        assertSame(WHITE, mockUI.getWinner().getPlayerColor());
        assertSame("No One", mockUI.getWinner().getName());
    }

    //play game, testplayer1 wins with amber
    @Test
    void playGameTest() throws NoSuchFieldException, IllegalAccessException {
        GameLogic aGame = new GameLogic();

        //ArrayList creation & MockUI
        ArrayList<Coordinate> deleteTile = new ArrayList<>();
        Coordinate deletePlayer1 = new Coordinate(7,3);
        Coordinate deletePlayer2 = new Coordinate(1,2);
        deleteTile.add(deletePlayer1);
        deleteTile.add(deletePlayer2);

        ArrayList<Coordinate> setTile = new ArrayList<>();
        Coordinate setPlayer1 = new Coordinate(2,1);
        Coordinate setPlayer2 = new Coordinate(7,4);
        setTile.add(setPlayer1);
        setTile.add(setPlayer2);

        MockUI mockUI = new MockUI(names, colors, deleteTile, setTile);

        //injection of mockUI
        Field ui = GameLogic.class.getDeclaredField("ui");
        ui.setAccessible(true);
        ui.set(aGame, mockUI);

        //setUp game
        aGame.gameSetup();

        //MockGrid initialization and injection // color yellow wins
        MockGrid aMockGrid = new MockGrid(ORANGE, YELLOW, mockUI);
        Field grid = GameLogic.class.getDeclaredField("aGrid");
        grid.setAccessible(true);
        grid.set(aGame, aMockGrid);

        //play Game
        aGame.playGame();

        //declare winner
        aGame.getWinner();

        System.out.println(aMockGrid);
        assertSame(ORANGE, mockUI.getWinner().getPlayerColor());
        assertSame("TestPlayer1", mockUI.getWinner().getName());
        //it should be Yellow and testplayer2?
    }
}
