package benj.renderer.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.border.Border;

/*
 * Custom rounded border implementation for Swing components.
 * Allows setting border radius, color, and padding.
 */
public class RoundedBorder implements Border {
    private Color color;
    private int radius;
    private Insets padding;

    /*
     * Constructor to set the radius and color
     * 
     * @param radius
     * 
     * @param color of the border
     * 
     * @param top padding
     * 
     * @param right padding
     * 
     * @param bottom padding
     * 
     * @param left padding
     */
    public RoundedBorder(Color color, int radius, int top, int right, int bottom, int left) {
        this.color = color;
        this.radius = radius;
        this.padding = new Insets(top, left, bottom, right);
    }

    /*
     * Paints the border with rounded corners.
     * 
     * @param the component for which this border is being painted
     * 
     * @param the graphics context
     * 
     * @param the x position of the border
     * 
     * @param the y position of the border
     * 
     * @param the width of the border
     * 
     * @param the height of the border
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        // Use Graphics2D for anti-aliasing (smoother edges)
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Calculate actual radius for pill shape if needed
        int actualRadius = radius;
        if (radius >= 999) { // Using the 999 constant for pill shape
            actualRadius = height;
        }

        // 2. DRAW THE BORDER: Draw the outline with the specified border color
        g2.setColor(color);
        g2.drawRoundRect(x, y, width - 1, height - 1, actualRadius, actualRadius);

        g2.dispose();
    }

    /*
     * sets the padding for the component.
     * 
     * @param the component for which this border insets value applies
     * 
     * @return the insets of the border
     */
    @Override
    public Insets getBorderInsets(Component c) {
        return padding;
    }

    /*
     * Allows the border to be seen.
     * 
     * @return fill the area within the border
     */
    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}