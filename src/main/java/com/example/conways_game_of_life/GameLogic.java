package com.example.conways_game_of_life;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameLogic {

    private final InterfaceUI ui;
    private Grid aGrid;

    private final List<Observer> observers;

    private final List<Player> players;

    public GameLogic(){
        this.observers = new ArrayList<>();
        this.players = new ArrayList<>();
        this.ui = new UserInteraction(this, players);
    }

    //setup game (player + grid)
    public void gameSetup(){
        //set up player
        players.add(new Player(ui.setPlayerName(1), ui.setPlayerColor(1)));
        players.add(new Player(ui.setPlayerName(2), ui.setPlayerColor(2)));
        players.sort(Comparator.comparing(Player::getName));

        //setup grid
        this.aGrid = new Grid(players.get(0).getPlayerColor(), players.get(1).getPlayerColor(), ui);
        notifyObserver();
        ui.displayEndTurnInfo();

    }

    //play game
    public void playGame(){
        // if one player has no active tiles, then the game ends
        while(allPlayerHaveTiles()){
            for(Player aPlayer : players){

                //delete a tile
                aGrid.setKill(ui.deleteTile(aPlayer), aPlayer);
                notifyObserver();

                //set a tile
                aGrid.setTile(ui.setTile(aPlayer), aPlayer);
                notifyObserver();

                //go a step forward (next cell)
                aGrid.makeGenerationStep();
                notifyObserver();
                ui.displayEndTurnInfo();

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
    //observer can register itself with this function
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
        //assert !allPlayerHaveTiles(); // 4 cases: 1. both have tiles

        if(!aGrid.hasTiles(players.get(0).getPlayerColor()) && !aGrid.hasTiles(players.get(1).getPlayerColor())){
            ui.declareWinner(new Player("No One", Color.WHITE)); // 2. case both have no tiles: no one wins
        }
        else if(aGrid.hasTiles(players.get(0).getPlayerColor())) {
            ui.declareWinner(players.get(0)); //3. case: Player 0 wins
        }
        else{// if(aGrid.hasTiles(players.get(1).getPlayerColor())) {
            ui.declareWinner(players.get(1)); //4. case Player 1 wins
        }
    }
}
