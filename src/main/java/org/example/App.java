package org.example;

import java.awt.*;
import org.example.Presentation.View;
import javax.swing.*;

/**
 * The type start.
 */
public class App {
    /**
     * public static void main() method.
     *
     */
    public static void main(String[] args) {
        JFrame frame = new View("Orders Management");
        frame.setMinimumSize(new Dimension(500, 300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}