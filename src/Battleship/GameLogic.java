package Battleship;

import java.util.Random;

public class GameLogic {

    private int[][] opponentTiles;
    private int[][] original_opponentTiles;
    private int[][] playerTiles;
    private int size = 4;
    private int opponentShip = 3;  // Number of ships to place
    private int playerShip = 3;

    private Random random = new Random();

    // Initialize empty tiles
    private void initTiles() {
        opponentTiles = new int[size][size];
        original_opponentTiles = new int[size][size];
        playerTiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                opponentTiles[i][j] = 0;
                playerTiles[i][j] = 0;
                original_opponentTiles[i][j] = 0;
            }
        }
    }

    // Print the board tiles
    private void printTiles(){
        System.out.println("\nPlayer Tiles: ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(playerTiles[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Opponent Tiles: ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(opponentTiles[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Place a ship that takes up 2 tiles
    private void placeShip(int shipNumber) {
        boolean placed = false;

        while (!placed) {
            // Randomly choose if ship will be horizontal or vertical
            boolean isHorizontal = random.nextBoolean();

            int row, col;

            if (isHorizontal) {
                // Choose a random row and column that allows a horizontal ship
                row = random.nextInt(size);
                col = random.nextInt(size - 1); // Ensure there's space for 2 tiles

                // Check if the space is empty and place the ship
                if (opponentTiles[row][col] == 0 && opponentTiles[row][col + 1] == 0) {
                    opponentTiles[row][col] = shipNumber;
                    opponentTiles[row][col + 1] = shipNumber;

                    original_opponentTiles[row][col] = shipNumber;
                    original_opponentTiles[row][col + 1] = shipNumber;

                    placed = true;
                }
            } else {
                // Choose a random row and column that allows a vertical ship
                row = random.nextInt(size - 1); // Ensure there's space for 2 tiles
                col = random.nextInt(size);

                // Check if the space is empty and place the ship
                if (opponentTiles[row][col] == 0 && opponentTiles[row + 1][col] == 0) {
                    opponentTiles[row][col] = shipNumber;
                    opponentTiles[row + 1][col] = shipNumber;

                    original_opponentTiles[row][col] = shipNumber;
                    original_opponentTiles[row + 1][col] = shipNumber;

                    placed = true;
                }
            }
        }
    }

    public GameLogic() {
        initTiles();

        // Place the opponent ships on the board
        for (int i = 1; i <= opponentShip; i++) {
            placeShip(i);  // Place each ship numbered 1, 2, 3
        }

        printTiles();
    }

    public int[][] getOpponentTiles() {
        return original_opponentTiles;
    }

    // Handle a shot and return the result (hit or miss)
    public String handleShot(int row, int col) {
        if (opponentTiles[row][col] != 0) {
            int shipNumber = opponentTiles[row][col];
            opponentTiles[row][col] = -1; // Mark as hit (using -1 as hit marker)

            // Check if the entire ship has been sunk
            if (isShipSunk(shipNumber)) {
                opponentShip--;  // Reduce the number of remaining ships
                return "Ship Sunk!";
            }
            return "Hit!";
        } else {
            return "Miss!";
        }
    }

    // Check if a ship is completely sunk
    private boolean isShipSunk(int shipNumber) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (opponentTiles[i][j] == shipNumber) {
                    return false; // Ship is not yet sunk
                }
            }
        }
        return true; // Ship is completely sunk
    }

    // Get the number of remaining ships
    public int getRemainingShips() {
        return opponentShip;
    }

}