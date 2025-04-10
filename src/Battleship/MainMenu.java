package Battleship;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Battleship.Main.bgm;


public class MainMenu extends JPanel {
    private JButton btnStart, btnSetting, btnInstruction, btnExit;
    private static JLabel lblBattleship;



    public MainMenu() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;


        c.gridx = 0;


        c.gridy = 0;
        lblBattleship = new JLabel("<html><font color='red'>Battle</font><font color='blue'>Ship</font></html>");
        lblBattleship.setFont(new Font("Serif", Font.BOLD, 70));
        add(lblBattleship, c);

        lblAnimation();


        c.gridy = 1;
        c.fill = GridBagConstraints.NONE;
        btnStart = new JButton(new ImageIcon("res/MainMenu/StartButton.jpg"));
        btnStart.setOpaque(false);
        btnStart.setBorder(new LineBorder(Color.BLACK, 7, true));
        add(btnStart, c);
        btnStart.addActionListener(e -> {
            bgm.changeTrack("res/BGM/BattleBGM.wav");
            CardLayoutPanel.updatePanel(3);
        });

        c.gridy = 2;
        c.insets = new Insets(10, 0, 10, 0);
        btnInstruction = new JButton(new ImageIcon("res/MainMenu/InstructionButton.jpg"));
        btnInstruction.setOpaque(false);
        btnInstruction.setBorder(new LineBorder(new Color(0, 112, 207), 5, true));  // Color and thickness of border
        add(btnInstruction, c);
        btnInstruction.addActionListener(e -> {CardLayoutPanel.updatePanel(2);});

        c.gridy = 3;
        JPanel panel1 = new JPanel(new FlowLayout());
        panel1.setPreferredSize(new Dimension(210, 60));
        panel1.setOpaque(false);
        add(panel1, c);

        btnSetting = new JButton(new ImageIcon("res/MainMenu/SettingButton.png"));
        btnSetting.setOpaque(false);
        btnSetting.setContentAreaFilled(false);
        btnSetting.setBorderPainted(false);
        panel1.add(btnSetting);
        btnSetting.addActionListener(e -> {CardLayoutPanel.updatePanel(1);});

        btnExit = new JButton(new ImageIcon("res/MainMenu/ExitButton.png"));
        btnExit.setOpaque(false);
        btnExit.setContentAreaFilled(false);
        btnExit.setBorderPainted(false);
        panel1.add(btnExit);
        btnExit.addActionListener(e -> {System.exit(0);});
    }


    private static void lblAnimation() {
        Timer timer = new Timer(30, new ActionListener() {
            int direction = 1; // 1 for down, -1 for up
            int yPosition = 50; // Initial vertical position

            @Override
            public void actionPerformed(ActionEvent e) {
                // Move the label up and down
                yPosition += direction;

                // Change direction when reaching certain limits
                if (yPosition >= 100) {
                    direction = -1; // Move up
                } else if (yPosition <= 60) {
                    direction = 1; // Move down
                }

                // Update the label position
                lblBattleship.setLocation(lblBattleship.getX(), yPosition);
            }
        });

        timer.start();

    }

}
