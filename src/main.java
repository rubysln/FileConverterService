import Services.JSONConvertToXML;
import Services.XMLConvertToJSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class main {
    public static void main(String[] args) throws IOException {
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
}
