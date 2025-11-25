package benj.renderer.ui.components;

import javax.swing.*;
import java.awt.*;

import benj.renderer.ui.AppTheme;

public class TopNav extends Component {

    public TopNav() {
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // 1. Title
        JLabel title = new JLabel("Incremental Game");
        title.setFont(AppTheme.FONT_SUB_HEADING);
        title.setForeground(AppTheme.COLOR_TEXT);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(20, 0, 10, 0); // Top padding
        gbc.anchor = GridBagConstraints.CENTER;
        add(title, gbc);

        // 2. Bottom Row (Links + Settings)
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);

        // Links
        JPanel linksPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        linksPanel.setOpaque(false);

        String[] worlds = { "World 1", "World 2", "World 3", "World 4", "World 5" };
        for (String world : worlds) {
            JLabel link = new JLabel(world);
            link.setFont(AppTheme.FONT_NORMAL); // Or SMALL?
            link.setForeground(AppTheme.COLOR_TEXT);
            link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            linksPanel.add(link);
        }

        bottomPanel.add(linksPanel, BorderLayout.CENTER);

        // Settings Icon
        JLabel settings = new JLabel("\u2699"); // Gear icon
        settings.setFont(AppTheme.FONT_SUB_HEADING);
        settings.setForeground(AppTheme.COLOR_TEXT);
        settings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        // Add some padding to the right of settings
        settings.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 32));

        bottomPanel.add(settings, BorderLayout.EAST);

        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 20, 0); // Bottom padding
        add(bottomPanel, gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(AppTheme.COLOR_PRIMARY);

        // Draw rounded rectangle shifted up to keep top corners square
        int radius = AppTheme.BORDER_RADIUS; // Adjust as needed for the curve
        g2.fillRoundRect(0, -radius, getWidth(), getHeight() + radius, radius, radius);

        g2.dispose();
        // Do not call super.paintComponent(g) because we are handling the background
        // and setOpaque(false) handles the transparency behind the rounded corners.
    }
}
