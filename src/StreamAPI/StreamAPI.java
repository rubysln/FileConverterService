package StreamAPI;

import Objects.Book;
import Objects.Genres;
import Objects.Library;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class StreamAPI {
    private Library library;
    private List<Book> books;

    public StreamAPI(Library library) {
        this.library = library;
        this.books = library.getBook();
    }

    public List<Integer> map(){
        return books.stream().map(Book::getPages).collect(Collectors.toList());
    }

    public List<Book> filter(Integer underThanPages, Integer underThanPublicationYear){
        return books.stream().filter(Book -> Book.getPages() > underThanPages && Book.getPublicationYear() > underThanPublicationYear).collect(Collectors.toList());
    }

    public List<Book> forEach(String genreName){
        books.stream().forEach(Book -> Book.setGenres(genreName));
        return books;
    }

    public Set<String> collect(){
        return books.stream().map(Book::getAuthor).collect(Collectors.toSet());
    }

    public List<Book> sorted(){
        return books.stream().sorted(Comparator.comparing(Book::getPublicationYear)).collect(Collectors.toList());
    }
}
