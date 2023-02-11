package objects;

import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Author {

  private String name;
  private List<Book> books = new ArrayList<>();

  public Author(String name) {
    this.name = name;
  }

  public void addBook(Book book) {
    this.books.add(book);
  }
}
