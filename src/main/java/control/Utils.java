package control;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Utils {
    public static String loadResource(String path) throws Exception{
        String result;
        try(InputStream in = Utils.class.getResourceAsStream(path);
            Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name())){
                result = scanner.useDelimiter("\\A").next();
        }

        return result;
    }
}