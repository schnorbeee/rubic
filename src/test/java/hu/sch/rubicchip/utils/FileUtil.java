package hu.sch.rubicchip.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    public static String readFile(String filePath) {
        try {
            File file = ResourceUtils.getFile(Paths.get("src", "test", "resources") + filePath);
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            throw new RuntimeException("Error file reading. Path:" + filePath, e);
        }
    }
}
