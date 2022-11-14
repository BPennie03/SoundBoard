package Reese;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
public class SoundButton extends JButton implements MouseListener {

    private final File audioFile;
    private final String name;

    public SoundButton(String name, File f) {
        this.name = name;
        this.audioFile = f;
        this.setFocusPainted(false);
        this.setText(name);
        this.setBorder(new BevelBorder(BevelBorder.RAISED, this.borderColor1, this.borderColor2));
        this.setContentAreaFilled(false);
        this.setBackground(backgroundColor);
        this.setFont(new Font("Gill Sans MT", Font.BOLD, 14));
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setOpaque(true);
        addMouseListener(this);
    }

    public String getAudioFile(){
        return this.audioFile.toString();
    }

    public void play(){
        try{
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(this.audioFile.toURI().toURL());
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        }
        catch (LineUnavailableException | UnsupportedAudioFileException | IOException err){
            err.printStackTrace();
        }

    }

    public String getName() {
        return this.name;
    }

}
