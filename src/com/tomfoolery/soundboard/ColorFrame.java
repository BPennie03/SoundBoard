package com.tomfoolery.soundboard;

import javax.swing.*;
import java.awt.*;

/**
 * ColorFrame class is for when we eventually implement the custom color themes using a
 * JColorChooser that will work with our JsonParser to keep a Json of the user's custom settings
 */
public class ColorFrame extends JFrame {

    public ColorFrame() {
        initFrame();

        // Creates a button that opens the JColor chooser
        // NOTE: This is not currently complete
        JButton btn = new JButton("Pick me");
        btn.setOpaque(true);
        btn.addActionListener(e -> {
            JColorChooser jcc = new JColorChooser(Color.decode("#FFFF00"));
            Color c = JColorChooser.showDialog(null, "Title", Color.decode("#00FF00"));
        });
        this.add(btn);
    }

    /**
     * Initializes the frame and options
     */
    public void initFrame() {
        this.setVisible(true);
        this.setSize(100, 100);
        this.setLocationRelativeTo(null);
    }

}
