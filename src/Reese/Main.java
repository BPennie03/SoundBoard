package Reese;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
       new Main();
    }
    public Main(){
        //JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Soundboard");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        root.add(new Soundboard(getSizeOfBoard()), BorderLayout.CENTER);
        root.add(new Controls(frame),BorderLayout.SOUTH);
        root.setVisible(true);

        frame.add(root);
        frame.setVisible(true);
        playOutro(frame, "audio/ByeBye.wav");
    }


    public static int getSizeOfBoard(){
        File dir = new File("./audio");
        int numFiles = Objects.requireNonNull(dir.list()).length;
        return ((int) Math.sqrt(numFiles)) + 1;
    }

    public static void playOutro(JFrame frame, String s){
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt)
            {
                FileInputStream input;
                try {
                    input = new FileInputStream(s);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }

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