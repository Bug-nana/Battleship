package Battleship;

//This class will be the Main Window

import javax.swing.*;
import java.awt.*;

public class Frame1 extends JFrame{

    private static JFrame frame = new JFrame("Battleship");

    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int frameWidth= (int) screenSize.getWidth(), frameHeight=(int) screenSize.getHeight();

    //Initializing the MainMenu as the default display
    private static JPanel panel = new CardLayoutPanel("res/MainMenu/background.jpg");

    public Frame1(){
        frame.add(panel);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        frame.setVisible(true);
    }


    public static void switchFrame(String filename){
        frame.remove(panel);
        panel = new CardLayoutPanel(filename);
        frame.add(panel);
    }
}
