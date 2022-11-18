package com.tomfoolery.soundboard.Utils;

import com.tomfoolery.soundboard.exceptions.OperatingSystemNotFoundException;
import com.tomfoolery.soundboard.enums.OS;

import java.io.File;
import java.util.Objects;

public class Utils {
    public static OS getOS() throws OperatingSystemNotFoundException {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("mac")){
            return OS.OSX;
        }
        else if(os.contains("windows")){
            return OS.Windows;
        }
        else if(os.contains("nix") || os.contains("nux") || os.contains("aix")){
            return OS.Unix;
        }
        else{
            throw new OperatingSystemNotFoundException("This operating system is not supported!");
        }

    }

    public static int getSizeOfBoard(){
        File dir = new File("./audio");
        int numFiles = Objects.requireNonNull(dir.list()).length;
        return ((int) Math.sqrt(numFiles)) + 1;
    }



}
