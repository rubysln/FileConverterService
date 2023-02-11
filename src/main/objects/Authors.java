package objects;

import java.util.List;
import java.util.Objects;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Authors {

  private List<Author> authors;

  public Authors(List<Author> authors) {
    this.authors = authors;
  }
}
