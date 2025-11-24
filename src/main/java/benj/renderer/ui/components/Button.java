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
    private int radius;

    public Button(String text, Color backgroundColor, Color borderColor, int radius, int paddingTop, int paddingRight,
            int paddingBottom, int paddingLeft) {
        super(text);
        this.radius = radius;
        setContentAreaFilled(false);
        setBorderPainted(true); // Enable
        // border
        // painting
        // so
        // RoundedBorder
        // is
        // called
        setOpaque(false);
        setBorder(new RoundedBorder(borderColor, radius, paddingTop, paddingRight, paddingBottom, paddingLeft));
        setForeground(AppTheme.COLOR_TEXT);
        setBackground(backgroundColor);
        setFont(AppTheme.FONT_NORMAL);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        java.awt.Graphics2D g2 = (java.awt.Graphics2D) g.create();
        g2.setRenderingHint(java.awt.RenderingHints.KEY_ANTIALIASING, java.awt.RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());

        // Calculate actual radius for pill shape if needed, matching RoundedBorder
        // logic
        int actualRadius = radius;
        if (radius >= 999) {
            actualRadius = getHeight(); // fillRoundRect uses diameter for arc width/height, but wait.
            // fillRoundRect(x, y, w, h, arcWidth, arcHeight)
            // If we want a pill, arcWidth and arcHeight should be the height of the
            // component.
            // RoundedBorder uses height/2 for radius, but drawRoundRect takes
            // arcWidth/arcHeight?
            // Let's check RoundedBorder again.
            // RoundedBorder: g2.drawRoundRect(x, y, width - 1, height - 1, actualRadius,
            // actualRadius);
            // If RoundedBorder sets actualRadius = height / 2, then it's drawing corners
            // with that radius.
            // drawRoundRect takes arcWidth and arcHeight. If arcWidth = height/2, that's
            // not a full semicircle (which needs arcWidth = height).
            // Wait, Java's drawRoundRect/fillRoundRect takes arcWidth and arcHeight.
            // To get a full semicircle end, arcHeight should be equal to the height of the
            // rectangle.

            // Let's re-read RoundedBorder.
            // if (radius >= 999) { actualRadius = height / 2; }
            // g2.drawRoundRect(..., actualRadius, actualRadius);

            // If height is 50, actualRadius becomes 25.
            // drawRoundRect(..., 25, 25). The corners will be quarter-circles of radius
            // 12.5? No.
            // The documentation says "arcWidth - the horizontal diameter of the arc at the
            // four corners".
            // So if arcWidth is 25, the radius is 12.5.
            // If we want a pill shape (semicircle ends), the radius should be height/2, so
            // the diameter (arcWidth) should be height.

            // So RoundedBorder seems to be using height/2 as the arc diameter? That would
            // mean corners are not fully round?
            // If the user says "Start Game button looks off", maybe RoundedBorder is ALSO
            // wrong?
            // But the user said "Start Game button looks off" and provided a screenshot
            // where the border seems to be INSIDE the fill or something.
            // Actually, if RoundedBorder uses height/2, and Button uses 999, Button is
            // drawing HUGE corners (oval), and Border is drawing small corners.

            // If I want a pill shape:
            // arcWidth = height
            // arcHeight = height

            actualRadius = getHeight();
        }

        // Fill the rounded rectangle
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), actualRadius, actualRadius);

        g2.dispose();
        super.paintComponent(g);
    }
}
