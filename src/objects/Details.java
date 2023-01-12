package objects;


import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@XmlRootElement
public class Details {

  private int publicationYear;
  private int pages;
  private String genres;
  private String about;
}
