package objects;

import java.util.List;

public class Author {

  private String name;
  private List<Book> books;

  public Author(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }

  @Override
  public String toString() {
    return "Author{" +
        "name='" + name + '\'' +
        ", books=" + books +
        '}';
  }

  public Book getBook() {
    if (books.size() > 1) {
      return null;
    } else {
      Book book = books.get(0);
      books.remove(0);
      return book;
    }
  }
}
