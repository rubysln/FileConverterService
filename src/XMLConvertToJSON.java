import org.json.JSONObject;
import org.json.XML;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class XMLConvertToJSON {
    public static void doConvert(File file,String output) throws IOException {
        String xml = Files.readString(file.toPath());
        JSONObject jsonObject = XML.toJSONObject(xml);
        Files.write(Paths.get(output + ".json"), jsonObject.toString().getBytes());
    }
}
