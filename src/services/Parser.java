package services;

import objects.Author;
import objects.Authors;
import objects.Book;
import objects.Library;
import com.google.gson.Gson;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Parser {

  public Authors parseFromXML(String input)
      throws ParserConfigurationException, SAXException, IOException {
            /*
            В данном методе мы десериализируем файл XML, изменяем его структуру и сохраняем в объект.
            */

    SAXParserFactory saxParserFactory = SAXParserFactory.newInstance(); // Создание фабрики для работы с парсером.
    ParserHandler handler = new ParserHandler(); // Инициализируем наш хандлер для ручного парсинга файла.
    SAXParser saxParser = saxParserFactory.newSAXParser(); // Создаем парсер.

    File file = new File(input);
    saxParser.parse(file, handler);

    Authors authors = handler.getAuthors();
    return endParse(authors);
  }

  private Authors endParse(Authors authors) {
            /*
            Данный метод необходим, потому что мы из библиотеки книг будем делать список авторов с их книгами, внутри метода
            при помощи алгоритма дубликаты авторов в коллекции будут удалены, а книги дубликатов присвоены оригиналам.
            */

    for (int i = 0; i < authors.getAuthors().size(); i++) {
      Author author = authors.getAuthors().get(i);
      String name = author.getName();
      for (int j = 0; j < authors.getAuthors().size(); j++) {
        if (i == j) {
          continue;
        } else {
          Author secondAuthor = authors.getAuthors().get(j);
          String secondName = secondAuthor.getName();
          if (name.equals(secondName)) {
            author.getBooks().add(secondAuthor.getBook());
            authors.getAuthors().remove(secondAuthor);
          }
        }
      }
    }
    return authors;
  }

  public void parseToJson(Authors authors, String output) throws IOException {
            /*
            Для сериализации была выбрана библиотека GSON, удобство, простота, отсутствие багов после тестирования в созданном файле.
            */

    Gson gson = new Gson();
    Files.write(Path.of(output + ".json"), gson.toJson(authors).toString().getBytes());
  }

  public Library parseFromJson(File file) throws IOException {
            /*
            Для десериализации была выбрана та же библиотека GSON по тем же причинам, идеальная работа с .json файлами!
            */

    Gson gson = new Gson();
    FileReader fileReader = new FileReader(file);
    Authors authors = gson.fromJson(
        fileReader, Authors.class);
    List<Book> books = new ArrayList<>();
    for (Author e : authors.getAuthors()) {
      for (Book j : e.getBooks()) {
        j.setAuthor(e.getName());
        books.add(j);
      }
    }
    return new Library(books);
  }

  public void parseToXML(Library library, String output) throws JAXBException {
            /*
            Тут уже интереснее..
            Для сериализации в файл XML был выбран JAXB, т.к в ручную прописывать хандлер не имеет смысла, мною это было сделано без использования лишних библиотек,
            вручную.
            А JAXB как по мне отличный аналог для де/сериализации XML-файлов, имеет похожий принцип построения кода как GSON, за исключением того,
            что для работы сериализации в файл необходимо прописывать в объектах аннотации, но как по мне, это очень даже удобно.
            */

    JAXBContext context = JAXBContext.newInstance(Library.class);
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.marshal(library, new File(output));
  }
}
