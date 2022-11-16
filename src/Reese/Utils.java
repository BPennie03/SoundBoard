package Reese;

public class Utils {
    public static OS getOS() throws OperatingSystemNotFoundException {
        String os = System.getProperty("os.name");
        if(os.toLowerCase().contains("mac")){
            return OS.OSX;
        }
        else if(os.toLowerCase().contains("windows")){
            return OS.Windows;
        }
        else if(os.toLowerCase().contains("nix") || os.toLowerCase().contains("nux") || os.toLowerCase().contains("aix")){
            return OS.Unix;
        }
        else{
            throw new OperatingSystemNotFoundException("The application operating system is not supported!");
        }

    }

}
