package Objects;

import java.util.List;

public class Library {
    private List<Book> book;

    public Library(List<Book> books) {
        this.book = books;
    }

    public List<Book> getBook() {
        return book;
    }

    @Override
    public String toString() {
        return "Library{" +
                "book=" + book +
                '}';
    }
}
