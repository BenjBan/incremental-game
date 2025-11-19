package main.java.benj.renderer.ui;

public class AppTheme {
    // -- Colours --
    public static final String COLOR_PRIMARY = "#37445F",
            COLOR_SECONDARY = "#222D3D",
            COLOR_TEXT = "#FEFEFE",
            // secondary colours
            COLOR_UNAVAILABLE = "#909090",
            COLOR_VALID = "#57A550",
            COLOR_INVALID = "#FF474A";

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
}
