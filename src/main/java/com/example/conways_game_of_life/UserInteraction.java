package com.example.conways_game_of_life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInteraction implements InterfaceUI {

    public UserInteraction() {

    }

    @Override
    public String setPlayerName(int player) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter username for player " + player+1 + ": ");
        String playerName = null;
        boolean validInput = false;
        while (!validInput) {
            playerName = myObj.nextLine();  // Read user input
            if (playerName.length() > 0){ //minimum one character long
                validInput = true;
            }
            else {
                System.out.println("Name must be at least on character long, try again: ");
            }
        }
        System.out.println("Username of player "+ player+1 +" is: " + playerName +"\n");  // Output user input
        return playerName;


        // check if name is valid
        // can be done much easier in GUI
    }

    @Override
    public Color setPlayerColor(int player) {

        // aks user about color and return
        Scanner aScanner = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Choose a color (Enter letter in brackets): ");
        System.out.println("Yellow (0), Orange (1), Red (2), Magenta (3), Purple (4), Violet (5), Blue (6), Teal (7), Green (8)");

        ArrayList<String> validInputs = new ArrayList<>(Arrays.asList("YELLOW", "ORANGE", "RED", "MAGENTA", "PURPLE", "VIOLET", "BLUE", "TEAL", "GREEN"));
        int userSelection = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                userSelection = Integer.parseInt(aScanner.nextLine());  // Read user input
                if (userSelection >= 0 && userSelection < validInputs.size()) {
                    validInput = true;
                } else {
                    System.out.println("Enter one of the provided numbers.");
                    System.out.println("Yellow (0), Orange (1), Red (2), Magenta (3), Purple (4), Violet (5), Blue (6), Teal (7), Green (8)");
                }
            }catch(NumberFormatException e){
                System.out.println("Yellow (0), Orange (1), Red (2), Magenta (3), Purple (4), Violet (5), Blue (6), Teal (7), Green (8)");
            }
        }
        return Color.valueOf(validInputs.get(userSelection));
        // TODO Make sure that not both players can choose the same color.
    }

    @Override
    public Coordinate deleteTile(Player aPlayer) {
        // player not needed if:
        // maybe do this with new GUI in the GameLogic/Grid class
        return null;
    }

    @Override
    public Coordinate setTile(Player aPlayer) {
        //
        return null;
    }

    // Does only open a new window which states winner and closes stage on "Enter"
    @Override
    public void declareWinner(Player aPlayer) {
        String winnerName = aPlayer.getName();
        System.out.println("The winner is " + winnerName);

    }


    @Override
    public void UpdateGrid(Grid aGrid){
        // print out new grid



    }
}
