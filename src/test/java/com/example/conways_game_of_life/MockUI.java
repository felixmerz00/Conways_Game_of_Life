package com.example.conways_game_of_life;

import java.util.ArrayList;

public class MockUI implements InterfaceUI{

    private Player winner;// = new Player("Not Set", Color.RED);
    private final ArrayList<String> names;
    private final ArrayList<Color> colors;
    private final ArrayList<Coordinate> deleteTile;
    private final ArrayList<Coordinate> setTile;

    public MockUI(ArrayList<String> names, ArrayList<Color> colors, ArrayList<Coordinate> deleteTile, ArrayList<Coordinate> setTile){
        this.names = names;
        this.colors = colors;
        this.deleteTile = deleteTile;
        this.setTile = setTile;
    }

    public String setPlayerName(int one){
        String name = names.get(0);
        names.remove(0);
        return name;
    }

    public Color setPlayerColor(int one){
        Color color = colors.get(0);
        colors.remove(0);
        return color;
    }
    public Coordinate deleteTile(Player aPlayer){
        Coordinate coordinate = deleteTile.get(0);
        deleteTile.remove(0);
        return coordinate;
    }
    public Coordinate setTile(Player aPlayer){
        Coordinate coordinate = setTile.get(0);
        setTile.remove(0);
        return coordinate;
    }

    public Player getWinner() {
        return winner;
    }

    public void declareWinner(Player aPlayer){
        this.winner = aPlayer;
    }

    @Override
    public void displayEndTurnInfo() {

    }
}
