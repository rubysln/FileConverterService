import java.io.BufferedReader;
import java.io.InputStreamReader;
import lombok.val;
import objects.Authors;
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
    val bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    System.out.println("Введите имя исходного файла с расширением");
    val inputFileName = bufferedReader.readLine();
    val inputFile = new File(inputFileName);
    if (inputFile.exists()) {
      System.out.println("Введите имя финального файла без расширения:");
      val outputFileName = bufferedReader.readLine();
      if (inputFileName.matches(".+\\.xml")) {
        val authors = ParseToJson.parseFromXML(inputFile);
        SaveTo.toJson(authors, outputFileName);
      } else if (inputFileName.matches(".+\\.json")) {
        val library = ParseToXML.parseFromJson(inputFile);
        SaveTo.toXML(library, outputFileName);
      }
    } else {
      System.out.println("Ошибка: Данного файла не существует.");
    }
  }
}