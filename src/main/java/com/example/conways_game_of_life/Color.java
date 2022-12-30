package com.example.conways_game_of_life;

public enum Color {
    // The following colors correspond to existing colors from the javafx.scene.paint.Class class.
    // We use the color WHITE for dead tiles.
    WHITE, YELLOW, ORANGE, RED, MAGENTA, PURPLE, VIOLET, BLUE, TEAL, GREEN;

    @Override
    public String toString() {
        if(this.ordinal() == 0){
            return " ";
        }
        return Character.toString(this.name().charAt(0));
    }
}
