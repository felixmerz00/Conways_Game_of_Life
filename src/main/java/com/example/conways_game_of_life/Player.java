package com.example.conways_game_of_life;

public class Player {

    private final String name;
    private final Color color;

    public Player(String name, String color){
        this.name = name;
        this.color = Color.valueOf(color);
    }

    public String getName() {
        return name;
    }

    public Color getPlayerColor() {
        return color;
    }
}
