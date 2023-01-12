package objects;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Authors {

  private List<Author> authors;

  public Authors(List<Author> authors) {
    this.authors = authors;
  }
}
