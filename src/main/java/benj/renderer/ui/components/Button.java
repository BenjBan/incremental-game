package benj.renderer.ui.components;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;

import benj.renderer.ui.AppTheme;
import benj.renderer.ui.RoundedBorder;

/*
 * Automatically styled button. 
 */
public class Button extends JButton {
    public Button(String text, Color backgroundColor, Color borderColor, int radius, int paddingTop, int paddingRight,
            int paddingBottom, int paddingLeft) {
        super(text);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        setBorder(new RoundedBorder(borderColor, radius, paddingTop, paddingRight, paddingBottom, paddingLeft));
        setForeground(AppTheme.COLOR_TEXT);
        setBackground(backgroundColor);
        setFont(AppTheme.FONT_SUB_HEADING);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}
