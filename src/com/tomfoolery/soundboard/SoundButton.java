package com.tomfoolery.soundboard;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
public class SoundButton extends JButton implements MouseListener {

    private final File audioFile;
    private final String name;
    private final Color backgroundColor = Color.decode("#9C89FF");
    private final Color hoverColor = Color.decode("#8A7AE2");
    private final Color borderColor1 = Color.decode("#7E38B7");
    private final Color borderColor2 = Color.decode("#541675");


    public SoundButton(String name, File f) {
        this.name = name;
        this.audioFile = f;
        initSoundButton();
    }

    public void initSoundButton(){
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

    @Override
    public void mouseClicked(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){
        if (e.getSource()==this) {
            this.setBorder(new BevelBorder(BevelBorder.RAISED, this.borderColor1, this.borderColor2));
        }
    }

    @Override
    public void mousePressed(MouseEvent e){
        if (e.getSource()==this) {
            this.setBorder(new BevelBorder(BevelBorder.LOWERED, this.borderColor1, this.borderColor2));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource()==this) {
            this.setBackground(this.hoverColor);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource()==this) {
            this.setBackground(this.backgroundColor);
        }
    }

}
