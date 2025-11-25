package benj.renderer.ui.components;

import javax.swing.JTextField;
import javax.swing.BorderFactory;

import benj.renderer.ui.AppTheme;

public class TextField extends JTextField {
    public TextField(int columns) {
        super("Enter world name here", columns);
        setFont(AppTheme.FONT_NORMAL);
        setForeground(AppTheme.COLOR_TEXT_DARK);
        setBackground(AppTheme.COLOR_TEXT);
        setCaretColor(AppTheme.COLOR_UNAVAILABLE2);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    }

}
