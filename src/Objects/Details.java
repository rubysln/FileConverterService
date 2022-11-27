package Objects;


public class Details {
    private int publicationYear;
    private int pages;
    private String genres;
    private String about;


    public int getPublicationYear() {
        return publicationYear;
    }

    public int getPages() {
        return pages;
    }

    public String getGenres() {
        return genres;
    }

    public String getAbout() {
        return about;
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }
    public void setAbout(String about) {
        this.about = about;
    }

    @Override
    public String toString() {
        return "Details{" +
                "publicationYear=" + publicationYear +
                ", pages=" + pages +
                ", genres='" + genres + '\'' +
                ", about='" + about + '\'' +
                '}';
    }
}
