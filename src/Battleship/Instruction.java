package Battleship;

import javax.swing.*;
import java.awt.*;


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
