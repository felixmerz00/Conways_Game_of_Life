package com.example.conways_game_of_life;

public interface InterfaceUI {

    String setPlayerName();
    String setPlayerColor();
    Coordinate deleteTile(Player aPlayer);
    Coordinate setTile(Player aPlayer);

}
