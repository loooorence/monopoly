package monopoly.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Scanner;

public class Resources {

    public static String loadResource(String fileName) throws Exception{
        String result;

        try (InputStream in = Resources.class.getResourceAsStream(fileName);
             Scanner scanner = new Scanner(in, "UTF-8")) {
            result = scanner.useDelimiter("\\A").next();
        }

        return result;
    }

    public static String getResourcePath(String fileName) throws Exception {
        URL resource = Resources.class.getResource(fileName);
        File f = Paths.get(resource.toURI()).toFile();
        return f.getCanonicalPath();
    }
}
