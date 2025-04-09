package Battleship;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class MainMenu extends JPanel {
    private JButton btnStart, btnSetting, btnInstruction;




    public MainMenu() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;


        c.gridx = 0;
        c.gridy = 0;


        btnStart = new JButton("Start");
        add(btnStart, c);

        c.gridy = 1;
        btnSetting = new JButton("Settings");
        add(btnSetting, c);
        btnSetting.addActionListener(e -> {CardLayoutPanel.updatePanel(1);});


        c.gridy = 2;
        btnInstruction = new JButton("Instruction");
        add(btnInstruction, c);
        btnInstruction.addActionListener(e -> {CardLayoutPanel.updatePanel(2);});


    }


}
