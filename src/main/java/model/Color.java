package model;

public enum Color {
    // The following colors correspond to existing colors from the javafx.scene.paint.Class.
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
