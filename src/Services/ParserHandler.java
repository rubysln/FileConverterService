package Services;

import Objects.Author;
import Objects.Authors;
import Objects.Book;
import Objects.Details;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class ParserHandler extends DefaultHandler {
    private static final String TAG_LIBRARY = "library";
    private static final String TAG_BOOK = "book";
    private static final String TAG_AUTHOR = "author";
    private static final String TAG_TITLE = "title";
    private static final String TAG_DETAILS = "details";
    private static final String TAG_GENRES = "genres";
    private static final String TAG_PAGES = "pages";
    private static final String TAG_PUBLICATIONYEAR = "publicationYear";
    private static final String TAG_ABOUT = "about";

    private boolean isBook = false;
    private boolean isDetails = false;


    //Искомые внутри файла объекты для сериализации
    private Authors authors = new Authors();
    private List<Author> authorList = new ArrayList<>();
    private Author author;

    //Автономные объекты не требующие изменения структуры
    private List<Book> books = new ArrayList<>();
    private Book book = new Book();
    private Details details = new Details();

    public Authors getAuthors() {
        return authors;
    }

    private String currentTagName;
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parsing");
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("End parsing");
        authors.setAuthors(authorList);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println("Loading...");
        currentTagName = qName;
        if(currentTagName != null){
            if(currentTagName.equals(TAG_BOOK)) isBook = true;
            else if(currentTagName.equals(TAG_DETAILS)) isDetails = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("Loading...");
        currentTagName = qName;
        if(currentTagName == null) return;
        if(currentTagName != null){
            if(currentTagName.equals(TAG_BOOK)) {
                isBook = false;


                books.add(book);
                author.setBooks(books);
                authorList.add(author);
                book = new Book();
                books = new ArrayList<>();
            }
            else if(currentTagName.equals(TAG_DETAILS)){
                isDetails = false;

                book.setDetails(details);
                details = new Details();
            };
            if(currentTagName.equals(TAG_BOOK) || currentTagName.equals(TAG_DETAILS)) return;
            currentTagName = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if(currentTagName == null){
            return;
        }

        if(isBook){
            switch (currentTagName){
                case (TAG_AUTHOR):
                    author = new Author(new String(ch, start, length));
                    break;
                case (TAG_TITLE):
                    book.setTitle(new String(ch, start, length));
                    break;
            }
            if(isDetails){
                switch (currentTagName){
                    case (TAG_GENRES):
                        details.setGenres(new String(ch, start, length));
                        break;
                    case(TAG_PAGES):
                        details.setPages(Integer.valueOf(new String(ch, start, length)));
                        break;
                    case(TAG_PUBLICATIONYEAR):
                        details.setPublicationYear(Integer.valueOf(new String(ch, start, length)));
                        break;
                    case(TAG_ABOUT):
                        details.setAbout(new String(ch, start, length));
                        break;
                }
            }
        }
    }
}
