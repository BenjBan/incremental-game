package benj.renderer.ui.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import benj.renderer.ui.AppTheme;

public class BottomNav extends Component {

    public BottomNav() {
        setOpaque(false);
        // 1 row, 5 columns, 32px horizontal gap, 0px vertical gap
        setLayout(new GridLayout(1, 5, 32, 0));
        // 16px vertical padding, 64px horizontal padding
        setBorder(new EmptyBorder(16, 64, 16, 64));

        for (int i = 1; i <= 5; i++) {
            // Using HTML to force wrapping as seen in the screenshot
            JLabel label = new JLabel("<html><center>Upgr<br>ade " + i + "</center></html>", SwingConstants.CENTER);
            label.setFont(AppTheme.FONT_NORMAL);
            label.setForeground(AppTheme.COLOR_TEXT);
            label.putClientProperty(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            // Enforce 64x64 pixel size for each button
            label.setPreferredSize(new java.awt.Dimension(64, 64));
            add(label);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(AppTheme.COLOR_PRIMARY);

        // Draw pill shape
        // Use height for radius to ensure semicircular ends
        int radius = getHeight();
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        g2.dispose();
        // Do not call super.paintComponent(g) as we handle the background
    }
}
