package services;

import java.util.Set;
import java.util.stream.Collectors;
import javax.xml.bind.Unmarshaller;
import lombok.val;
import objects.Author;
import objects.Authors;
import objects.Book;
import objects.Library;
import com.google.gson.Gson;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Parser {

  public Authors parseFromXML(File file)
      throws JAXBException {
            /*
            В данном методе мы десериализируем файл XML, изменяем его структуру и сохраняем в объект.
            */

    val jaxbContext = JAXBContext.newInstance(Library.class);
    val unmarshaller = jaxbContext.createUnmarshaller();
    val library = (Library) unmarshaller.unmarshal(file);

    List<Author> authors = new ArrayList<>();
    List<Book> libraryBooks = library.getBooks();
    Set<String> authorNames = libraryBooks.stream().map(Book::getAuthor)
        .collect(Collectors.toSet());
    for (var authorName : authorNames) {
      Author author = new Author(authorName);
      for (var libraryBook : libraryBooks) {
        if (authorName.equals(libraryBook.getAuthor())) {
          author.addBook(libraryBook);
          libraryBook.setAuthor(null);
        }
      }
      authors.add(author);
    }
    return new Authors(authors);
  }

  public void parseToJson(Authors authors, String output) throws IOException {
            /*
            Для сериализации была выбрана библиотека GSON, удобство, простота, отсутствие багов после тестирования в созданном файле.
            */

    val gson = new Gson();
    Files.write(Path.of(output + ".json"), gson.toJson(authors).toString().getBytes());
  }

  public Library parseFromJson(File file) throws IOException {
            /*
            Для десериализации была выбрана та же библиотека GSON по тем же причинам, идеальная работа с .json файлами!
            */

    val gson = new Gson();
    val fileReader = new FileReader(file);
    Authors authors = gson.fromJson(
        fileReader, Authors.class);
    List<Book> books = new ArrayList<>();
    for (Author element : authors.getAuthors()) {
      for (Book book : element.getBooks()) {
        book.setAuthor(element.getName());
        books.add(book);
      }
    }
    return new Library(books);
  }

  public void parseToXML(Library library, String output) throws JAXBException {
            /*
            Сериализация была сделана при помощи JAXB
            */

    val context = JAXBContext.newInstance(Library.class);
    val marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    marshaller.marshal(library, new File(output + ".xml"));
  }
}
