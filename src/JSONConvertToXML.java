import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class JSONConvertToXML {
    public static void doConvert(File file, String output) throws IOException {
        String jsonString = Files.readString(file.toPath());
    }
}
