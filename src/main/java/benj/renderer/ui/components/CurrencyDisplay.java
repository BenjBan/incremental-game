package benj.renderer.ui.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;

import benj.renderer.ui.AppTheme;

public class CurrencyDisplay extends Component {

    public CurrencyDisplay() {
        setOpaque(false);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // 1. Currency Label
        JLabel currency = new JLabel("$0");
        currency.setFont(AppTheme.FONT_HEADING);
        currency.setForeground(AppTheme.COLOR_TEXT);
        currency.putClientProperty(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING,
                java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 20, 0); // Bottom padding
        add(currency, gbc);

        // 2. Stats Row
        String[] stats = { "+1.0", "x1.0", "1.0log1.0", "1.0^2", "2^0.0", "!1.0" };

        JPanel statsPanel = new JPanel(new java.awt.GridLayout(1, stats.length));
        statsPanel.setOpaque(false);

        for (String s : stats) {
            JLabel stat = new JLabel(s, javax.swing.SwingConstants.CENTER);
            stat.setFont(AppTheme.FONT_SMALL);
            stat.setForeground(AppTheme.COLOR_TEXT);
            stat.putClientProperty(java.awt.RenderingHints.KEY_TEXT_ANTIALIASING,
                    java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            statsPanel.add(stat);
        }

        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 0, 0, 0);
        add(statsPanel, gbc);
    }
}
