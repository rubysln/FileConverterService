package objects;


import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlRootElement(name = "book")
public class Book {

  private String author;
  private String title;
  private Details details;
}
