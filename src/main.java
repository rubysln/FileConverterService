import Objects.Library;
import Services.JSONConvertToXML;
import Services.JsonToPojo;
import Services.XMLConvertToJSON;
import StreamAPI.StreamAPI;

import java.io.*;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.json");
        Library library = doParse(file);
        StreamAPI stream = new StreamAPI(library);

        System.out.println(stream.filter(100, 1990).toString());
        System.out.println(stream.forEach("Классика"));
    }

    public static void doConvert() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));


        System.out.println("Введите имя входного файла с расширением: ");
        String inputFile = bf.readLine();
        System.out.println("Введите имя конвертного файла: ");
        String outputFile = bf.readLine();
        if(inputFile.matches(".+\\.xml")){
            File file = new File(inputFile);
            XMLConvertToJSON.doConvert(file, outputFile);
        }
        else if(inputFile.matches(".+\\.json")){
            File file = new File(inputFile);
            JSONConvertToXML.doConvert(file, outputFile);
        }
    }

    public static Library doParse(File file) throws FileNotFoundException {
        JsonToPojo jsonToPojo = new JsonToPojo();
        return jsonToPojo.parse(file);
    }
}
