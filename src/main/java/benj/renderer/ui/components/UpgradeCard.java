package benj.renderer.ui.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import benj.renderer.ui.AppTheme;
import benj.renderer.ui.RoundedBorder;

public class UpgradeCard extends Card {

    public UpgradeCard() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;

        // --- 1. Header Panel ---
        JPanel headerPanel = new RoundedPanel();
        headerPanel.setLayout(new GridBagLayout());
        headerPanel.setBorder(new RoundedBorder(AppTheme.COLOR_TEXT, AppTheme.BORDER_RADIUS, 8, 8, 8, 8));
        headerPanel.setBackground(AppTheme.COLOR_SECONDARY);

        GridBagConstraints headerGbc = new GridBagConstraints();
        headerGbc.fill = GridBagConstraints.HORIZONTAL;
        headerGbc.weightx = 1.0;
        headerGbc.gridx = 0;

        // Title
        JLabel title = new JLabel("Title", SwingConstants.CENTER);
        title.setFont(AppTheme.FONT_NORMAL);
        title.setForeground(AppTheme.COLOR_TEXT);
        title.putClientProperty(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        headerGbc.gridy = 0;
        headerPanel.add(title, headerGbc);

        // Rate
        JLabel rate = new JLabel("$0/s", SwingConstants.CENTER);
        rate.setFont(AppTheme.FONT_SUB_HEADING);
        rate.setForeground(AppTheme.COLOR_TEXT);
        rate.putClientProperty(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        headerGbc.gridy = 1;
        headerPanel.add(rate, headerGbc);

        // Description
        JLabel desc = new JLabel("Automatically gain +$1 per second", SwingConstants.CENTER);
        desc.setFont(AppTheme.FONT_SMALL);
        desc.setForeground(AppTheme.COLOR_TEXT);
        desc.putClientProperty(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        headerGbc.gridy = 2;
        headerPanel.add(desc, headerGbc);

        // Add Header Panel to Card
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weighty = 0.3; // Give it some weight
        gbc.insets = new Insets(0, 0, 4, 0); // Gap below header
        add(headerPanel, gbc);

        // --- 2. Body Panel ---
        JPanel bodyPanel = new RoundedPanel();
        bodyPanel.setLayout(new GridBagLayout());
        bodyPanel.setBorder(new RoundedBorder(AppTheme.COLOR_TEXT, AppTheme.BORDER_RADIUS, 8, 8, 8, 8));
        bodyPanel.setBackground(AppTheme.COLOR_SECONDARY);

        GridBagConstraints bodyGbc = new GridBagConstraints();
        bodyGbc.fill = GridBagConstraints.HORIZONTAL;
        bodyGbc.weightx = 1.0;
        bodyGbc.gridx = 0;

        // Spacer (The big empty area)
        JLabel spacer = new JLabel();
        bodyGbc.gridy = 0;
        bodyGbc.weighty = 1.0;
        bodyGbc.fill = GridBagConstraints.BOTH;
        bodyPanel.add(spacer, bodyGbc);

        // Stats
        bodyGbc.weighty = 0;
        bodyGbc.fill = GridBagConstraints.HORIZONTAL;
        String[] stats = { "⬆ Level: 0", "＄ Income: $0", "🕒 Speed: 1.0 seconds" };
        bodyGbc.gridy = 1;
        bodyGbc.insets = new Insets(2, 8, 2, 8);

        for (String statText : stats) {
            JLabel stat = new JLabel(statText);
            stat.setFont(AppTheme.FONT_SMALL);
            stat.setForeground(AppTheme.COLOR_TEXT);
            stat.putClientProperty(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            bodyPanel.add(stat, bodyGbc);
            bodyGbc.gridy++;
        }

        // Add Body Panel to Card
        gbc.gridy = 1;
        gbc.weighty = 0.7; // More weight for body
        gbc.insets = new Insets(4, 0, 4, 0); // Gap above/below body
        add(bodyPanel, gbc);

        // --- 3. Button ---
        Button button = new Button("FREE", AppTheme.COLOR_VALID, AppTheme.COLOR_VALID, AppTheme.BORDER_RADIUS_MAX, 10,
                0, 10, 0);

        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 0, 0, 0); // Gap above button
        add(button, gbc);
    }

    // Helper class for panels with rounded background
    private class RoundedPanel extends JPanel {
        public RoundedPanel() {
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), AppTheme.BORDER_RADIUS, AppTheme.BORDER_RADIUS);
            g2.dispose();
            super.paintComponent(g);
        }
    }
}
