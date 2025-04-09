package Battleship;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CardLayoutPanel extends JPanel {

    private static CardLayout cardLayout = new CardLayout();
    private static JPanel cardPanel = new JPanel(cardLayout);
    private BufferedImage backgroundImage;


    public CardLayoutPanel() {

        try {
            // Load the image once when the panel is created
            backgroundImage = ImageIO.read(new File("res/MainMenu/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace(); // Handle exception
        }



        cardPanel.setOpaque(false);
        cardPanel.setPreferredSize(new Dimension(Frame1.frameWidth, Frame1.frameHeight));


        // Create cards
        JPanel menuPanel = new MainMenu();
        JPanel settingsPanel = new Setting();
        JPanel instructionPanel = new Instruction();


        // Add cards to card panel
        cardPanel.add(menuPanel, "Menu");
        cardPanel.add(settingsPanel, "Settings");
        cardPanel.add(instructionPanel, "Instruction");

        // Add to frame
        add(cardPanel);

        // Show initial card
        cardLayout.show(cardPanel, "Menu");

    }

    public static void updatePanel(int panel){
        switch(panel){
            case 1:
                cardLayout.show(cardPanel, "Settings");
                break;
            case 2:
                cardLayout.show(cardPanel, "Instruction");
                break;
            default:
                cardLayout.show(cardPanel, "Menu");
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            // Draw the background image, stretched to fill the panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
