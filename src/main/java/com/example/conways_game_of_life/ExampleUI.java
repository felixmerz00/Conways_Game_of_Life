package com.example.conways_game_of_life;

public class ExampleUI implements  InterfaceUI, Observer {
   private Grid aGrid;

    ExampleUI(GameLogic aGameLogic){
        aGameLogic.registerObserver(this);
    }
    @Override
    public String setPlayerName(int number_of_player) {
        return null;
    }

    @Override
    public Color setPlayerColor(int number_of_player) {
        return null;
    }

    @Override
    public Coordinate deleteTile(Player aPlayer) {
        return null;
    }

    @Override
    public Coordinate setTile(Player aPlayer) {
        return null;
    }

    @Override
    public void declareWinner(Player aPlayer) {
    }

    @Override
    public void updateGrid(Grid aGrid) {
        this.aGrid = aGrid;
    }
}
