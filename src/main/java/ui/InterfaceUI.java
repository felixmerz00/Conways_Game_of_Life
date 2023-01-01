package ui;

import model.Color;
import model.Coordinate;
import model.Player;

public interface InterfaceUI {

    String setPlayerName(int player);
    Color setPlayerColor(int player);
    Coordinate deleteTile(Player aPlayer);
    Coordinate setTile(Player aPlayer);

    void declareWinner(Player aPlayer);
    void displayEndTurnInfo();
}
