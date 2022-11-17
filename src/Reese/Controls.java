package Reese;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Controls extends JPanel {

    private final JFrame mainFrame;
    public Controls(JFrame mainFrame){

        this.mainFrame = mainFrame;
        this.setBackground(Color.decode("#7B64ED"));
        initChooser();
        initRefresh();
        //do other stuff
    }

    public void initChooser(){
        JButton btn = new JButton();
        btn.setText("Add Sound!");
        btn.addActionListener(e ->{

            JFileChooser choosah = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".WAV files only", "wav");
            choosah.setFileFilter(filter);
            choosah.setMultiSelectionEnabled(true);

            choosah.showOpenDialog(null);
            try{
                File[] selectedFiles = choosah.getSelectedFiles();
                for (File selectedFile : selectedFiles) {
                    File dir = new File("./audio/" + selectedFile.getName());
                    Files.copy(selectedFile.toPath(), dir.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
                }
            } catch (IOException | NullPointerException ex) {
                System.out.println("No file was selected!");
            }

        });
        this.add(btn);

    }

    public void initRefresh(){
        JButton btn = new JButton();
        btn.setText("Refresh");
        btn.addActionListener(e ->{
            mainFrame.dispose();
            new Driver();
        });

        this.add(btn);
    }

}
