import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class XMLConvertToJSON {
    public static void doConvert(File file,String output) throws IOException {
        String xml = Files.readString(file.toPath());
    }
}
