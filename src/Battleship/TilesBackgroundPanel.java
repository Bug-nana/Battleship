package Battleship;

import javax.swing.*;
import java.awt.*;

public class TilesBackgroundPanel extends JPanel {
    private Image backgroundImage;

    public TilesBackgroundPanel(String path) {
        backgroundImage = new ImageIcon(path).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the image to fill the panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
