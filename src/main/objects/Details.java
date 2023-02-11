package objects;


import javax.xml.bind.annotation.XmlRootElement;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@XmlRootElement
public class Details {

  private int publicationYear;
  private int pages;
  private String genres;
}
