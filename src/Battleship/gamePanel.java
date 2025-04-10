package Battleship;

import javax.swing.*;
import java.awt.*;

public class gamePanel extends JPanel {

    private static final int SIZE = 4; // Size of the grid (4x4 for this example)
    private JButton[][] opponentButtons;
    private GameLogic gameLogic = new GameLogic(); // GameLogic instance to fetch the grid data

    public gamePanel() {
        setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();

        // Create the opponent's grid panel
        JPanel opponentPanel = new JPanel(new GridLayout(SIZE, SIZE));
        opponentButtons = new JButton[SIZE][SIZE];

        // Add buttons to the opponent grid
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                opponentButtons[i][j] = new JButton();
                opponentButtons[i][j].setBackground(Color.BLUE); // Blue for empty tiles
                final int row = i;
                final int col = j;

                // Using a lambda expression for the action listener
                opponentButtons[i][j].addActionListener(e -> {
                    // Handle shot and update the UI
                    String result = gameLogic.handleShot(row, col);

                    if (result.equals("Hit!")) {
                        opponentButtons[row][col].setBackground(Color.RED); // Mark as hit
                        opponentButtons[row][col].setText("H"); // Show 'H' for hit
                    } else if (result.equals("Miss!")) {
                        opponentButtons[row][col].setBackground(Color.WHITE); // Mark as miss
                        opponentButtons[row][col].setText("M"); // Show 'M' for miss
                    } else if (result.equals("Ship Sunk!")) {
                        opponentButtons[row][col].setBackground(Color.RED); // Mark as hit
                        opponentButtons[row][col].setText("H"); // Show 'H' for hit
                        JOptionPane.showMessageDialog(null, "A ship has been sunk!");
                    }

                    // Update the number of remaining ships
                    updateRemainingShips();
                });

                opponentPanel.add(opponentButtons[i][j]);
            }
        }

        // Add opponent panel to grid layout at position (0,0)
        c.gridx = 0;
        c.gridy = 0;
        add(opponentPanel, c);

        // Update the grid with the ship placement from GameLogic
        updateOpponentGrid();
    }


    // Update the opponent's grid to reflect the ships placed
    private void updateOpponentGrid() {
        int[][] opponentTiles = gameLogic.getOpponentTiles(); // Get the tiles from GameLogic

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (opponentTiles[i][j] != 0) {
                    // Place the ship number on the button
                    opponentButtons[i][j].setText(String.valueOf(opponentTiles[i][j]));
                    opponentButtons[i][j].setBackground(Color.GRAY); // Gray for ship tiles
                }
            }
        }
    }

    // After updating the opponent grid or handling a shot in the gamePanel class
    private void updateRemainingShips() {
        int remainingShips = gameLogic.getRemainingShips();
        System.out.println("Remaining opponent ships: " + remainingShips);

        // Check if the game is over
        if (remainingShips == 0) {
            JOptionPane.showMessageDialog(this, "Game Over! You Win!");
            // You can disable further interaction or close the game
        }
    }


}