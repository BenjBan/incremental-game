package main.java.benj.app;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class App extends JFrame {
    public App() {
        JLabel label = new JLabel("Welcome to the Incremental Game!");
        label.setHorizontalAlignment(JLabel.CENTER);

        setTitle("Incremental Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        add(label);
        setVisible(true);
    }
}
