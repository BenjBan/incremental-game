package benj.renderer.ui.components;

import java.awt.Dimension;

public abstract class Card extends Component {

    public Card() {
        // Set fixed size
        setPreferredSize(new Dimension(320, 402));
        setMinimumSize(new Dimension(320, 402));
        setMaximumSize(new Dimension(320, 402));

        setOpaque(false);
    }
}
