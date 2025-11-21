package benj.renderer.ui;

import java.awt.Color;
import java.awt.Font;

/*
 * Centralized theme management for the application.
 * Defines colors and fonts used throughout the UI.
 */
public class AppTheme {
        // -- Colours --
        public static final Color COLOR_PRIMARY = new Color(34, 45, 61),
                        COLOR_SECONDARY = new Color(55, 68, 95),
                        COLOR_TEXT = new Color(254, 254, 254),
                        COLOR_TEXT_DARK = new Color(25, 25, 25),
                        // secondary colours
                        COLOR_UNAVAILABLE = new Color(144, 144, 144),
                        COLOR_UNAVAILABLE2 = new Color(144, 144, 144, 128),
                        COLOR_VALID = new Color(87, 165, 80),
                        COLOR_INVALID = new Color(255, 71, 74);

        // -- Fonts --
        public static final String FONT_FAMILY = "Roboto";
        public static final int FONT_SIZE_HEADING = 64,
                        FONT_SIZE_SUB_HEADING = 32,
                        FONT_SIZE_NORMAL = 20,
                        FONT_SIZE_SMALL = 16;
        public static final Font FONT_HEADING = new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE_HEADING),
                        FONT_SUB_HEADING = new Font(FONT_FAMILY, Font.BOLD, FONT_SIZE_SUB_HEADING),
                        FONT_NORMAL = new Font(FONT_FAMILY, Font.PLAIN, FONT_SIZE_NORMAL),
                        FONT_SMALL = new Font(FONT_FAMILY, Font.PLAIN, FONT_SIZE_SMALL);

        // -- Border Radius --
        public static final int BORDER_RADIUS = 16,
                        BORDER_RADIUS_FULL = 999;
}
