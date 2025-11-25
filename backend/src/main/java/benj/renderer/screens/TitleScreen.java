package benj.renderer.screens;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

import benj.renderer.ui.AppTheme;
import benj.renderer.ui.components.Button;

/*
* Title screen for the Incremental Game.
* Displays the game title and a start button.
*/
public class TitleScreen extends Screen {
    JButton startButton = new Button("Start Game", AppTheme.COLOR_VALID, AppTheme.COLOR_VALID,
            AppTheme.BORDER_RADIUS_MAX, 16, 32, 16, 32);

    public TitleScreen() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(16, 16, 16, 16);
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new GridBagLayout());
        add(titlePanel, gbc);

        // 1. Game Title
        JLabel label = new JLabel("Incremental Game");
        label.setFont(AppTheme.FONT_HEADING);
        label.setForeground(AppTheme.COLOR_TEXT);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(label, gbc);

        // 2. Start Button
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(startButton, gbc);
    }

    /*
     * @param The action for the start button.
     */
    public void addStartGameListener(ActionListener listener) {
        startButton.addActionListener(listener);
    }
}
