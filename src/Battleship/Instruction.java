package Battleship;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Instruction extends JPanel {

    private final JLabel lbl;
    private final JButton btnBack;

    public Instruction() {
        setOpaque(false);
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.PAGE_START;
        c.insets = new Insets(20, 0, 0, 0);
        lbl = new JLabel("Instruction");
        lbl.setFont(new Font("SansSerif", Font.BOLD, 50));
        add(lbl, c);

        c.gridy = 1;
        c.insets = new Insets(20, 20, 0, 20);
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());
        add(panel1, c);
        JTextArea textarea = new JTextArea(10, 10);
        textarea.setEditable(false);

        // Load predefined text
        try {
            String content = Files.readString(Paths.get("res/docs/help.txt"));
            textarea.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JScrollPane TextAreascrollable = new JScrollPane(textarea);
        TextAreascrollable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        TextAreascrollable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel1.add(TextAreascrollable, BorderLayout.CENTER);


        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(10, 0, 100, 0);
        c.gridy = 2;
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Arial", Font.BOLD, 18));
        btnBack.setPreferredSize(new Dimension(120, 40));
        btnBack.setBackground(Color.LIGHT_GRAY);
        btnBack.setContentAreaFilled(false);
        add(btnBack, c);
        btnBack.addActionListener(e -> {
            CardLayoutPanel.updatePanel(0);
        });


    }

}
