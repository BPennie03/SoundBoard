package com.tomfoolery.soundboard.SoundBoard;

import com.tomfoolery.soundboard.Enums.OS;
import com.tomfoolery.soundboard.Exceptions.OperatingSystemNotFoundException;
import com.tomfoolery.soundboard.Utils.Utils;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

public class Driver {
    public static void main(String[] args) {
       new Driver();
    }
    public Driver(){
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Soundboard");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        JPanel root = new JPanel();
        root.setLayout(new BorderLayout());
        root.add(new Soundboard(Utils.getSizeOfBoard()), BorderLayout.CENTER);
        root.add(new Controls(frame),BorderLayout.SOUTH);
        root.setVisible(true);

        /*
        Need to test if this works on Windows. If it doesn't need to add
        frame.setIconImage(new ImageIcon("assets/TFSB.png).getImage);
        Taskbar thing only works java 9+
         */
        try{
            if(Utils.getOS() == OS.OSX){
                Taskbar.getTaskbar().setIconImage(new ImageIcon("assets/TFSB.png").getImage());
                //comment from braden's laptop
                //comment from reese
            }
            else{
                frame.setIconImage(new ImageIcon("asset/TFSB").getImage());
            }
        }
        catch (OperatingSystemNotFoundException e){
            e.printStackTrace();
        }

        frame.add(root);
        frame.setVisible(true);
        playOutro(frame, "audio/ByeBye.wav");
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