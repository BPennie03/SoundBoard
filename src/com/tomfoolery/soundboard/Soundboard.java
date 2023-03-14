package com.tomfoolery.soundboard;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.LinkedList;
import java.util.Objects;

/**
 * Class for the soundboard in which the buttons get added to
 */
public class Soundboard extends JPanel {
    public Soundboard(int size) {

        GridLayout layout = new GridLayout();
        layout.setColumns(size);
        layout.setRows(size);
        this.setLayout(layout);
        Color panelColor = Color.decode("#541675");
        this.setBackground(panelColor);

        File dir = new File("./audio");

        makeButtons(Objects.requireNonNull(dir.listFiles()));
    }

    /**
     * Creates soundButons based on the selected files and adds them to the soundBoard
     * @param files the array of files that were selected to be added
     */
    public void makeButtons(File[] files) {
        LinkedList<SoundButton> btns = new LinkedList<>();
        for (File file : files) {
            File filename = new File(file.getAbsolutePath());
            btns.add(new SoundButton(getName(file.getAbsolutePath()), filename));
        }
        for(SoundButton b : btns){
            if(!b.getAudioFile().endsWith("wav")) continue; // only currently supports .wav files
            b.addActionListener(e ->{
                try{
                    b.play();
                }
                catch (Exception err){
                    err.printStackTrace();
                }
            });
            this.add(b);
        }

    }

    /**
     * Takes the filename of the sound, then strips out just the name to be displayed on the button
     * @param filename filename of the sound (.wav file)
     * @return the name to be put on the button
     */
    public static String getName(String filename) {
        return filename.substring(filename.lastIndexOf('/')+1, filename.lastIndexOf('.'));
    }

}
