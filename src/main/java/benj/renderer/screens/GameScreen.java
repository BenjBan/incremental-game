package benj.renderer.screens;

import javax.swing.JLabel;

import benj.renderer.ui.AppTheme;

/*
 * Game screen for the Incremental Game.
 * Displays the main game interface.
 */
public class GameScreen extends Screen {
    public GameScreen() {
        JLabel header = new JLabel("Game Screen - Under Construction"),
                display = new JLabel("display"),
                content = new JLabel("Game content will appear here."),
                footer = new JLabel("Footer Information");

        addContainer(header, 0);
        addContainer(display, 1);
        addContainer(content, 2, true);
        addContainer(footer, 3);
    }
}