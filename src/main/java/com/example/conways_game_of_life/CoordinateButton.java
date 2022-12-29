package com.example.conways_game_of_life;

import javafx.scene.control.Button;

public class CoordinateButton extends Button {
    private final int x;
    private final int y;

    public CoordinateButton(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }
}
