package Reese;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Controls extends JPanel {

    private final JFrame mainFrame;

    public Controls(JFrame mainFrame){

        this.mainFrame = mainFrame;
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

            //ignore
            choosah.showOpenDialog(null);

            File selectedFile = choosah.getSelectedFile();
            File dir = new File("./audio/" + selectedFile.getName());
            try {
                Files.copy(selectedFile.toPath(), dir.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
                //need to remake the buttons
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });
        this.add(btn);

    }

    public void initRefresh(){
        JButton btn = new JButton();
        btn.setText("Refresh");
        btn.addActionListener(e ->{
            mainFrame.dispose();
            new Main();
        });

        this.add(btn);
    }

}
