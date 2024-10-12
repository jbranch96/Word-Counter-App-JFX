package WordCounterJFX.utils;

import java.io.File;

public class GetIconPath {

    public static String getIconPath() {
        String currentPath = System.getProperty("user.dir");
        File currentDir = new File(currentPath);
        String parentDir =  currentDir.getParent();
        String pathToIcon = parentDir + "\\bin\\WordCounterJFX\\resources\\icon.png";
        return new File(pathToIcon).toURI().toString();
    }
}