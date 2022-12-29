package com.example.conways_game_of_life;

public interface InterfaceUI {

    String setPlayerName(int player);
    Color setPlayerColor(int player);
    Coordinate deleteTile(Player aPlayer);
    Coordinate setTile(Player aPlayer);

    void declareWinner(Player aPlayer);

    void updateGrid(Grid aGrid);
}
