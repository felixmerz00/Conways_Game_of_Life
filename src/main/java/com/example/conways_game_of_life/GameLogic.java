package com.example.conways_game_of_life;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GameLogic {

    private final InterfaceUI ui;
    private final Grid aGrid;

    private final List<Observer> observers;

    private final List<Player> players = new ArrayList<>();

    public GameLogic(){
        this.observers = new ArrayList<>();
        //this.ui = new UI(this);
        this.aGrid = new Grid();
    }

    //setup game
    public void gameSetup(){
        players.add(new Player(ui.setPlayerName(), ui.setPlayerColor()));
        players.add(new Player(ui.setPlayerName(), ui.setPlayerColor()));
        players.sort(Comparator.comparing(Player::getName));
        aGrid.initState();
    }

    //play game
    public void playGame(){
        // one player has none active tile
        while(allPlayerHaveTiles()){
            for(Player aPlayer : players){
                notifyOb();

                //delete a tile
                aGrid.delete(ui.deleteTile(aPlayer));

                //set a tile
                aGrid.set(ui.setTile(aPlayer));

                //go a step forward (next cell)
                aGrid.nextGen(aPlayer);
            }
        }

        //one player has no active tile -->end
        //determine winner

    }
    public boolean allPlayerHaveTiles(){
        for(Player aPlayer: players){
            if(!aGrid.hasTiles(aPlayer)){
                return false;
            }
        }
        return true;
    }

    public void registerOb(Observer aObserver){
        this.observers.add(aObserver);
    }

    public void notifyOb(){
        for(Observer aObserver: observers){
            aObserver.updateGrid();
        }
    }
}
