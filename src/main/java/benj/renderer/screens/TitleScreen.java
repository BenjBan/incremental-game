package main.java.benj.renderer.screens;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

/*
* Title screen for the Incremental Game.
* Displays the game title and a start button.
*/
public class TitleScreen extends Screen {
    JButton startButton = new JButton("Start Game".toUpperCase());

    public TitleScreen() {
        setBackground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        add(titlePanel, "titlePanel");
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // 1. Game Title
        JLabel label = new JLabel("Welcome to the Incremental Game!");
        label.setFont(new Font("Roboto-Mono", Font.PLAIN, 24));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        gbc.gridy = 0;
        gbc.gridx = 0;
        add(label, gbc);

        // 2. Start Button
        startButton.setPreferredSize(new Dimension(150, 50));
        startButton.setFont(new Font("Roboto-Mono", Font.PLAIN, 12));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(new Color(22, 111, 198));
        startButton.setOpaque(true);
        startButton.setContentAreaFilled(true);
        startButton.setBorder(null);
        startButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        startButton.addActionListener(e -> {
            if (e.getSource() == startButton) {
                System.out.println("Start Game button clicked!");
            }
        });
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(startButton, gbc);
    }

    /*
     * @param listener The action for the start button.
     */
    public void addStartGameListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }
}
