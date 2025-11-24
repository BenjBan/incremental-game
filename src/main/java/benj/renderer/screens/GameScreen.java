package benj.renderer.screens;

import javax.swing.JPanel;

import java.awt.FlowLayout;

import benj.renderer.ui.components.BottomNav;
import benj.renderer.ui.components.Carousel;
import benj.renderer.ui.components.Component;
import benj.renderer.ui.components.CurrencyDisplay;
import benj.renderer.ui.components.TopNav;

/*
 * Game screen for the Incremental Game.
 * Displays the main game interface.
 */
public class GameScreen extends Screen {
    public GameScreen() {
        Component header = new TopNav(),
                display = new CurrencyDisplay(),
                content = new Carousel(),
                footer = new BottomNav();

        JPanel footerWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        footerWrapper.setOpaque(false);
        footerWrapper.add(footer);

        addContainer(header, 0);
        addContainer(display, 1);
        addContainer(content, 2, true);
        addContainer(footerWrapper, 3);
    }
}