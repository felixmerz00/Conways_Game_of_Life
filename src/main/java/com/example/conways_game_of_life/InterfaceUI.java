package com.example.conways_game_of_life;

public interface InterfaceUI {

    String setPlayerName();
    Color setPlayerColor();
    Coordinate deleteTile(Player aPlayer);
    Coordinate setTile(Player aPlayer);

    void declareWinner(Player aPlayer);

    void UpdateGrid(Grid aGrid);
}
