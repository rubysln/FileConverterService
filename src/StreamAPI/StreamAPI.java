package StreamAPI;

import Objects.Book;
import Objects.Library;

import java.util.List;
import java.util.stream.Collectors;

public class StreamAPI {
    private Library library;
    List<Book> books = library.getBook();

    public StreamAPI(Library library) {
        this.library = library;
    }

    public List<String> map(){
        return books.stream().map(Book::getAuthor).collect(Collectors.toList());
    }
    
}
