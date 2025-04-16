package Battleship;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
        TilesBackgroundPanel opponentPanel = new TilesBackgroundPanel("res/Game/TilesBackground.jpg");
        opponentPanel.setLayout(new GridLayout(SIZE, SIZE));
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
                        ImageIcon icon = new ImageIcon("res/Game/Ship/" + "hit.png");
                        opponentButtons[row][col].setIcon(icon);
                    } else if (result.equals("Miss!")) {
                        ImageIcon icon = new ImageIcon("res/Game/Ship/" + "miss.png");
                        opponentButtons[row][col].setIcon(icon);
                    } else if (result.equals("Ship Sunk!")) {
                        ImageIcon icon = new ImageIcon("res/Game/Ship/" + "hit.png");
                        opponentButtons[row][col].setIcon(icon);
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
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JButton button = opponentButtons[i][j];
                ImageIcon icon = new ImageIcon("res/Game/Ship/" + "transparent.png");

                button.setIcon(icon);
                button.setOpaque(false);
                button.setContentAreaFilled(false);
                button.setBorderPainted(true);

// Apply a visible border to all buttons
                button.setBorder(new LineBorder(Color.BLACK, 1));
                button.setMargin(new Insets(0, 0, 0, 0));
                button.setFocusable(false);
            }
        }
    }


    private String getShipImageName(int[][] grid, int i, int j) {
        int val = Math.abs(grid[i][j]);

        // Check for horizontal ship first
        if (j > 0 && Math.abs(grid[i][j - 1]) == val) {
            return "opp_ship_right.png"; // This tile is the right end
        }
        if (j < SIZE - 1 && Math.abs(grid[i][j + 1]) == val) {
            return "opp_ship_left.png"; // This tile is the left end
        }

        // Check for vertical ship
        if (i > 0 && Math.abs(grid[i - 1][j]) == val) {
            return "opp_ship_bottom.png"; // This tile is the bottom end
        }
        if (i < SIZE - 1 && Math.abs(grid[i + 1][j]) == val) {
            return "opp_ship_top.png"; // This tile is the top end
        }

        return null; // shouldn't happen
    }

    // After updating the opponent grid or handling a shot in the gamePanel class
    private void updateRemainingShips() {
        int remainingShips = gameLogic.getRemainingShips();
        System.out.println("Remaining opponent ships: " + remainingShips);

        // Check if the game is over
        if (remainingShips == 0) {
            JOptionPane.showMessageDialog(this, "Game Over! You Win!");
            // You can disable further interaction or close the game
            int[][] opponentTiles = gameLogic.getOpponentTiles();


            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    JButton button = opponentButtons[i][j];
                    int tile = opponentTiles[i][j];

                    if (tile != 0) {
                        // Use different images for part 1 and part 2 of the ship
                        String imageName = getShipImageName(opponentTiles, i, j);
                        ImageIcon icon = new ImageIcon("res/Game/Ship/" + imageName);

                        button.setIcon(icon);
                        button.setOpaque(false);
                    }
                }
            }

        }
    }


}