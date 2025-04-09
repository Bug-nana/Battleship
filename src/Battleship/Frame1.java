package Battleship;

//This class will be the Main Window

import javax.swing.*;
import java.awt.*;

public class Frame1 extends JFrame{

    private JFrame frame = new JFrame("Battleship");

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int frameWidth= (int) screenSize.getWidth(), frameHeight=(int) screenSize.getHeight();

    public Frame1(){

        frame.add(new CardLayoutPanel());
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
    }
}
