package com.example.conways_game_of_life;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameLogic {

    private final InterfaceUI ui;
    private Grid aGrid;

    private final List<Observer> observers;

    private final List<Player> players = new ArrayList<>();

    public GameLogic(){
        this.observers = new ArrayList<>();
        this.ui = new ExampleUI(this);
    }

    //setup game (player + grid)
    public void gameSetup(){
        //set up player
        players.add(new Player(ui.setPlayerName(), ui.setPlayerColor()));
        players.add(new Player(ui.setPlayerName(), ui.setPlayerColor()));
        players.sort(Comparator.comparing(Player::getName));

        //setup grid
        this.aGrid = new Grid(players.get(0).getPlayerColor(), players.get(1).getPlayerColor(), ui);
        notifyObserver();

    }

    //play game
    public void playGame(){
        // if one player has no active tiles, then the game ends
        while(allPlayerHaveTiles()){
            for(Player aPlayer : players){

                //delete a tile
                aGrid.kill(ui.deleteTile(aPlayer));
                notifyObserver();

                //set a tile
                aGrid.playerSetTile(ui.setTile(aPlayer), aPlayer);
                notifyObserver();

                //go a step forward (next cell)
                aGrid.makeGenerationStep();
                notifyObserver();

                if(!allPlayerHaveTiles()){
                    break;
                }
            }
        }
    }

    public static void main(String[] args){
        GameLogic game = new GameLogic();
        game.gameSetup();
        game.playGame();
        game.getWinner();
    }

    //end condition to the while loop in playGame
    private boolean allPlayerHaveTiles(){
        for(Player aPlayer: players){
            if(!aGrid.hasTiles(aPlayer.getPlayerColor())){
                return false;
            }
        }
        return true;
    }
    //observer can register themself with this function
    public void registerObserver(Observer aObserver){
        this.observers.add(aObserver);
    }

    //used as callback method in observer pattern
    private void notifyObserver(){
        for(Observer aObserver: observers){
            aObserver.updateGrid(aGrid);
        }
    }

    //utility function to let the ui declare the winner and pass the correct player as winner
    //@PRE: at least one player has no Tiles
    public void getWinner(){
        assert !allPlayerHaveTiles();
        if(!aGrid.hasTiles(players.get(0).getPlayerColor()) && !aGrid.hasTiles(players.get(1).getPlayerColor())){
            ui.declareWinner(new Player("No One", Color.WHITE)); // no one wins
        }
        else if(aGrid.hasTiles(players.get(0).getPlayerColor()) && !aGrid.hasTiles(players.get(1).getPlayerColor())) {
            ui.declareWinner(players.get(0)); //Player 0 wins
        }
        else{// if(!aGrid.hasTiles(players.get(0).getPlayerColor()) && aGrid.hasTiles(players.get(1).getPlayerColor())) {
            ui.declareWinner(players.get(1)); //Player 1 wins
        }
    }
}
