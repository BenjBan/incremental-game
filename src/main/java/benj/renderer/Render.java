package main.java.benj.renderer;

import javax.swing.JFrame;
import javax.swing.JLabel;

/*
 * This module is responsible for rendering the game window and its contents.
 * It sets up the graphical user interface for the game, including
 * displaying text, images, and other visual elements.
 */
public class Render extends JFrame {
    public Render() {
        JLabel label = new JLabel("Welcome to the Incremental Game!");
        label.setHorizontalAlignment(JLabel.CENTER);

        setTitle("Incremental Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        add(label);
        setVisible(true);
    }
}
