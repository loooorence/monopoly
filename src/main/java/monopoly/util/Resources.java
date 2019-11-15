package monopoly.util;

import java.io.InputStream;
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
}
