package benj.renderer.ui.components;

import java.awt.Color;

import javax.swing.JButton;

import benj.renderer.ui.AppTheme;
import benj.renderer.ui.RoundedBorder;

/*
 * Automatically styled button. 
 * Uses default button if type isn't specified.
 */
public class Button extends JButton {
    public Button(String text, Color backgroundColor, Color borderColor) {
        super(text);

        setContentAreaFilled(false);
        setBorderPainted(false);
        setFont(AppTheme.FONT_SUB_HEADING);
        setForeground(AppTheme.COLOR_TEXT);
        setBackground(backgroundColor);
        setBorder(new RoundedBorder(borderColor, AppTheme.BORDER_RADIUS, 32, 16, 32, 16));
    }
}
