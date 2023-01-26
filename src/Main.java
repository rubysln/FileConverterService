import objects.Authors;
import objects.Library;
import services.ParseToJson;
import services.ParseToXML;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import services.SaveTo;

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
    Authors authors = ParseToJson.parseFromXML(new File("input.xml"));
    SaveTo.toJson(authors, "output");
    Library library = ParseToXML.parseFromJson(new File("output.json"));
    SaveTo.toXML(library, "output.xml");
  }
}