package Battleship;

import javax.swing.*;
import java.awt.*;

public class gamePanel extends JPanel {

    private JLabel lbl;

    public gamePanel(){
        setLayout(new FlowLayout());

        lbl = new JLabel("Game");
        add(lbl);
    }

}
