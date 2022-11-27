package Services;

import Objects.Author;
import Objects.Authors;
import Objects.Book;
import Objects.Library;
import com.google.gson.Gson;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Parser {
        public Authors parseFromXML() throws ParserConfigurationException, SAXException, IOException {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            ParserHandler handler = new ParserHandler();
            SAXParser saxParser = saxParserFactory.newSAXParser();

            File file = new File("input.xml");
            saxParser.parse(file, handler);

            Authors authors = handler.getAuthors();
            return endParse(authors);
        }
        private Authors endParse(Authors authors){
            for(int i = 0; i < authors.getAuthors().size(); i++){
                Author author = authors.getAuthors().get(i);
                String name = author.getName();
                for(int j = 0; j < authors.getAuthors().size(); j++){
                    if(i == j) continue;
                    else{
                        Author secondAuthor = authors.getAuthors().get(j);
                        String secondName = secondAuthor.getName();
                        if(name.equals(secondName)){
                            author.getBooks().add(secondAuthor.getBook());
                            authors.getAuthors().remove(secondAuthor);
                        }
                    }
                }
            }
            return authors;
        }

        public void parseToJson(Authors authors, String output) throws IOException {
            Gson gson = new Gson();
            Files.write(Path.of("output.json"), gson.toJson(authors).toString().getBytes());
        }

        public Library parseFromJson(File file) throws IOException {
            Gson gson = new Gson();
            FileReader fileReader = new FileReader(file);
            Authors authors = gson.fromJson(fileReader, Authors.class);
            List<Book> books = new ArrayList<>();
            for(var e:authors.getAuthors()){
                for(var j:e.getBooks()){
                    j.setAuthor(e.getName());
                    books.add(j);
                }
            }
            return new Library(books);
        }

        public void parseToXML(Library library, String output) throws IOException, JAXBException {
            JAXBContext context = JAXBContext.newInstance(Library.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(library, new File(output));
        }
}
