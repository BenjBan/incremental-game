package main.java.benj.app;

import javax.swing.JFrame;
import javax.swing.JLabel;

import main.java.benj.renderer.Render;

/*
 * This module is responsible for managing the functionality of the game
 * (e.g. starting new games, loading/saving games, managing the application
 * window(s), etc). User input is managed by this module, this includes
 * mapping key strokes back to objects in the game world, etc.
 */
public class App {
    public App() {
        new Render();
    }
}
