package com.example.conways_game_of_life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class UserInteraction implements InterfaceUI, Observer{

    Grid aGrid;
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

    @Override
    public Color setPlayerColor(int player) {

        // aks user about color and return
        Scanner aScanner = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Choose a color for player " + player + " (Enter letter in brackets): ");
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

        System.out.println("Color of player " + player + " is " + Color.valueOf(validInputs.get(userSelection)).toString().toLowerCase());
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
        // System.out.println(aGrid);

        // print out new grid
        System.out.println("================ NEW GENERATION ================");
        System.out.println(" 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 ");
        System.out.println("+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+ ");

        int j = 0;
        int i = 0;
        for(Tile t: aGrid){
            if(i == 16){
                System.out.print(j+1);
                System.out.print("\n");
                i = 0;
                j++;
            }
            if(i == 0){
                System.out.print(j+1);
            }
            System.out.print('|');
            Character uppercaseLetter = t.getColor().toString().charAt(0);
            System.out.print(uppercaseLetter);
            System.out.print("|");
            i++;
        }

        System.out.println("\n+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+--+ ");
        System.out.println(" 01 02 03 04 05 06 07 08 09 10 11 12 13 14 15 16 ");
        System.out.print("=================================================");
        System.out.print("\n\n--------------------------------------------------\n\n");
    }
}
