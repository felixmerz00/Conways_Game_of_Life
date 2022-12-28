package com.example.conways_game_of_life;

public class UserInteraction implements InterfaceUI {

    GUI graphicInterface = new GUI();
    Color listOfColors = new Color();

    @Override
    public String setPlayerName() {
        String name = graphicInterface.getName();

        // check if name is valid
        // can be done much easier in GUI

        return name;
    }

    @Override
    public Color setPlayerColor() {

        // how can I get the color of player 1?

        Color color = graphicInterface.getColor(listOfColors);

        return color;
    }

    @Override
    public Coordinate deleteTile(Player aPlayer) {
        // player not needed if:
        // maybe do this with new GUI in the GameLogic/Grid class
        return null;
    }

    @Override
    public Coordinate setTile(Player aPlayer) {
        // player not needed if:
        // maybe do this aswell in GameLogic/Grid class (otherwise UI has to go back and forth)
        return null;
    }

    @Override
    public Coordinate deleteTile() {
        // yields a coordinate
        Coordinate selectedTile = graphicInterface.selectTile();




        return selectedTile;
    }

    @Override
    public Coordinate setTile() {
        // ask user to press on tile and make input validation
        // yields a coordinate
        Coordinate selectedTile = graphicInterface.selectTile();

        return selectedTile;
    }

    // Does only open a new window which states winner and closes stage on "Enter"
    @Override
    public void declareWinner(Player aPlayer) {
        String winnerName = aPlayer.getName();
        graphicInterface.popUpWinner(winnerName);
    }


    @Override
    public void UpdateGrid(Grid aGrid){
        // update grid here in GUI
        graphicInterface.refreshGrid(aGrid);
    }
}
