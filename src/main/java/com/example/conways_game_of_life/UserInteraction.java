package com.example.conways_game_of_life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInteraction implements InterfaceUI, Observer{

    Grid aGrid;
    int chosenColorPlayerOne = -1;   // Stores the color of the first player, so the second player cannot choose the same one.
    public UserInteraction(GameLogic aGameLogic) {
        aGameLogic.registerObserver(this);
    }

    @Override
    public String setPlayerName(int player) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("\n-----------------------------------------");
        System.out.println("Enter username for player " + player + ": ");
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
        System.out.println("Username of player "+ player +" is: " + playerName +"\n");  // Output user input
        return playerName;
    }


    // Aks user to choose a color and return it.
    @Override
    public Color setPlayerColor(int player) {

        Scanner aScanner = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Choose a color for player " + player + " (Enter letter in brackets): ");
        System.out.println("Yellow (0), Orange (1), Red (2), Magenta (3), Purple (4), Violet (5), Blue (6), Teal (7), Green (8)");

        ArrayList<String> validInputs = new ArrayList<>(Arrays.asList("YELLOW", "ORANGE", "RED", "MAGENTA", "PURPLE", "VIOLET", "BLUE", "TEAL", "GREEN"));
        int userSelection = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                userSelection = Integer.parseInt(aScanner.nextLine());  // Read user input
                if (userSelection >= 0 && userSelection < validInputs.size() && userSelection != chosenColorPlayerOne) {
                    validInput = true;
                    if(chosenColorPlayerOne == -1){
                        chosenColorPlayerOne = userSelection;
                    }
                } else {
                    if(chosenColorPlayerOne != -1){
                        System.out.println("Enter one of the provided numbers which is different from the first players color.");
                    }else{
                        System.out.println("Enter one of the provided numbers.");
                    }
                    System.out.println("Yellow (0), Orange (1), Red (2), Magenta (3), Purple (4), Violet (5), Blue (6), Teal (7), Green (8)");
                }
            }catch(NumberFormatException e){
                System.out.println("Yellow (0), Orange (1), Red (2), Magenta (3), Purple (4), Violet (5), Blue (6), Teal (7), Green (8)");
            }
        }

        System.out.println("Color of player " + player + " is " + validInputs.get(userSelection).toLowerCase());
        return Color.valueOf(validInputs.get(userSelection));
        // TODO Make sure that not both players can choose the same color.
    }

    @Override
    public Coordinate deleteTile(Player aPlayer) {
        Scanner aScanner = new Scanner(System.in);  // Create a Scanner object

        // ask for X-Coordinate
        System.out.println("-----------------------------------------");
        System.out.println("\n" + aPlayer.getName() + ":");
        System.out.println("Please enter X-Coordinate of enemy tile which you would like to kill.\n" +
                "Enter an integer below (between 1 and 16):");
        int userinputX = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                userinputX = Integer.parseInt(aScanner.nextLine());  // Read user input
                if (userinputX > 0 && userinputX < 17) {
                    validInput = true;
                } else {
                    System.out.println("X-Coordinate must be between 1 and 16.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter only an integer between 1 and 16.");
            }
        }
        System.out.println("Your chosen X-Coordinate is " + userinputX);

        // aks for Y-Coordinate
        System.out.println("Please enter Y-Coordinate of enemy tile which you would like to kill.\n" +
                "Enter an integer below (between 1 and 16):");
        int userinputY = 0;
        validInput = false;
        while (!validInput) {
            try {
                userinputY = Integer.parseInt(aScanner.nextLine());  // Read user input
                if (userinputY > 0 && userinputY < 17) {
                    validInput = true;
                } else {
                    System.out.println("Y-Coordinate must be between 1 and 16.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter only an integer between 1 and 16.");
            }
        }
        System.out.println("Your chosen Coordinate is (" + userinputX + "," + userinputY + ")");



        // make Coordinate from user inputs
        return new Coordinate(userinputX, userinputY);
    }

    @Override
    public Coordinate setTile(Player aPlayer) {
        Scanner aScanner = new Scanner(System.in);  // Create a Scanner object

        // ask for X-Coordinate
        System.out.println("\n" + aPlayer.getName() + ":");
        System.out.println("Please enter X-Coordinate of dead Tile which you would like to occupy.\n" +
                "Enter an integer below (between 1 and 16):");
        int userinputX = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                userinputX = Integer.parseInt(aScanner.nextLine());  // Read user input
                if (userinputX > 0 && userinputX < 17) {
                    validInput = true;
                } else {
                    System.out.println("X-Coordinate must be between 1 and 16.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter only an integer between 1 and 16.");
            }
        }
        System.out.println("Your chosen X-Coordinate is " + userinputX);

        // aks for Y-Coordinate
        System.out.println("Please enter Y-Coordinate of dead Tile which you would like to occupy.\n" +
                "Enter an integer below (between 1 and 16):");
        int userinputY = 0;
        validInput = false;
        while (!validInput) {
            try {
                userinputY = Integer.parseInt(aScanner.nextLine());  // Read user input
                if (userinputY > 0 && userinputY < 17) {
                    validInput = true;
                } else {
                    System.out.println("Y-Coordinate must be between 1 and 16.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter only an integer between 1 and 16.");
            }
        }
        System.out.println("Your chosen Coordinate is (" + userinputX + "," + userinputY + ")");

        // make Coordinate from user inputs
        return new Coordinate(userinputX, userinputY);
    }

    // Does only open a new window which states winner and closes stage on "Enter"
    @Override
    public void declareWinner(Player aPlayer) {
        String winnerName = aPlayer.getName();
        System.out.println("The winner is " + winnerName);

    }

    @Override
    public void updateGrid(Grid aGrid){
        this.aGrid = aGrid;
        System.out.println(aGrid);
    }
}
