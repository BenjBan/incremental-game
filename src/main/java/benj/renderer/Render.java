package main.java.benj.renderer;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Taskbar;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import main.java.benj.renderer.screens.*;

/*
 * This module is responsible for rendering the game window and its contents.
 * It sets up the GUI for the game, including
 * displaying text, images, and other visual elements.
 */
public class Render extends JFrame {
    public Render() {
        setTitle("Incremental Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel cardHolder = new JPanel(cardLayout);

        TitleScreen title = new TitleScreen();
        title.addStartGameListener(e -> cardLayout.show(cardHolder, "Game"));
        cardHolder.add(title, "Title");

        GameScreen game = new GameScreen();
        cardHolder.add(game, "Game");

        SettingsScreen settings = new SettingsScreen();
        cardHolder.add(settings, "Settings");

        cardLayout.show(cardHolder, "Title");
        setContentPane(cardHolder);
        setVisible(true);
    }
}
