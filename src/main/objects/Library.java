package objects;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.FIELD)
@EqualsAndHashCode
@ToString
public class Library {

  @XmlElement(name = "book")
  private List<Book> books = new ArrayList<>();

  public Library(List<Book> books) {
    this.books = books;
  }
}
