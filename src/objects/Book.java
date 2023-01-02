package objects;


public class Book {

  private String author;
  private String title;
  private Details details;

  public String getTitle() {
    return title;
  }

  public Details getDetails() {
    return details;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setDetails(Details details) {
    this.details = details;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  @Override
  public String toString() {
    return "Book{" +
        "author='" + author + '\'' +
        ", title='" + title + '\'' +
        ", details=" + details +
        '}';
  }
}
