import Objects.Authors;
import Objects.Library;
import Services.Parser;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
    Если в кратце описывать сделанную мною работу, то получится как-то так:
    1) Создание исходного XML-файла.
    2) Десериализация и изменение структуры оного при помощи вручную написанного хандлера библиотеки StAX.
    3) Де / Сериализация в .json при помощи GSON.
    4) Сериализация в XML при помощи JAXB.
*/

public class main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, JAXBException {
        Parser parser = new Parser();

        System.out.println("Введите название исходного файла с расширением: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String output = null;

        if(input.matches(".+\\.xml")){
            Authors authors = parser.parseFromXML(input);
            System.out.println("Введите название сохраняемого JSON файла: ");
            output = scanner.nextLine();
            parser.parseToJson(authors, output);
        }
        else if(input.matches(".+\\.json")){
            Library library = parser.parseFromJson(new File(input));
            System.out.println("Введите название сохраняемого XML файла: ");
            output = scanner.nextLine();
            parser.parseToXML(library, output);
        }
    }
}
