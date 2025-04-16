package Battleship;

import javax.swing.*;
import java.util.Random;

import static Battleship.gamePanel.*;

public class GameLogic {

    private static final int opp_default_amt = 4;
    private static final int pla_default_amt = 4;
    public static int opponentShip = opp_default_amt, playerShip = pla_default_amt;  // Number of ships to place
    SoundEffect sound = new SoundEffect();
    private int[][] opponentTiles;
    private int[][] original_opponentTiles;
    private int[][] playerTiles;
    private final int size = SIZE;
    private final Random random = new Random();

    public GameLogic() {
        initTiles();

        opponentShip = opp_default_amt;
        playerShip = pla_default_amt;

        // Place the opponent ships on the board
        for (int i = 1; i <= opponentShip; i++) {
            placeShip(i, 0);  // Place each ship numbered 1, 2, 3
        }

        for (int i = 1; i <= playerShip; i++) {
            placeShip(i, 1);  // Place each ship numbered 1, 2, 3
        }

        printTiles();
    }


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
    private void printTiles() {

        System.out.println("Opponent Tiles: ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(opponentTiles[i][j] + " ");
            }
            System.out.println();
        }


        System.out.println("\nPlayer Tiles: ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(playerTiles[i][j] + " ");
            }
            System.out.println();
        }


    }

    // Place a ship that takes up 2 tiles
    private void placeShip(int shipNumber, int side) {
        boolean placed = false;

        while (!placed) {
            // Randomly choose if ship will be horizontal or vertical
            boolean isHorizontal = random.nextBoolean();

            int row, col;


            if (side == 0) {
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
            } else {
                if (isHorizontal) {
                    // Choose a random row and column that allows a horizontal ship
                    row = random.nextInt(size);
                    col = random.nextInt(size - 1); // Ensure there's space for 2 tiles

                    // Check if the space is empty and place the ship
                    if (playerTiles[row][col] == 0 && playerTiles[row][col + 1] == 0) {
                        playerTiles[row][col] = shipNumber;
                        playerTiles[row][col + 1] = shipNumber;

                        placed = true;
                    }
                } else {
                    // Choose a random row and column that allows a vertical ship
                    row = random.nextInt(size - 1); // Ensure there's space for 2 tiles
                    col = random.nextInt(size);

                    // Check if the space is empty and place the ship
                    if (playerTiles[row][col] == 0 && playerTiles[row + 1][col] == 0) {
                        playerTiles[row][col] = shipNumber;
                        playerTiles[row + 1][col] = shipNumber;

                        placed = true;
                    }
                }
            }


        }
    }

    public int[][] getOpponentTiles(int mode) {
        if (mode == 0) {
            return original_opponentTiles;
        } else if (mode == 1) {
            return opponentTiles;
        }
        return null;
    }

    public int[][] getPlayerTiles() {
        return playerTiles;
    }

    // Handle a shot and return the result (hit or miss)
    public String handleShot(int row, int col, int side) {

        if (side == 0) {
            if (opponentTiles[row][col] != 0 && opponentTiles[row][col] != -2) {
                int shipNumber = opponentTiles[row][col];
                opponentTiles[row][col] = -1; // Mark as hit (using -1 as hit marker)

                // Check if the entire ship has been sunk
                if (isShipSunk(shipNumber, side)) {
                    opponentShip--;  // Reduce the number of remaining ships
                    GameStatus.lblOpponentShip.setText("Opponent Ship Remaining: " + opponentShip);
                    GameStatus.lblShot.setText("Last Shot: Hit!");
                    sound.hit();
                    return "Ship Sunk!";
                }
                GameStatus.lblShot.setText("Last Shot: Hit!");
                sound.hit();
                return "Hit!";
            } else {
                GameStatus.lblShot.setText("Last Shot: Miss!");
                sound.miss();
                opponentTiles[row][col] = -2; // Mark miss
                return "Miss!";
            }
        } else {
            if (playerTiles[row][col] != 0 && playerTiles[row][col] != -2) {
                int shipNumber = playerTiles[row][col];
                playerTiles[row][col] = -1; // Mark as hit (using -1 as hit marker)

                // Check if the entire ship has been sunk
                if (isShipSunk(shipNumber, side)) {
                    playerShip--;  // Reduce the number of remaining ships
                    sound.hit();
                    return "Ship Sunk!";
                }
                sound.hit();
                return "Hit!";
            } else {
                sound.miss();
                playerTiles[row][col] = -2;
                return "Miss!";
            }
        }


    }

    // Check if a ship is completely sunk
    private boolean isShipSunk(int shipNumber, int side) {

        if (side == 0) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (opponentTiles[i][j] == shipNumber) {
                        return false; // Ship is not yet sunk
                    }
                }
            }
            return true; // Ship is completely sunk
        } else {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (playerTiles[i][j] == shipNumber) {
                        return false; // Ship is not yet sunk
                    }
                }
            }
            return true; // Ship is completely sunk
        }


    }

    // Get the number of remaining ships
    public int getRemainingShips(int side) {
        if (side == 0) {
            return opponentShip;
        } else {
            return playerShip;
        }

    }


    // Computer chooses a random tile to attack on the player's board
    public void getComputerMove() {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                opponentButtons[i][j].setEnabled(false);
            }
        }

        new Timer(100, f -> {
            int row, col;
            do {
                row = random.nextInt(size);
                col = random.nextInt(size);
                // Skip if already tried (hit or miss)
            } while (playerTiles[row][col] == -1 || playerTiles[row][col] == -2);

            String result = handleShot(row, col, 1);
            if (result.equals("Hit!")) {
                ImageIcon icon = new ImageIcon("res/Game/Ship/" + "hit.png");
                playerButtons[row][col].setIcon(icon);


            } else if (result.equals("Miss!")) {
                ImageIcon icon = new ImageIcon("res/Game/Ship/" + "miss.png");
                playerButtons[row][col].setIcon(icon);


            } else if (result.equals("Ship Sunk!")) {
                ImageIcon icon = new ImageIcon("res/Game/Ship/" + "hit.png");
                playerButtons[row][col].setIcon(icon);
                //JOptionPane.showMessageDialog(null, "A ship has been sunk!");


            }
            ((Timer) f.getSource()).stop(); // Stop the timer
        }).start();



        new Timer(200, e -> {
            // Re-enable player input
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    opponentButtons[i][j].setEnabled(true);
                }
            }
            ((Timer) e.getSource()).stop(); // Stop the timer
        }).start();






    }

}