package benj.renderer.ui.components;

import javax.swing.JPanel;

import benj.renderer.ui.AppTheme;

public class Component extends JPanel {
    public Component() {
        setOpaque(true);
        setForeground(AppTheme.COLOR_TEXT);
        setBackground(AppTheme.COLOR_SECONDARY);
        setFont(AppTheme.FONT_NORMAL);
    }
}
