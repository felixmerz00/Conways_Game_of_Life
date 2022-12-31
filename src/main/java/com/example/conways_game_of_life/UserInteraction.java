package com.example.conways_game_of_life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class UserInteraction implements InterfaceUI, Observer{

    Grid aGrid;
    int chosenColorPlayerOne = -1;   // Stores the color of the first player, so the second player cannot choose the same one.

    List<Player> playerList;
    public UserInteraction(GameLogic aGameLogic, List<Player> playerList) {
        aGameLogic.registerObserver(this);
        this.playerList = playerList;
    }

    /**
     * @pre The input parameter int player indicates which player is setting his name.
     * Because this game has two players the int player must equal one or two. */
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

    /**
     * @pre The input parameter int player indicates which player is setting his color.
     * Because this game has two players the int player must equal one or two. */
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
                // Check if the user chose a valid color.
                if (userSelection >= 0 && userSelection < validInputs.size() && userSelection != chosenColorPlayerOne) {
                    validInput = true;
                    if(chosenColorPlayerOne == -1){
                        chosenColorPlayerOne = userSelection;
                    }
                } else {    // Display error message if the player chose an invalid color.
                    if(chosenColorPlayerOne != -1){ // For choosing a wrong color the two players get different error messages.
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
    }

    @Override
    public Coordinate deleteTile(Player aPlayer) {
        Scanner aScanner = new Scanner(System.in);  // Create a Scanner object

        System.out.println(aPlayer.getName() + ": Which enemy tile (" + aPlayer.getPlayerColor().toString() + ") would you like to kill?");

        int userInputX = getInputForCoordinate(aScanner, "X");  // ask user for X-Coordinate
        int userInputY = getInputForCoordinate(aScanner, "Y");  // ask user for Y-Coordinate

        System.out.println("You chose the coordinate (" + userInputX + "," + userInputY + ")");

        return new Coordinate(userInputX, userInputY);  // Create and return Coordinate object.
    }

    @Override
    public Coordinate setTile(Player aPlayer) {
        Scanner aScanner = new Scanner(System.in);  // Create a Scanner object

        System.out.println(aPlayer.getName() + ": Which dead tile would you like to occupy?");
        int userInputX = getInputForCoordinate(aScanner, "X");  // ask user for X-Coordinate
        int userInputY = getInputForCoordinate(aScanner, "Y");  // ask user for Y-Coordinate
        System.out.println("You chose the coordinate (" + userInputX + "," + userInputY + ")");

        return new Coordinate(userInputX, userInputY);  // Create and return Coordinate object.
    }

    // Ask user for and int between 1 and 16 and return it.
    private int getInputForCoordinate(Scanner aScanner, String coordinate) {
        System.out.println("Please enter the " + coordinate + "-Coordinate of the tile. This should be an integer between 1 and 16.");

        int userInput = -1;
        while (userInput == -1) {
            try {
                userInput = Integer.parseInt(aScanner.nextLine());  // Read user input
                // If the input is not within bounds, we must ask for a new coordinate (iterate again).
                if (userInput < 1 || userInput > 16) {
                    System.out.println(coordinate + "-Coordinate must be between 1 and 16.");
                    userInput = -1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter only an integer between 1 and 16.");
            }
        }
        return userInput;
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
    }
    @Override
    public void displayEndTurnInfo(){
        System.out.println(aGrid);
        int counterPlayer1 = 0;
        int counterPlayer2 = 0;
        Color playerOneColor = playerList.get(0).getPlayerColor();
        Color playerTwoColor = playerList.get(1).getPlayerColor();

        for(Tile t: aGrid){
            if(t.getColor() == playerOneColor){
                counterPlayer1++;
            } else if (t.getColor() == playerTwoColor) {
                counterPlayer2++;
            }
        }
        System.out.println("Number of alive tiles " + playerList.get(0).getName() + ": " + counterPlayer1);
        System.out.println("Number of alive tiles " + playerList.get(1).getName() + ": " + counterPlayer2);
        System.out.println("Number of generations: " + aGrid.getNumberOfGenerations());
        System.out.println("--------------------------------------------------------");
    }
}
