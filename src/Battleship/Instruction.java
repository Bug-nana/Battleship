package Battleship;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Instruction extends JPanel{

    private JLabel lbl;
    private JButton btnBack;

    public Instruction(){
        setOpaque(false);
        setLayout(new FlowLayout());
        lbl = new JLabel("Instruction");
        add(lbl);


        btnBack = new JButton("Back");
        add(btnBack);
        btnBack.addActionListener(e -> {CardLayoutPanel.updatePanel(0);});
    }

}
