package benj.renderer.ui.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class Carousel extends Component {

    public Carousel() {
        setOpaque(false);
        setLayout(new BorderLayout());

        // Content Panel to hold the cards
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 32, 0));
        contentPanel.setOpaque(false);
        // Add some padding around the content
        contentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Add 8 Upgrade Cards
        for (int i = 0; i < 8; i++) {
            contentPanel.add(new UpgradeCard());
        }

        // Scroll Pane
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        // Make scrollbar invisible or styled if needed, for now standard
        // To hide scrollbar but keep functionality:
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(0, 0));

        add(scrollPane, BorderLayout.CENTER);
    }
}
