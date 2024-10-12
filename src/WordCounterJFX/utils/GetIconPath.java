package WordCounterJFX.utils;

import java.io.File;

public class GetIconPath {

    public static String getIconPath() {
        String invocationDir = System.getProperty("user.dir");
        String pathToIcon = invocationDir + "\\bin\\WordCounterJFX\\resources\\icon.png";
        return new File(pathToIcon).toURI().toString();
    }
}