package objects;


import javax.xml.bind.annotation.XmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@XmlRootElement(name = "book")
public class Book {

  private String author;
  private String title;
  private Details details;
}
