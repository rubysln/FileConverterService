package services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import lombok.val;
import objects.Author;
import objects.Authors;
import objects.Book;
import objects.Library;

public class ParseToJson {

  public static Authors parseFromXML(File file)
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
}
