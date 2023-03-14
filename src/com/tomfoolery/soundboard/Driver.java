package com.tomfoolery.soundboard;

import com.tomfoolery.soundboard.Utils.Utils;
import com.tomfoolery.soundboard.enums.OS;
import com.tomfoolery.soundboard.exceptions.OperatingSystemNotFoundException;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {
    public static void main(String[] args) {
        new Driver();
    }

    /**
     * Main Driver class that runs the Soundboard program
     */
    public Driver() {

        // Frame options
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Soundboard");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Panel options
        JPanel root = new JPanel(new BorderLayout());
        root.add(new Soundboard(Utils.getSizeOfBoard()), BorderLayout.CENTER);
        root.add(new Controls(frame), BorderLayout.SOUTH);
        root.setVisible(true);


        // Gets the operating system and chooses which method to use to display the custom
        // taskbar Icon
        try {
            if (Utils.getOS() == OS.OSX) {
                Taskbar.getTaskbar().setIconImage(new ImageIcon("assets/TFSB.png").getImage());
            } else {
                frame.setIconImage(new ImageIcon("asset/TFSB").getImage());
            }
        } catch (OperatingSystemNotFoundException e) {
            e.printStackTrace();
        }

        frame.add(root);
        frame.setVisible(true);

        // Plays the outro for when the frame is closed
        playOutro(frame, "audio/Discord Leave.wav");
    }

    /**
     * Play Outro play's the specified leave audio for whenever the window/frame is closed
     *
     * @param frame the main frame of the soundboard
     * @param s     string for the path to the outro audio
     */
    public static void playOutro(JFrame frame, String s) {

        // Adds a window listener for a "windowClosing" event that will detect when the
        // specified window is closed and will execute the audio when closed
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                FileInputStream input;
                try {
                    input = new FileInputStream(s);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

                // Plays the audio
                BufferedInputStream buffInput = new BufferedInputStream(input);
                AudioInputStream audio;
                try {
                    audio = AudioSystem.getAudioInputStream(buffInput);

                    Clip clip = AudioSystem.getClip();
                    clip.open(audio);
                    clip.start();

                } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}