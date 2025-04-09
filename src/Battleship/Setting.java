package Battleship;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Setting extends JPanel {

    private JLabel lbl, lblVolume;
    private JButton btnBack;
    private JSlider sliderVolume;
    public static float bgmVolume = 10;

    public Setting(){
        setOpaque(false);
        setLayout(new FlowLayout());

        lbl = new JLabel("Setting Menu");
        add(lbl);

        btnBack = new JButton("Back");
        add(btnBack);
        btnBack.addActionListener(e -> {CardLayoutPanel.updatePanel(0);});

        sliderVolume = new JSlider(JSlider.HORIZONTAL, 0, 100, (int)bgmVolume);
        add(sliderVolume);
        lblVolume = new JLabel(String.valueOf(bgmVolume));
        add(lblVolume);



        sliderVolume.addChangeListener(e -> {
            Main.changeVolume(sliderVolume.getValue());
            bgmVolume = sliderVolume.getValue();
            lblVolume.setText(String.valueOf(bgmVolume));
        });
    }

}
