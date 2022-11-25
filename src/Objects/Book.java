package Objects;

import java.util.List;

public class Book {
    private String author;
    private String title;
    private int publicationYear;
    private int pages;
    private List<Genres> genres;

    public Book(String author, String title, List<Genres> genres) {
        this.author = author;
        this.title = title;
        this.genres = genres;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
