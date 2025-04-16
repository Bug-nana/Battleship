package Battleship;

import javax.swing.*;
import java.awt.*;

public class GameStatus extends JPanel {

    public static JLabel lblOpponentShip, lblShot, lblTurn;
    private final JLabel lblTitle;

    public GameStatus() {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;

        lblTitle = new JLabel("Opponent Status");
        add(lblTitle, c);

        c.gridy = 1;
        lblOpponentShip = new JLabel("Ship Remaining: " + GameLogic.opponentShip);
        add(lblOpponentShip, c);

        c.gridy = 2;
        JLabel lblSpacer = new JLabel(" ");
        add(lblSpacer, c);

        c.gridy = 3;
        lblShot = new JLabel("Last Shot: ");
        add(lblShot, c);

    }
}
