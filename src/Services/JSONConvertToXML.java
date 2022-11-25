package Services;

import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONConvertToXML {
    public static void doConvert(File file, String output) throws IOException {
        String jsonString = Files.readString(file.toPath());
        JSONObject json = new JSONObject(jsonString);
        Files.write(Paths.get(output + ".xml"), XML.toString(json).getBytes());
    }
}
