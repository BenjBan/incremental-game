package benj.renderer.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
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
        g.setColor(color);
        g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
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
     * @return true if the border is opaque
     */
    @Override
    public boolean isBorderOpaque() {
        return true;
    }
}