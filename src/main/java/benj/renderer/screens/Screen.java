package benj.renderer.screens;

import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import benj.renderer.ui.AppTheme;

public abstract class Screen extends JPanel {
    private GridBagConstraints gbc = new GridBagConstraints();

    public Screen() {
        setBackground(AppTheme.COLOR_SECONDARY);
        setLayout(new GridBagLayout());
    }

    /*
     * Add a container for laying out elements of a page.
     * All containers span the width of the window.
     * 
     * @param the container to apply
     * 
     * @param whether the container should be a fixed or dynamic height.
     * 
     * @param the fixed height of the container
     */
    protected void addContainer(JComponent container, int gridx, int gridy, boolean isDynamic, int height) {
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.BOTH;

        if (isDynamic)
            gbc.weighty = 1;
        else
            gbc.weighty = 0;

        add(container, gbc);
    }

    protected void addContainer(JComponent container, int gridy, boolean isDynamic) {
        addContainer(container, 0, gridy, isDynamic, 0);
    }

    protected void addContainer(JComponent container, int gridy) {
        addContainer(container, gridy, false);
    }

    /*
     * Behaviour for when the screen is shown.
     */
    public void onShow() {
    }

    /*
     * Behaviour for when the screen is hidden.
     */
    public void onHide() {
    }
}
