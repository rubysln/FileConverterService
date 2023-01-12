import objects.Authors;
import objects.Library;
import services.Parser;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

/*
    Если в кратце описывать сделанную мною работу, то получится как-то так:
    1) Создание исходного XML-файла.
    2) Десериализация при помощи JAXB, изменение структуры
    3) Де / Сериализация в .json при помощи GSON.
    4) Сериализация в XML при помощи JAXB.
*/

public class Main {

  public static void main(String[] args)
      throws JAXBException, IOException {
    Parser parser = new Parser();
    Authors authors = parser.parseFromXML(new File("input.xml"));
    parser.parseToJson(authors, "output");
    Library library = parser.parseFromJson(new File("output.json"));
    parser.parseToXML(library, "output.xml");
  }
}