package Reese;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class SoundButton extends JButton {

    private final File audioFile;
    private final String name;

    public SoundButton(String name, File f) {
        this.name = name;
        this.audioFile = f;
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
