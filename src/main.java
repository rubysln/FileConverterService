import Objects.Authors;
import Objects.Library;
import Services.Parser;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class main {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, JAXBException {
        Parser parser = new Parser();
        Authors authors = parser.parseFromXML();
        parser.parseToJson(authors, "output.json");
        Library library = parser.parseFromJson(new File("output.json"));
        parser.parseToXML(library, "output.xml");
    }
}
