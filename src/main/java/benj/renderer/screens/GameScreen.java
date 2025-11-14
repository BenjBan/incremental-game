package main.java.benj.renderer.screens;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

/*
 * Game screen for the Incremental Game.
 * Displays the main game interface.
 */
public class GameScreen extends Screen {
    public GameScreen() {
        setBackground(Color.LIGHT_GRAY);

        JLabel label = new JLabel("Game Screen - Under Construction");
        label.setFont(new Font("Roboto-Mono", Font.PLAIN, 24));
        label.setHorizontalAlignment(JLabel.CENTER);
        add(label, BorderLayout.CENTER);
    }
}