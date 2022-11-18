package com.tomfoolery.soundboard;

import javax.swing.*;
import java.awt.*;

public class ColorFrame extends JFrame {

    public ColorFrame(){
        initFrame();
        initChoosah();
    }

    public void initFrame() {
        this.setVisible(true);
        this.setSize(100, 100);
        this.setLocationRelativeTo(null);
    }


    public void initChoosah(){
        JColorChooser jcc = new JColorChooser(Color.decode("#FFFF00"));
        Color c = JColorChooser.showDialog(null,"Title", Color.decode("#00FF00"));
        this.add(jcc);
    }


}
