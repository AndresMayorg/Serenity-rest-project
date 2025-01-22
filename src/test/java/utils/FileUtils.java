package utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    public static String readBase64FromFile(String filePath) throws IOException {
        String path = "src/test/resources" + filePath;
        return Files.readString(Paths.get(path));
    }
}
