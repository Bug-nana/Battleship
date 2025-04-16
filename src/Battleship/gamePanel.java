package Battleship;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static Battleship.Main.bgm;

public class gamePanel extends JPanel {

    public static final int SIZE = 6; // Size of the grid (4x4 for this example)
    private final JButton[][] opponentButtons = new JButton[SIZE][SIZE];
    private final JButton[][] playerButtons = new JButton[SIZE][SIZE];
    private final JPanel opponentPanel;
    private final JPanel playerPanel;
    private final JPanel statusPanel;


    private final GameLogic gameLogic = new GameLogic(); // GameLogic instance to fetch the grid data

    public gamePanel() {
        setLayout(new GridBagLayout());
        setOpaque(false);

        GridBagConstraints c = new GridBagConstraints();

        opponentPanel = createTiles(opponentButtons, 0);
        playerPanel = createTiles(playerButtons, 1);
        statusPanel = new GameStatus();

        // Add opponent panel to grid layout at position (0,0)
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(0, 0, 10, 30);
        add(opponentPanel, c);

        // Update the grid with the ship placement from GameLogic
        updateOpponentGrid();

        c.insets = new Insets(10, 0, 0, 30);

        c.gridy = 1;
        add(playerPanel, c);

        updatePlayerGrid();


        c.gridx = 1;
        c.gridy = 0;
        add(statusPanel, c);


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

    private void updatePlayerGrid() {
        int[][] playerTiles = gameLogic.getPlayerTiles();


        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                JButton button = playerButtons[i][j];
                int tile = playerTiles[i][j];

                if (tile != 0) {
                    // Use different images for part 1 and part 2 of the ship
                    String imageName = getShipImageName(playerTiles, i, j, 1);
                    ImageIcon icon = new ImageIcon("res/Game/Ship/" + imageName);

                    button.setIcon(icon);
                } else {
                    // Empty tile: transparent
                    button.setText("");
                    button.setIcon(null);
                }
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

    private String getShipImageName(int[][] grid, int i, int j, int mode) {
        int val = Math.abs(grid[i][j]);

        if (mode == 0) {// Check for horizontal ship first
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
        } else if (mode == 1) {
            if (j > 0 && Math.abs(grid[i][j - 1]) == val) {
                return "pla_ship_right.png"; // This tile is the right end
            }
            if (j < SIZE - 1 && Math.abs(grid[i][j + 1]) == val) {
                return "pla_ship_left.png"; // This tile is the left end
            }

            // Check for vertical ship
            if (i > 0 && Math.abs(grid[i - 1][j]) == val) {
                return "pla_ship_bottom.png"; // This tile is the bottom end
            }
            if (i < SIZE - 1 && Math.abs(grid[i + 1][j]) == val) {
                return "pla_ship_top.png"; // This tile is the top end
            }
        }


        return null; // shouldn't happen
    }

    // After updating the opponent grid or handling a shot in the gamePanel class
    private void updateRemainingShips() {
        int opp_remainingShips = gameLogic.getRemainingShips(0);
        System.out.println("Remaining opponent ships: " + opp_remainingShips);
        int player_remainingShips = gameLogic.getRemainingShips(1);
        System.out.println("Remaining player ships: " + player_remainingShips);

        // Check if the game is over
        if (opp_remainingShips == 0 || player_remainingShips == 0) {

            // You can disable further interaction or close the game
            int[][] original_opponentTiles = gameLogic.getOpponentTiles(0);
            int[][] opponentTiles = gameLogic.getOpponentTiles(1);

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    JButton button = opponentButtons[i][j];
                    int tile = original_opponentTiles[i][j];
                    int tile2 = opponentTiles[i][j];

                    if (tile != 0 && tile2 != -1) {
                        // Use different images for part 1 and part 2 of the ship
                        String imageName = getShipImageName(original_opponentTiles, i, j, 0);
                        ImageIcon icon = new ImageIcon("res/Game/Ship/" + imageName);

                        button.setIcon(icon);
                        button.setOpaque(false);
                    }
                }
            }


            String msg = (opp_remainingShips == 0) ? "Game Over! You Win!\nBack to Main Menu" : "Game Over! You Lose!\nBack to Main Menu";
            JOptionPane.showMessageDialog(this, msg);


            bgm.changeTrack("res/BGM/MainMenuBGM.wav");
            CardLayoutPanel.updatePanel(0);
            Frame1.switchFrame("res/MainMenu/background.jpg");

        }
    }


    private TilesBackgroundPanel createTiles(JButton[][] buttons, int side) {

        JButton[][] button = buttons;

        // Create the opponent's grid panel
        TilesBackgroundPanel Panel = new TilesBackgroundPanel("res/Game/TilesBackground.jpg");
        Panel.setLayout(new GridLayout(SIZE, SIZE));

        // Add buttons to the opponent grid
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                button[i][j] = new JButton();
                button[i][j].setBackground(Color.BLUE); // Blue for empty tiles
                final int row = i;
                final int col = j;

                // Using a lambda expression for the action listener
                button[i][j].addActionListener(e -> {
                    // Handle shot and update the UI
                    String result = gameLogic.handleShot(row, col, side);

                    if (result.equals("Hit!")) {
                        ImageIcon icon = new ImageIcon("res/Game/Ship/" + "hit.png");
                        button[row][col].setIcon(icon);


                    } else if (result.equals("Miss!")) {
                        ImageIcon icon = new ImageIcon("res/Game/Ship/" + "miss.png");
                        button[row][col].setIcon(icon);


                    } else if (result.equals("Ship Sunk!")) {
                        ImageIcon icon = new ImageIcon("res/Game/Ship/" + "hit.png");
                        button[row][col].setIcon(icon);
                        JOptionPane.showMessageDialog(null, "A ship has been sunk!");


                    }

                    // Update the number of remaining ships
                    updateRemainingShips();
                });

                Panel.add(button[i][j]);
            }
        }

        return Panel;

    }


}