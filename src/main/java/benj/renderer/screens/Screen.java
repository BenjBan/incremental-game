package benj.renderer.screens;

import javax.swing.JPanel;

public abstract class Screen extends JPanel {
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
