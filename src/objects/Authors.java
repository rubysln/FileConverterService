package objects;

import java.util.List;

public class Authors {

  private List<Author> authors;


  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }

  @Override
  public String toString() {
    return "Authors{" +
        "authors=" + authors +
        '}';
  }
}
