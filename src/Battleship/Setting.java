package Battleship;

import javax.swing.*;
import java.awt.*;

import static Battleship.Frame1.frameHeight;


public class Setting extends JPanel {

    public static float bgmVolume = 10;
    private final JLabel lbl;
    private final JLabel lblVolume;
    private final JLabel lbl2;
    private final JButton btnBack;
    private final JSlider sliderVolume;

    public Setting() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;


        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(20, 0, frameHeight / 3, 0);
        lbl = new JLabel("Setting");
        lbl.setFont(new Font("SansSerif", Font.BOLD, 50));
        add(lbl, c);


        c.insets = new Insets(0, 0, 0, 0);
        c.gridy = 1;
        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.setOpaque(true);
        add(panel1, c);

        lbl2 = new JLabel("Music Volume: ");
        lbl2.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel1.add(lbl2);
        sliderVolume = new JSlider(JSlider.HORIZONTAL, 0, 100, (int) bgmVolume);
        panel1.add(sliderVolume);
        lblVolume = new JLabel(String.valueOf(bgmVolume));
        lblVolume.setFont(new Font("SansSerif", Font.BOLD, 20));
        panel1.add(lblVolume);


        sliderVolume.addChangeListener(e -> {
            BGM.changeVolume(sliderVolume.getValue());
            bgmVolume = sliderVolume.getValue();
            lblVolume.setText(String.valueOf(bgmVolume));
        });

        c.gridy = 2;
        c.insets = new Insets(30, 0, 0, 0);
        btnBack = new JButton("Back");

        btnBack.setFont(new Font("Arial", Font.BOLD, 18));
        btnBack.setPreferredSize(new Dimension(120, 40));
        btnBack.setBackground(Color.LIGHT_GRAY);
        btnBack.setContentAreaFilled(false);

        add(btnBack, c);
        btnBack.addActionListener(e -> {
            CardLayoutPanel.updatePanel(0);
        });

        c.gridy = 3;
        c.weighty = 1.0;
        add(Box.createVerticalGlue(), c);
    }

}
