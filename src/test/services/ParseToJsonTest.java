package services;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBException;
import objects.Author;
import objects.Authors;
import objects.Book;
import objects.Details;
import org.junit.Test;

public class ParseToJsonTest {

  @Test
  public void parseFromXML() throws JAXBException {
    //act
    List<Author> authorsList = new ArrayList<>();

    //Достоевский
    Author dostoevski = new Author("Достоевский Роман Фёдорович");

    List<Book> dostoevskiBooks = new ArrayList<>();
    Book idiot = new Book();
    idiot.setAuthor(null);
    idiot.setTitle("Идиот");
    Details idiotDetails = new Details();
    idiotDetails.setGenres("Классика, Роман");
    idiotDetails.setPages(567);
    idiotDetails.setPublicationYear(1868);
    idiot.setDetails(idiotDetails);
    dostoevskiBooks.add(idiot);

    dostoevski.setBooks(dostoevskiBooks);
    authorsList.add(dostoevski);

    //Лермонтов
    Author lermontov = new Author("Михаил Юрьевич Лермонтов");

    List<Book> lermontovBooks = new ArrayList<>();
    Book heroOfOurTime = new Book();
    heroOfOurTime.setTitle("Герой нашего времени");
    heroOfOurTime.setAuthor(null);
    Details heroOfOurTimeDetails = new Details();
    heroOfOurTimeDetails.setGenres("Роман, Романтизм, Психологический реализм");
    heroOfOurTimeDetails.setPages(152);
    heroOfOurTimeDetails.setPublicationYear(1840);
    heroOfOurTime.setDetails(heroOfOurTimeDetails);

    lermontovBooks.add(heroOfOurTime);
    lermontov.setBooks(lermontovBooks);
    authorsList.add(lermontov);

    //Пушкин
    Author pushkin = new Author("Пушкин Александр Сергеевич");

    List<Book> pushkinBooks = new ArrayList<>();
    Book fairyTales = new Book();
    fairyTales.setTitle("Сказки");
    fairyTales.setAuthor(null);
    Details fairyTalesDetails = new Details();
    fairyTalesDetails.setPublicationYear(1981);
    fairyTalesDetails.setPages(132);
    fairyTalesDetails.setGenres("Классика, Сказки");
    fairyTales.setDetails(fairyTalesDetails);
    pushkinBooks.add(fairyTales);

    Book evgeniyOnegin = new Book();
    evgeniyOnegin.setAuthor(null);
    evgeniyOnegin.setTitle("Евгений Онегин");
    Details evgeniyOneginDetails = new Details();
    evgeniyOneginDetails.setGenres("Классика, Роман");
    evgeniyOneginDetails.setPages(256);
    evgeniyOneginDetails.setPublicationYear(1833);
    evgeniyOnegin.setDetails(evgeniyOneginDetails);
    pushkinBooks.add(evgeniyOnegin);

    pushkin.setBooks(pushkinBooks);
    authorsList.add(pushkin);

    //actual
    Authors authorsActual = ParseToJson.parseFromXML(new File("input.xml"));

    //expected
    Authors authorsExpected = new Authors(authorsList);

    assertEquals(authorsExpected, authorsActual);
  }
}